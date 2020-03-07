package burukeyou.admin.service.impl;

import burukeyou.admin.entity.dto.QueryUserConditionDto;
import burukeyou.admin.entity.pojo.UmsAdmin;
import burukeyou.admin.entity.pojo.UmsAdminRole;
import burukeyou.admin.entity.vo.UmsAdminVO;
import burukeyou.admin.mapper.UmsAdminMapper;
import burukeyou.admin.service.UmsAdminRoleService;
import burukeyou.admin.service.UmsAdminService;
import burukeyou.auth.authClient.util.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper,UmsAdmin> implements UmsAdminService {

    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    private UmsAdminRoleService adminRoleRelationService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveOrupdate(UmsAdmin umsAdmin) {
        Assert.notNull(umsAdmin,"umsAdmin to create or update must not be null");

        if (umsAdmin.getId() == null){
            // save
            umsAdmin = UmsAdmin.builder().deleted(false).accountNonExpired(true)
                    .credentialsNonExpired(true).accountNonLocked(true)
                    .createHost("xx").adminName(umsAdmin.getAdminName())
                    .password(passwordEncoder.encode(umsAdmin.getPassword()))
                    .nickname(umsAdmin.getNickname()).mobile(umsAdmin.getMobile())
                    .email(umsAdmin.getEmail()).avatar(umsAdmin.getAvatar()).description(umsAdmin.getDescription()).build();

            // 分配角色


        }else if (!umsAdmin.getId().equals(AuthUtils.ID()))
            //update
            return false;

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

        Page<UmsAdmin> page = new Page<>(dto.getPage(),dto.getSize());

        if ("asc".equals(dto.getOrder())){
            page.addOrder(OrderItem.asc(dto.getOrderField()));
        }else {
            page.addOrder(OrderItem.desc(dto.getOrderField()));
        }
        return baseMapper.getListByCondition(page,dto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean setRoleOfUser(String userId, Set<String> roleIdList) {
        int count = this.count(new QueryWrapper<UmsAdmin>().lambda().eq(UmsAdmin::getId,userId));
        if (count <= 0)
            return false;

        adminRoleRelationService.deleteByUserIdRoleId(userId,null);

        List<UmsAdminRole> list = roleIdList.stream().map(e -> new UmsAdminRole(userId, e)).collect(Collectors.toList());
        return adminRoleRelationService.saveBatch(list);
    }
}
