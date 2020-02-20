package burukeyou.user.service;

import burukeyou.user.entity.pojo.UmsFavorites;

import java.util.List;

public interface UmsFavoritesService {

    /**
     *  save or update
     * @param umsFavorites
     * @return
     */
    boolean insertOrUpdate(UmsFavorites umsFavorites);

    /**
     *  get favorites list by user id
     * @param userId
     * @return
     */
    List<UmsFavorites> getListByUserId(String userId);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     *      get one favorites unique id
     * @param id
     * @return
     */
    UmsFavorites getById(String id);
}
