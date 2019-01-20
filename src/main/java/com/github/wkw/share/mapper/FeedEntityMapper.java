package com.github.wkw.share.mapper;

import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.vo.FeedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * author wukewei on 2019/1/19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Mapper(componentModel = "spring")
public interface FeedEntityMapper {

    FeedEntityMapper INSTANCE = Mappers.getMapper(FeedEntityMapper.class);


    FeedEntity shareFeedToFeedEntity(ShareFeed shareFeed);

    @Mappings({
            @Mapping(source = "shareFeed.id", target = "id"),
            @Mapping(source = "shareFeed.userId", target = "userId"),
            @Mapping(source = "shareFeed.content", target = "content"),
            @Mapping(source = "shareFeed.images", target = "images"),
            @Mapping(source = "shareFeed.video", target = "video"),
            @Mapping(source = "shareFeed.likeCount", target = "likeCount"),
            @Mapping(source = "shareFeed.commentCount", target = "commentCount"),
            @Mapping(source = "shareFeed.addTime", target = "addTime"),
            @Mapping(source = "shareFeed.tagId", target = "tagId"),
            @Mapping(source = "shareFeed.categoryId", target = "categoryId"),
            @Mapping(source = "shareFeed.deleted", target = "deleted"),
            @Mapping(source = "info.nickname", target = "userName"),
            @Mapping(source = "info.avatar", target = "userAvatar"),
            @Mapping(source = "liked", target = "liked")
    })
    FeedEntity shareFeedToFeedEntityeedEntity(ShareFeed shareFeed, ShareUserInfo info, boolean liked);
}
