package com.souls.utils;

import com.souls.vo.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class QueryPools {
    //用于做缓存
    private static ConcurrentHashMap<String, List<Object>> pools = new ConcurrentHashMap<>();
    //缓存
    private static ConcurrentHashMap<String, String> methodsPools = new ConcurrentHashMap<>();

    //将查询结果添加到缓存中
    public static void addQueryData(String uuid, List<Object> value, RedisUtil redisUtil) {
        if (redisUtil == null) pools.put(uuid, value);
        else
            redisUtil.saveObjectByByte(uuid, value);
    }

    //将查询结果放到缓存中
    public static void addQueryMethod(String method2Params, String uuid, RedisUtil redisUtil) {
        if (redisUtil == null)
            methodsPools.put(method2Params, uuid);
        else
            redisUtil.setString(method2Params, uuid);
    }

    //查询缓存中是否已有所需查询结果
    public static String searchUUidByMethod(String uuid, RedisUtil redisUtil) {
        if (redisUtil == null)
            return methodsPools.get(uuid);
        return redisUtil.getString(uuid);
    }

    public static PageInfo getDataFromPools(PageInfo pageInfo, RedisUtil redisUtil) {
        List<Object> srcList = null;
        if (redisUtil == null) srcList = pools.get(pageInfo.getUuid());
        else srcList = (List<Object>) redisUtil.getObjectFromByte(pageInfo.getUuid());
        if (srcList == null) {
            pageInfo.setStatus("404");
            return pageInfo;
        }

        Integer total = srcList.size();
        Integer size = pageInfo.getSize();
        if (size == null || size <= 0) size = 2;

        int pages = 0;
        if (total % size == 0)
            pages = total / size;
        else
            pages = total / size + 1;
        Integer page = pageInfo.getPage();
        if (page == null || page <= 0)
            page = 1;
        else if (page > pages)
            page = pages;

        pageInfo.setPage(page);
        pageInfo.setSize(size);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(pages);
        pageInfo.setStatus("200");

        int start = (page - 1) * size;

        List<Object> target = new ArrayList<>();
        for (int i = start; (i - start) < size && i < srcList.size(); i++) {
            target.add(srcList.get(i));
        }
        pageInfo.setRows(target);
        return pageInfo;
    }
}

