package burukeyou.user.service;

import burukeyou.user.entity.pojo.UmsColumn;

public interface UserColumnService {

    /**
     *      save or update Column info
     * @param umsColumn
     * @return success or fail
     */
    Boolean saveOrupdate(UmsColumn umsColumn);

}
