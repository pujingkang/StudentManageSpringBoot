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
    public static void addQueryData(String uuid, List<Object> value){
        pools.put(uuid,value);
    }

    //将查询结果放到缓存中
    public static void addQueryMethod(String method2Params, String uuid){
        methodsPools.put(method2Params,uuid);
    }

    //查询缓存中是否已有所需查询结果
    public static String searchUUidByMethod(String uuid){
        return methodsPools.get(uuid);
    }

    public static PageInfo getDataFromPools(PageInfo pageInfo) {
        List<Object> srcList = null;
        Integer page = pageInfo.getPage();
        Integer size = pageInfo.getSize();
        if (page == null && page <= 0) page = 1;
        if (size == null && size <= 0) size = 2;
        List<Object> source = pools.get(pageInfo.getUuid());
        int total = source.size();
        int totalPage = 0;
        if (total % size == 0) totalPage = total / size;
        else
            totalPage = total / size + 1;
        if (page > totalPage) page = totalPage;

        List<Object> rows = new ArrayList();
        int start = (page - 1) * size;
        for (int i = start; i < (start + size) && i < total; i++)
            rows.add(source.get(i));

        pageInfo.setPage(page);
        pageInfo.setSize(size);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setRows(rows);

        return pageInfo;
    }
}

