package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.domain.ShareUserDetail;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.UserEntity;
import com.github.wkw.share.vo.request.LoginRequest;

public interface UserService {

    UserEntity login(LoginRequest request, Integer appId, String appVersion, String appModel) throws CommonException;

    int insertUser(ShareUser user);

    ShareUser findByPhone(String phone);

    ShareUserDetail findDetailByPhone(String phone);

    ListDataEntity<ShareUser> users(AbstractQry qry);

    int changeUserState(Integer uId, Boolean deleted);

    int logOut(Integer uId);
}
