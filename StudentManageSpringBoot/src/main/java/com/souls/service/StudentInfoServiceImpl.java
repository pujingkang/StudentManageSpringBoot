package com.souls.service;

import com.souls.mapper.StudentInfoMapper;
import com.souls.po.StudentInfo;
import com.souls.vo.PageInfo;
import com.souls.vo.StudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    @Autowired
    //private DBConnection dbConnection;
    private StudentInfoMapper stuMapper;

    public StudentInfoVo login(String stuID, String pwd) {
        StudentInfoVo vo = new StudentInfoVo();
        StudentInfo stu = stuMapper.getStuByID(stuID);
        if (stu == null) {
            vo.setMessage("No this student!");
            return vo;
        }

        Long current = System.currentTimeMillis();
        if ((current - stu.getErrorTime()) > 24 * 60 * 60 * 1000)
            stu.setCount(0);
        if ((current - stu.getErrorTime()) < 24 * 60 * 60 * 1000 && stu.getCount() == 3) {
            vo.setMessage("This user is locked for 24 hours!");
            return vo;
        }

        if (pwd.equals(stu.getPassword())) {
            stuMapper.updateLoginInfo(stu.getStuID(), 0L, 0);
            vo.setStudentInfo(stu);
            vo.setMessage("OK");
            return vo;
        }
        stu.setCount(stu.getCount() + 1);
        if (stu.getCount() == 3) {
            vo.setMessage("Password input error for 3 times, locked for 24h");
        } else {
            vo.setMessage("Password error, you having " + (3 - stu.getCount() + " times"));
        }

        stuMapper.updateLoginInfo(stu.getStuID(), current, stu.getCount());
        return vo;
    }

    @Override
    public StudentInfo getStudentByID(String id) {
        return stuMapper.getStuByID(id);
    }

    @Override
    public List<StudentInfo> getAllStudent() {
        return stuMapper.getAllStudent();
    }

    public List<Object> queryAllStudent(PageInfo pageInfo){return stuMapper.queryAllStudent();}

    @Override
    public Boolean deleteStu(String id) {
        return stuMapper.deleteStu(id);
    }

    @Override
    public Boolean updateStu(StudentInfo studentInfo) {
        return stuMapper.updateStu(studentInfo);
    }

    @Override
    public Boolean addStudent(StudentInfo stu) {
        return stuMapper.addStudent(stu);
    }

    @Override
    public List<StudentInfo> selectStu(String name, Integer major) {
        return stuMapper.selectStu(name, major);
    }

    public String createStuID() {
        List<StudentInfo> list = this.getAllStudent();
        if (list.size() == 0) return "XH0001";
        StudentInfo studentInfo = list.get(list.size() - 1);
        String lastID = studentInfo.getStuID();
        Integer num = Integer.parseInt(lastID.substring(2)) + 1;
        String newID = String.valueOf(num);
        if (newID.length() < 4) {
            for (int i = 4 - newID.length(); i > 0; i--) {
                newID = "0" + newID;
            }
        }
        newID = "XH" + newID;
        return newID;
    }
    public Integer getMaxXH() {
        String maxXH=stuMapper.getMaxXH();
        int max=Integer.parseInt(maxXH.substring(2));
        return max;
    }
}
