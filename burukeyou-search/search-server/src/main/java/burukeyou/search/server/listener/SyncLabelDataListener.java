package burukeyou.search.server.listener;

import burukeyou.search.server.entity.enums.IndexEnums;
import burukeyou.search.server.entity.es.EsArticle;
import burukeyou.search.server.entity.es.EsLabel;
import burukeyou.search.server.entity.es.EsUser;
import burukeyou.search.server.temp.*;
import burukeyou.search.server.utils.EsUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class SyncLabelDataListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private SysLableServiceImpl sysLableService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
       // buildArticle();


    }

    private void  buildUser(){
        EsUtils esUtils = new EsUtils(IndexEnums.USER.getIndex(), restHighLevelClient);
        for (UmsUser e : umsUserMapper.all()) {
            EsUser esUser = new EsUser();
            BeanUtils.copyProperties(e,esUser,"createdTime");
            esUtils.insert(esUser);
        }
    }

    private void  buildArticle(){
        EsUtils esUtils = new EsUtils(IndexEnums.ARTICLE.getIndex(), restHighLevelClient);
        for (AmsArticle e : articleMapper.all()) {
            EsArticle esArticle = new EsArticle();
            BeanUtils.copyProperties(e,esArticle,"createdTime");
            esArticle.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedTime()));
            esUtils.insert(esArticle);
        }

    }

    private void  buildLabel(){
        EsUtils esUtils = new EsUtils(IndexEnums.LABEL.getIndex(), restHighLevelClient);
        List<SysLabel> list = sysLableService.list();
        for (SysLabel e : list) {
            EsLabel esLabel = new EsLabel();
            esLabel.setId(e.getId());
            esLabel.setName(e.getName());
            esLabel.setFocusCount(e.getFocusCount());
            esLabel.setArticleCount(e.getArticleCount());
            esLabel.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreatedTime()));
            esLabel.setAvatar(e.getAvatar());

            esUtils.insert(esLabel);
        }
    }
}
