package burukeyou.admin.service.impl;

import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.dto.UmsAdminDto;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.admin.entity.pojo.UmsAdminRole;
import burukeyou.admin.entity.vo.UmsAdminVO;
import burukeyou.admin.entity.vo.UmsRoleVO;
import burukeyou.admin.mapper.UmsAdminMapper;
import burukeyou.admin.service.UmsAdminRoleService;
import burukeyou.admin.service.UmsAdminService;
import burukeyou.admin.service.UmsRoleService;
import burukeyou.admin.utils.CommonUtils;
import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.common.core.utils.CustomBeanUtils;
import burukeyou.common.core.utils.IdWorker;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper,UmsAdmin> implements UmsAdminService {

    private  PasswordEncoder passwordEncoder;

    private UmsAdminRoleService adminRoleRelationService;

    private UmsRoleService  umsRoleService;

    public UmsAdminServiceImpl(PasswordEncoder passwordEncoder, UmsAdminRoleService adminRoleRelationService, UmsRoleService umsRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.adminRoleRelationService = adminRoleRelationService;
        this.umsRoleService = umsRoleService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean saveOrupdate(UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = umsAdminDto.converTo();
        Assert.notNull(umsAdmin,"umsAdmin to create or update must not be null");

        if (umsAdmin.getId() == null){
            // save
            String userId = Long.toString(new IdWorker().nextId());
            umsAdmin.setId(userId);
            umsAdmin.setAccountNonExpired(true);
            umsAdmin.setDeleted(false);
            umsAdmin.setEnabled(true);
            umsAdmin.setCredentialsNonExpired(true);
            umsAdmin.setAccountNonLocked(true);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            umsAdmin.setCreateHost(CommonUtils.getRemoteIpAddress(request));
            umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        }

        // 重新设置角色
        List<String> roleIdLits = umsAdminDto.getRoleList();
        if (!CollectionUtils.isEmpty(roleIdLits)){
            adminRoleRelationService.deleteByUserIdRoleId(umsAdmin.getId(),null);
            List<UmsAdminRole> list =roleIdLits.stream().map(e -> new UmsAdminRole(umsAdmin.getId(), e)).collect(Collectors.toList());
            adminRoleRelationService.saveBatch(list);
        }

        return super.saveOrUpdate(umsAdmin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean deleteById(String userId) {
        adminRoleRelationService.deleteByUserIdRoleId(userId,null);
        return super.removeById(userId);
    }


    @Override
    public Page<UmsAdminVO> getListByCondition(QueryUserConditionDto dto) {
        Assert.notNull(dto.getPage(),"query page of condition cant not be null");

        Page<UmsAdminVO> page = new Page<>(dto.getPage(),dto.getSize());

//        if ("asc".equals(dto.getOrder())){
//            page.addOrder(OrderItem.asc(dto.getOrderField()));
//        }else {
//            page.addOrder(OrderItem.desc(dto.getOrderField()));
//        }
        Page<UmsAdminVO> list = baseMapper.getListByCondition(page, dto);

        list.setTotal(super.count()); //  由于关联了role会有多个都是使用result聚合了，它仍然按聚合前算
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean setRoleOfUser(String userId, Set<String> roleIdList) {
        int count = this.count(new QueryWrapper<UmsAdmin>().lambda().eq(UmsAdmin::getId,userId));
        if (count <= 0)
            return false;

        adminRoleRelationService.deleteByUserIdRoleId(userId,null);

        if (!CollectionUtils.isEmpty(roleIdList)){
            List<UmsAdminRole> list = roleIdList.stream().map(e -> new UmsAdminRole(userId, e)).collect(Collectors.toList());
            return adminRoleRelationService.saveBatch(list);
        }

        return false;
    }

    @Override
    public UmsAdminVO getOneById(String id) {
        // todo update no role will fill error roleVo bug
       return baseMapper.getListByCondition(QueryUserConditionDto.builder().userId(id).build());
    }


}
