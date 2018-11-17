package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareUserMapper;
import com.github.wkw.share.dao.UserMapper;
import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.domain.ShareUserDetail;
import com.github.wkw.share.domain.ShareUserExample;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.thirdparty.security.TokenService;
import com.github.wkw.share.utils.StringUtils;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.UserEntity;
import com.github.wkw.share.vo.request.LoginRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    ShareUserMapper shareUserMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public UserEntity login(LoginRequest request, Integer appId, String appVersion, String appModel) throws CommonException {
        ShareUserDetail user = userMapper.loadUserByUsername(request.getMobile());
        if (!ObjectUtils.isEmpty(user) && StringUtils.equals(request.getPassword(), user.getPassword())) {
            Long loginTime = System.currentTimeMillis();
            Long expiredTime = TokenService.tokenExpireDate(loginTime);
            String token = TokenService.encodeToken(expiredTime, request.getMobile(), request.getPassword(), appId);
            ShareUser shareUser = new ShareUser();
            shareUser.setId((user).getId());
            shareUser.setUpdateTime(LocalDateTime.now());
            shareUser.setToken(token);
            shareUser.setAppModel(appModel);
            shareUser.setAppVersion(appVersion);
            shareUser.setAppType(appId);
            shareUserMapper.updateByPrimaryKeySelective(shareUser);
            return new UserEntity(user.getId(), token);
        }
        throw new CommonException("用户名或密码错误");
    }

    @Override
    public int insertUser(ShareUser user) {
        return shareUserMapper.insert(user);
    }

    @Override
    public ShareUser findByPhone(String phone) {
        ShareUserExample userExample = new ShareUserExample()
                .createCriteria()
                .andPhoneEqualTo(phone)
                .example();
        return shareUserMapper.selectOneByExample(userExample);
    }

    @Override
    public ShareUserDetail findDetailByPhone(String phone) {
        return null;
    }

    @Override
    public ListDataEntity<ShareUser> users(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> shareUserMapper.selectByExample(null));
    }

    @Override
    public int changeUserState(Integer uId, boolean deleted) {
        ShareUser user = new ShareUser();
        user.setId(uId);
        user.setDeleted(deleted);
        user.setUpdateTime(LocalDateTime.now());
        return shareUserMapper.updateByPrimaryKeySelective(user);
    }
}
