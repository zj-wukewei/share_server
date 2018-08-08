package com.github.wkw.share.dao;

import com.github.wkw.share.domain.ShareUserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Mapper
public interface UserMapper {
    @Select("select u.id, u.phone,u.password, u.app_type as appType, r.role_name as roleName, u.app_model as appModel, u.token  from share_user u left join share_role r on u.role_id = r.id where u.phone =  #{mobile}")
    ShareUserDetail loadUserByUsername(@Param("mobile") String mobile);
}
