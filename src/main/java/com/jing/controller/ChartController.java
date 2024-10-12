package com.jing.controller;

import com.jing.pojo.User;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChartController {
    @Autowired
    private com.jing.service.UserService userService;

    @RequestMapping("/chart")
    public String chart(Model model) {
        User user = new User();
        user.setGender(null);
        List<User> users = userService.selectList(user);
        JSONArray jsonarray = JSONArray.fromObject(users);
        String jsonstr = jsonarray.toString();
        model.addAttribute("users", jsonstr);
        return "chart";
    }

}
