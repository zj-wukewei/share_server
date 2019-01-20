package com.github.wkw.share.mapper;

import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.vo.FollowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * author wukewei on 2019/1/19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Mapper(componentModel = "spring")
public interface FollowEntityMapper {

    FollowEntityMapper INSTANCE = Mappers.getMapper(FollowEntityMapper.class);

    @Mapping(source = "followed", target = "followed")
    FollowEntity shareUserInfoToFollow(ShareUserInfo shareUserInfo, boolean followed);
}
