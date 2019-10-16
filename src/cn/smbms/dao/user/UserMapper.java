package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr
 */
public interface UserMapper {

    /**
     * 通过userCode获取User
     * @param userCode
     * @return
     * @throws Exception
     */
    User getLoginUser(@Param("userCode") String userCode)throws Exception;

}
