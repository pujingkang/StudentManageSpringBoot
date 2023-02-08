package com.souls.vo;

import com.souls.po.StudentInfo;

public class StudentInfoVo {
    private StudentInfo studentInfo;
    private String message;

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
