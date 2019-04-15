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

package com.github.wkw.share.thirdparty.cache;

import com.alibaba.fastjson.JSON;
import com.github.wkw.share.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * author wukewei on 2019/4/15.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public abstract class BaseCache<T> implements IBaseCache<T> {
    @Autowired
    RedisTemplater redisTemper;

    public int defaultTimeout;

    public abstract String getPrefixKey();

    public abstract Class<T> getTClass();

    @Override
    public void setById(String id, T o) {
        String key = getPrefixKey() + id;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemper.setKey(key, JSON.toJSONString(o));
        }
    }

    @Override
    public void setById(String id, T o, int timeout) {
        String key = getPrefixKey() + id;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemper.setKey(key, JSON.toJSONString(o), timeout);
        }
    }

    @Override
    public void set(String key, T o) {
        key = getPrefixKey() + key;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemper.setKey(key, JSON.toJSONString(o));
        }
    }

    @Override
    public void set(String key, T o, int timeout) {
        key = getPrefixKey() + key;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemper.setKey(key, JSON.toJSONString(o), timeout);
        }
    }

    @Override
    public T getById(String id) {
        String key = getPrefixKey() + id;
        String value = redisTemper.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            T o = JSON.parseObject(value, getTClass());
            return o;
        } else {
            return null;
        }
    }

    @Override
    public T get(String key) {
        key = getPrefixKey() + key;
        String value = redisTemper.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            T o = JSON.parseObject(value, getTClass());
            return o;
        } else {
            return null;
        }
    }

    @Override
    public void setList(List<T> list) {
        String key = getPrefixKey();
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemper.setKey(key, value);
        }


    }

    @Override
    public void setList(List<T> list, int timeout) {

        String key = getPrefixKey();
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemper.setKey(key, value, timeout);
        }
    }

    @Override
    public boolean hasList() {
        return redisTemper.hasKey(getPrefixKey());
    }

    @Override
    public List<T> getList() {
        String key = getPrefixKey();
        String value = redisTemper.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            List<T> list = JSON.parseArray(value, getTClass());
            return list;
        } else {
            return null;
        }

    }

    @Override
    public void setList(String key, List<T> list) {
        key = getPrefixKey() + key;
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemper.setKey(key, value);
        }

    }

    @Override
    public void setList(String key, List<T> list, int timeout) {
        key = getPrefixKey() + key;
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemper.setKey(key, value, timeout);
        }
    }

    @Override
    public boolean hasList(String key) {
        return redisTemper.hasKey(getPrefixKey() + key);
    }

    @Override
    public List<T> getList(String key) {
        key = getPrefixKey() + key;
        String value = redisTemper.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            List<T> list = JSON.parseArray(value, getTClass());
            return list;
        } else {
            return null;
        }

    }

    @Override
    public boolean hasKeyById(String id) {
        String key = getPrefixKey() + id;
        return redisTemper.hasKey(key);
    }

    @Override
    public boolean hasKey(String key) {
        key = getPrefixKey() + key;
        return redisTemper.hasKey(key);
    }

    @Override
    public void deleteKeyById(String id) {
        String key = getPrefixKey() + id;
        redisTemper.deleteKey(key);

    }

    @Override
    public void deleteKey(String key) {
        key = getPrefixKey() + key;
        redisTemper.deleteKey(key);

    }

    @Override
    public void deleteList() {
        String key = getPrefixKey();
        redisTemper.deleteKey(key);

    }

    @Override
    public void deleteList(String key) {
        key = getPrefixKey() + key;
        redisTemper.deleteKey(key);

    }

    @Override
    public void deleteAllEntityKeys() {
        Set<String> set = redisTemper.getPatternKeys(getPrefixKey());
        set.stream().forEach(s -> redisTemper.deleteKey(s));
    }
}
