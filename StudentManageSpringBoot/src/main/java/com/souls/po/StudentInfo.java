package com.souls.po;

import org.springframework.stereotype.Component;

@Component
public class StudentInfo {
    private String stuID;
    private String stuName;
    private Integer majorID;
    private String enrollTime;
    private String sex;
    private String numID;
    private Double totalScore;
    private String password;
    private Integer count;
    private Long errorTime;
    private String icon;

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getMajorID() {
        return majorID;
    }

    public void setMajorID(Integer majorID) {
        this.majorID = majorID;
    }

    public String getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumID() {
        return numID;
    }

    public void setNumID(String numID) {
        this.numID = numID;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Long errorTime) {
        this.errorTime = errorTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "stuID='" + stuID + '\'' +
                ", stuName='" + stuName + '\'' +
                ", majorID=" + majorID +
                ", enrollTime='" + enrollTime + '\'' +
                ", sex='" + sex + '\'' +
                ", numID='" + numID + '\'' +
                ", totalScore=" + totalScore +
                ", password='" + password + '\'' +
                ", count=" + count +
                ", errorTime='" + errorTime + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
