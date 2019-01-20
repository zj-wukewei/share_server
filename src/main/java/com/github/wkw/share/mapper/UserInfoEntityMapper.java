/*
 * Copyright 2017 wukewei. https://github.com/zj-wukewei
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.wkw.share.mapper;

import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.vo.UserInfoEntity;
import com.github.wkw.share.vo.request.UserInfoRequest;
import org.mapstruct.Mapper;

/**
 * author wukewei on 2019/1/20.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Mapper(componentModel = "spring")
public interface UserInfoEntityMapper {

    UserInfoEntity shareUserInfoToUserInfoEntity(ShareUserInfo info);


    ShareUserInfo userInfoRequestToShareUserInfo(UserInfoRequest request);
}
