package com.souls.controller;

import com.souls.po.StudentInfo;
import com.souls.service.StudentInfoService;
import com.souls.utils.XHPools;
import com.souls.vo.PageInfo;
import com.souls.vo.StudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class StudentController {
    @Autowired
    private StudentInfoService service;

    @RequestMapping("/login.do")
    @ResponseBody
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

    @RequestMapping(value = "/view.do", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentInfo> viewAllStudent() {
        return  service.getAllStudent();
    }

    @RequestMapping(value = "/view.do", method = RequestMethod.POST)
    @ResponseBody
    public List<StudentInfo> viewStudent(String name, Integer major) {
        return service.selectStu(name, major);
    }

    @RequestMapping(value = "/viewAll.do")
    @ResponseBody
    public PageInfo getAllStu(PageInfo pageInfo){
        return (PageInfo) service.queryAllStudent(pageInfo);
    }

    @RequestMapping(value = "/addStu.do", method = RequestMethod.GET)
    @ResponseBody
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

    @RequestMapping(value = "/addStu.do", method = RequestMethod.POST)
    @ResponseBody
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

    @RequestMapping("/deleteStu.do")
    @ResponseBody
    public String deleteStu(String stuID) {
        return service.deleteStu(stuID).toString();
    }

    @RequestMapping(value = "/updateStu.do", method = RequestMethod.GET)
    @ResponseBody
    public Object getStu(String stuID) {
        return service.getStudentByID(stuID);
    }

    @RequestMapping(value = "/updateStu.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateStu(StudentInfo stu) {
        String s = service.updateStu(stu).toString();
        return s;
    }
}
