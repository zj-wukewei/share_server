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

package com.github.wkw.share.vo.request;

import javax.validation.constraints.NotNull;

/**
 * author wukewei on 2018/12/16.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FeedStatusRequest {
    @NotNull(message = "feedId不能为空")
    private Integer feedId;

    @NotNull(message = "deleted不能为空")
    private boolean deleted;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
