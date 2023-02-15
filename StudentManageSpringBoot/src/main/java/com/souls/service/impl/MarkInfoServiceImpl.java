package com.souls.service.impl;

import com.souls.mapper.MarkInfoMapper;
import com.souls.po.MarkInfo;
import com.souls.service.MarkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkInfoServiceImpl implements MarkInfoService {
    @Autowired
    private MarkInfoMapper markInfoMapper;

    public List<MarkInfo> getAllMarkInfo() {
        return markInfoMapper.getAllMarkInfo();
    }

    public Boolean deleteScore(String id) {
        return markInfoMapper.deleteScore(id);
    }

    public Boolean addScore(MarkInfo markInfo) {
        return markInfoMapper.addScore(markInfo);
    }

    public Integer createID() {
        List<MarkInfo> list = this.getAllMarkInfo();
        if (list.size() == 0) return 1;
        MarkInfo markInfo = list.get(list.size() - 1);
        Integer lastID = markInfo.getId();
        return lastID + 1;
    }
}
