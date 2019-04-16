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

package com.github.wkw.share.cache;

import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.thirdparty.cache.BaseCache;
import org.springframework.stereotype.Component;

/**
 * author wukewei on 2019/4/15.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

@Component
public class ShareUserInfoCache extends BaseCache<ShareUserInfo> {
    @Override
    public String getPrefixKey() {
        return "share-info-";
    }

    @Override
    public Class<ShareUserInfo> getTClass() {
        return ShareUserInfo.class;
    }
}
