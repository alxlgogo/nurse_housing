package com.jing.service;

import com.jing.mapper.UserSurveyMapper;
import com.jing.pojo.User;
import com.jing.pojo.UserSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userSurveyService")
public class UserSurveyService {
    @Autowired
    private UserSurveyMapper userSurveyMapper;
    public int insertUserSurvey(UserSurvey userSurvey) {
        int insert = userSurveyMapper.insert(userSurvey);
        return insert;
    }

    public List<UserSurvey> selectList() {
        List<UserSurvey> userSurvey = userSurveyMapper.selectList(null);
        return userSurvey;
    }
}
