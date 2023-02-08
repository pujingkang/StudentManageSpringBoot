package com.souls.service;

import com.souls.po.MarkInfo;

import java.util.List;

public interface MarkInfoService {
    List<MarkInfo> getAllMarkInfo();

    Boolean deleteScore(String id);

    Integer createID();

    Boolean addScore(MarkInfo markInfo);
}
