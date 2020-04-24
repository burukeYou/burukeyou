package burukeyou.boiling.service.impl;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.boiling.entity.dto.BoilingDto;
import burukeyou.boiling.entity.dto.FileParamDto;
import burukeyou.boiling.entity.dto.QueryConditionDto;
import burukeyou.boiling.entity.enums.StateEnum;
import burukeyou.boiling.entity.pojo.AmsBoiling;
import burukeyou.boiling.mapper.BoillingMapper;
import burukeyou.boiling.rpc.FileServiceRPC;
import burukeyou.boiling.service.BoilingService;
import burukeyou.common.core.entity.enums.FileDirTypeEnum;
import burukeyou.common.core.entity.vo.ResultVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoilingServiceImpl extends ServiceImpl<BoillingMapper,AmsBoiling> implements BoilingService {

    private FileServiceRPC fileServiceRPC;

    public BoilingServiceImpl(FileServiceRPC fileServiceRPC) {
        this.fileServiceRPC = fileServiceRPC;
    }

    @Override
    public boolean publish(BoilingDto boilingDto) {

        ResultVo<List<String>> resultVo = fileServiceRPC.uploadListBase64(new FileParamDto(FileDirTypeEnum.Boiling.VALUE(), boilingDto.getImgfiles()));

        AmsBoiling amsBoiling = boilingDto.converTo();
        amsBoiling.setUserId(AuthUtils.ID());
        amsBoiling.setUserNickname(AuthUtils.NICKNAME());
        amsBoiling.setUserAvatar(AuthUtils.AVATAR());
        amsBoiling.setCommentCount(0);
        amsBoiling.setThumbupCount(0);
        amsBoiling.setVisitsCount(0);
        amsBoiling.setState(StateEnum.PASS.STATE());
        amsBoiling.setUrl("xx");

        StringBuilder sb = new StringBuilder();
        if(resultVo != null && resultVo.getData() != null){
            resultVo.getData().forEach(e->sb.append(e).append("$$"));
        }else
            return false;

        if (StringUtils.isNotBlank(sb.toString()))
            amsBoiling.setContentPic(sb.toString());

        return this.save(amsBoiling);
    }

    @Override
    public Page<AmsBoiling> getPageCondition(QueryConditionDto dto) {
        Page<AmsBoiling> page = new Page<>(dto.getPage(),dto.getSize());
        dto.setLoginUserId(AuthUtils.ID());
        return baseMapper.getPageCondition(page,dto);
    }


    @Override
    public boolean deleteById(String id) {
        //1
        if (!IsEntityOwner(id))
            return false;

        //2 todo 删除该boiling下保存的图片

        //3
        return this.removeById(id);
    }





    // todo 待重构
    private boolean IsEntityOwner(String entityId){
        AmsBoiling one = this.getOne(new QueryWrapper<AmsBoiling>().select("user_id").eq("id", entityId));
        return (one != null && one.getUserId() != null && !one.getUserId().equals(AuthUtils.ID())) ? false : true;
    }
}
