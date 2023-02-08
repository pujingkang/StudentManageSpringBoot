package com.souls.controller;

import com.souls.po.MarkInfo;
import com.souls.service.MarkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private MarkInfoService service;

    @RequestMapping("/viewScore.do")
    @ResponseBody
    public List<MarkInfo> viewScore() {

        return service.getAllMarkInfo();
    }

    @RequestMapping("/addScore.do")
    @ResponseBody
    public String addScore(String courseID, String stuID, String baseScore, String testScore) {
        Integer id = service.createID();
        MarkInfo markInfo = new MarkInfo();
        markInfo.setId(id);
        markInfo.setCourseID(Integer.valueOf(courseID));
        markInfo.setStuID(stuID);
        double base = Double.parseDouble(baseScore);
        double test = Double.parseDouble(testScore);
        markInfo.setBaseScore(base);
        markInfo.setTestScore(test);
        markInfo.setFinalScore(base * 0.4 + test * 0.6);
        return service.addScore(markInfo).toString();
    }

    @RequestMapping("/deleteScore.do")
    @ResponseBody
    protected String deleteScore(String id) {

        return service.deleteScore(id).toString();
    }
}
