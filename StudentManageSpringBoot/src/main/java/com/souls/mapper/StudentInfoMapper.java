package com.souls.mapper;

import com.souls.po.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface StudentInfoMapper {

    StudentInfo getStuByID(@Param("stuID") String stuID);

    List<StudentInfo> getAllStudent();

    Boolean updateLoginInfo(@Param("stuID") String stuID, @Param("time") Long time, @Param("count") Integer count);

    Boolean updateStu(StudentInfo stu);

    Boolean deleteStu(@Param("stuID") String stuID);

    Boolean addStudent(StudentInfo stu);

    List<StudentInfo> selectStu(@Param("name") String name, @Param("major") Integer major);

    String getMaxXH();
}
