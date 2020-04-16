package burukeyou.auth.authClient.service;

import burukeyou.auth.authClient.util.AuthUtils;

import java.lang.reflect.Field;

public interface AuthService<T> {

    default boolean isEntityOwener(String entityId){
        T one = this.getById(entityId);
        try {
            Field userId = one.getClass().getDeclaredField("userId");
            userId.setAccessible(true);
            String ownerId = (String) userId.get(one);
            return (ownerId != null && !ownerId.equals(AuthUtils.ID())) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    T getById(String entityId);


}
