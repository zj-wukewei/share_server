package com.github.wkw.share.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.wkw.share.vo.ListDataEntity;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GoGo on  2018/8/10
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FastjsonUtils {
    public static <T> T transformObject(Object o, Class<T> to) {
        String josnString = JSONObject.toJSONString(o);
        return JSONObject.parseObject(josnString, to);
    }

    public static <T> ListDataEntity<T> transformListData(ListDataEntity entity, Class<T> to) {
        if (entity == null) {
            return new ListDataEntity<>();
        }
        ListDataEntity<T> dataEntity = new ListDataEntity<>();
        dataEntity.setHasMore(entity.isHasMore());
        dataEntity.setTotal(entity.getTotal());
        List<T> lists = new ArrayList<>(entity.getList().size());
        if (entity.getList() != null) {
            for (Object o : entity.getList()) {
                T d = transformObject(o, to);
                lists.add(d);
            }
        }
        dataEntity.setList(lists);
        return dataEntity;
    }

    public static <T> ListDataEntity<T> transformListData(ListDataEntity entity, Class<T> to, @NonNull TransInvoke invoke) throws Exception {
        if (entity == null) {
            return new ListDataEntity<>();
        }
        ListDataEntity<T> dataEntity = new ListDataEntity<>();
        dataEntity.setHasMore(entity.isHasMore());
        dataEntity.setTotal(entity.getTotal());
        List<T> lists = new ArrayList<>(entity.getList().size());
        if (entity.getList() != null) {
            for (Object o : entity.getList()) {
                T d = transformObject(o, to);
                invoke.invoke(o, d);
                lists.add(d);
            }
        }
        dataEntity.setList(lists);
        return dataEntity;
    }
}
