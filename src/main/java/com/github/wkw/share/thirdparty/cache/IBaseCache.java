package com.github.wkw.share.thirdparty.cache;

import java.util.List;

/**
 * author wukewei on 2019/4/15.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface IBaseCache<T> {
    void setById(String id, T o);

    void setById(String id, T o, int timeout);

    void set(String key, T o);

    void set(String key, T o, int timeout);

    T getById(String id);

    T get(String key);

    void setList(List<T> list);

    void setList(List<T> list, int timeout);

    boolean hasList();

    List<T> getList();

    void setList(String key, List<T> list);

    void setList(String key, List<T> list, int timeout);

    boolean hasList(String key);

    List<T> getList(String key);

    boolean hasKeyById(String id);

    boolean hasKey(String key);

    void deleteKeyById(String id);

    void deleteKey(String key);

    void deleteList();

    void deleteList(String key);

    void deleteAllEntityKeys();

}
