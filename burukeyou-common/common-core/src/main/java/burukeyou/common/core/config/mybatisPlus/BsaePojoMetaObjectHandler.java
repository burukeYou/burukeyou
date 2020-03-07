/*
package burukeyou.common.core.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

public abstract class BsaePojoMetaObjectHandler implements MetaObjectHandler {

    //
    public abstract String getCurrentUsername() ;

    // call when insert fill
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdBy",  String.class,  getCurrentUsername()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createdTime", Date.class, Date.from(ZonedDateTime.now().toInstant())); // 起始版本 3.3.0(推荐使用)
    }

    // call when update fill
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedBy", String.class,  getCurrentUsername()); // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "updatedTime",Date.class, Date.from(ZonedDateTime.now().toInstant())); // 起始版本 3.3.0(推荐使用)
    }
}
*/
