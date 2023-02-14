package com.souls.controller;

import com.souls.po.StudentInfo;
import com.souls.service.StudentInfoService;
import com.souls.utils.XHPools;
import com.souls.vo.PageInfo;
import com.souls.vo.StudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class StudentController {
    @Autowired
    private StudentInfoService service;

    @PostMapping("/login.do")
    public String login(StudentInfo stu, HttpSession session) {
        StudentInfoVo vo = service.login(stu.getStuID(), stu.getPassword());
        if (vo.getStudentInfo() != null) {
            session.setAttribute("stu", vo.getStudentInfo());
            session.setAttribute("token", "ok");//设置登录状态
            return "ok";
        }
        session.setAttribute("msg", vo.getMessage());
        return vo.getMessage();
    }

    @GetMapping(value = "/view.do")
    public List<StudentInfo> viewAllStudent() {
        return  service.getAllStudent();
    }

    @PostMapping(value = "/view.do")
    public List<StudentInfo> viewStudent(String name, Integer major) {
        return service.selectStu(name, major);
    }

    @GetMapping(value = "/viewAll.do")
    public PageInfo getAllStu(PageInfo pageInfo){
        return (PageInfo) service.queryAllStudent(pageInfo);
    }

    @GetMapping(value = "/addStu.do")
    public StudentInfo getID(HttpSession session) {
        synchronized (XHPools.max){
            if (XHPools.max == 0){
                XHPools.max = service.getMaxXH();
            }
        }
        String id = XHPools.getXH(session.getId());
        System.out.println(id);
        StudentInfo info = new StudentInfo();
        info.setStuID(id);
        return info;
    }

    @PostMapping(value = "/addStu.do")
    public String addStu(StudentInfo stu,HttpSession session) {
//        String id = service.createStuID();
//        synchronized (XHPools.max){
//            if (XHPools.max == 0){
//                XHPools.max = service.getMaxXH();
//            }
//        }
//        String id = XHPools.getXH(session.getId());
//        stu.setStuID(id);
        return service.addStudent(stu).toString();
    }

    @GetMapping("/deleteStu.do")
    public String deleteStu(String stuID) {
        return service.deleteStu(stuID).toString();
    }

    @GetMapping(value = "/updateStu.do")
    public Object getStu(String stuID) {
        return service.getStudentByID(stuID);
    }

    @PostMapping(value = "/updateStu.do")
    public String updateStu(StudentInfo stu) {
        String s = service.updateStu(stu).toString();
        return s;
    }
}
