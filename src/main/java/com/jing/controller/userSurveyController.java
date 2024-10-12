package com.jing.controller;

import com.jing.pojo.UserSurvey;
import com.jing.service.UserSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class userSurveyController {

    @Autowired
    private UserSurveyService userSurveyService;

    @RequestMapping(value = "/userSurvey", method = RequestMethod.POST)
    public String userSurvey(HttpServletRequest request) {
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String price = request.getParameter("price");
        String hobby = request.getParameter("hobby");
        String consideration = request.getParameter("consideration");
        UserSurvey userSurvey = new UserSurvey();
        userSurvey.setAge(age);
        userSurvey.setGender(gender);
        userSurvey.setPrice(price);
        userSurvey.setHobby(hobby);
        userSurvey.setConsideration(consideration);
        int surveyCoutnt = userSurveyService.insertUserSurvey(userSurvey);

        System.out.println("login....");
        return "login success";
    }


    @RequestMapping(value = "/userSurveyAnalysis", method = RequestMethod.GET)
    public String userSurveyAnalysis(HttpServletRequest request) {
        List<UserSurvey> userSurveys = userSurveyService.selectList();

        System.out.println("login....");
        return "login success";
    }


}
