package com.souls.service;

import com.souls.po.StudentInfo;
import com.souls.vo.PageInfo;
import com.souls.vo.StudentInfoVo;

import java.util.List;

public interface StudentInfoService {
    Integer getMaxXH();

    StudentInfoVo login(String stuID, String pwd);

    Object getStudentByID(String id);

    List<StudentInfo> getAllStudent();

    List<StudentInfo> queryAllStudent(PageInfo pageInfo);

    Boolean deleteStu(String id);

    Boolean updateStu(StudentInfo studentInfo);

    Boolean addStudent(StudentInfo stu);

    List<StudentInfo> selectStu(String name, Integer major);

    String createStuID();
}
