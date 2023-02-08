package com.souls.mapper;

import com.souls.po.MarkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface MarkInfoMapper {
    List<MarkInfo> getAllMarkInfo();

    Boolean deleteScore(@Param("id") String id);

    Boolean addScore(MarkInfo markInfo);
}
