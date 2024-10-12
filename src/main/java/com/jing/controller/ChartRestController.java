package com.jing.controller;


import com.jing.pojo.User;
import com.jing.pojo.UserExpectancy;
import com.jing.utils.JDBCutils;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.RConnectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ChartRestController {
    @Autowired
    private com.jing.service.UserService userService;

    @RequestMapping("/expectancyAge")
    public Object expectancyAge(Model model, HttpServletRequest request) {
        UserExpectancy userExpectancy = new UserExpectancy();
        try {
            int expectancyAge = Integer.parseInt(request.getParameter("expectancyAge"));
            String gender = request.getParameter("gender");
            int year = RConnectionUtils.expectancyAge(expectancyAge, gender);
            userExpectancy.setYear(year);
        } catch (RserveException e) {
            userExpectancy.setMsg("Error expectancyAge");
            e.printStackTrace();
        }
        return userExpectancy;
    }

    @RequestMapping("/generateAvgLifeYear")
    public Object generateAvgLifeYear(Model model, HttpServletRequest request) {
        String gender = request.getParameter("gender");
        UserExpectancy userExpectancy = new UserExpectancy();
        try {
            String fileName = RConnectionUtils.avgLife_year(gender);
            userExpectancy.setFileName(fileName);
        } catch (RserveException e) {
            e.printStackTrace();
            userExpectancy.setMsg("Error generateAvgLifeYear");
        }
        return userExpectancy;
    }

    @RequestMapping("/generateLifeExpectancy")
    public Object generateLifeExpectancy(Model model, HttpServletRequest request) {
        UserExpectancy userExpectancy = new UserExpectancy();
        try {
            String gender = request.getParameter("gender");
            String year = request.getParameter("year");
            String fileName = RConnectionUtils.lifeExpectancy(gender, year);
            userExpectancy.setFileName(fileName);
        } catch (RserveException e) {
            e.printStackTrace();
            userExpectancy.setMsg("Error generateLifeExpectancy");
        }
        return userExpectancy;
    }


    @RequestMapping("/updateMaleCheckoutTime")
    public Object updateAge(Model model, HttpServletRequest request) throws ParseException {
        boolean update = false;
        String gender = request.getParameter("gender");
        String action = request.getParameter("action");
        String sql = "";
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = JDBCutils.getConnection();
            if ("male".equals(gender) && "increase".equals(action)) {
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2006-03-01 09:00:00.0' WHERE (`id` = '1371579841576456194');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2007-03-01 09:00:00.0' WHERE (`id` = '1371580061647392770');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2008-03-01 09:00:00.0' WHERE (`id` = '1371580202836054018');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2012-03-01 09:00:00.0' WHERE (`id` = '1371580869868802050');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2013-03-01 09:00:00.0' WHERE (`id` = '1371581194998665218');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2014-03-01 09:00:00.0' WHERE (`id` = '1371581469964652545');");
                preparedStatement.executeUpdate();
            }
            if ("male".equals(gender) && "rest".equals(action)) {
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2004-03-01 09:00:00.0' WHERE (`id` = '1371579841576456194');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2005-03-01 09:00:00.0' WHERE (`id` = '1371580061647392770');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2006-03-01 09:00:00.0' WHERE (`id` = '1371580202836054018');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2010-03-01 09:00:00.0' WHERE (`id` = '1371580869868802050');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2011-03-01 09:00:00.0' WHERE (`id` = '1371581194998665218');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `checkout_time` = '2012-03-01 09:00:00.0' WHERE (`id` = '1371581469964652545');");
                preparedStatement.executeUpdate();
            }
            update = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    @RequestMapping("/updateFemaleCheckoutTime")
    public Object restAge(Model model, HttpServletRequest request) throws ParseException {
        boolean update = false;
        String gender = request.getParameter("gender");
        String action = request.getParameter("action");
        String sql = "";
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = JDBCutils.getConnection();
            if ("female".equals(gender) && "increase".equals(action)) {
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1928-07-13 00:00:00' WHERE (`id` = '1371585552796635138');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1928-03-22 00:00:00' WHERE (`id` = '1371585664260263937');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1929-08-25 00:00:00' WHERE (`id` = '1371586187818455041');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1931-04-24 00:00:00' WHERE (`id` = '1371586470132862978');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1932-05-13 00:00:00' WHERE (`id` = '1371586645417021441');");
                preparedStatement.executeUpdate();
            }
            if ("female".equals(gender) && "rest".equals(action)) {
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1926-07-13 00:00:00' WHERE (`id` = '1371585552796635138');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1926-03-22 00:00:00' WHERE (`id` = '1371585664260263937');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1927-08-25 00:00:00' WHERE (`id` = '1371586187818455041');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1929-04-24 00:00:00' WHERE (`id` = '1371586470132862978');");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("UPDATE `nurse_housing`.`user` SET `birthday` = '1930-05-13 00:00:00' WHERE (`id` = '1371586645417021441');");
                preparedStatement.executeUpdate();
            }
            update = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

//    public void test2(){
//        String gender = request.getParameter("gender");
//        int age = Integer.parseInt(request.getParameter("age"));
//        age = 1;
//        User user = new User();
//        user.setGender(gender);
//        List<Long> maleIds = new ArrayList<>();
//        maleIds.add(Long.parseLong("1371579841576456194"));
//        maleIds.add(Long.parseLong("1371580061647392770"));
//        maleIds.add(Long.parseLong("1371580202836054018"));
//        maleIds.add(Long.parseLong("1371580869868802050"));
//        maleIds.add(Long.parseLong("1371581194998665218"));
//        maleIds.add(Long.parseLong("1371581469964652545"));
//
//        String str = "2005-03-01 09:00:00.0";
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = format.parse(str);
//
//
//        List<User> users = userService.selectList(user);
//        for (int i = 0; i < users.size(); i++) {
//            User user1 = users.get(i);
//            Date checkoutTime = user1.getCheckoutTime();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(checkoutTime);
//            Long id = user1.getId();
//            int year = calendar.get(Calendar.YEAR);
//            for (Long maleId : maleIds) {
//                if (id == maleId) {
//                    calendar.add(Calendar.YEAR, age);
//                }
//            }
//            Date newDate = calendar.getTime();
//            user1.setCheckoutTime(newDate);
//            userService.updateByID(user1);
//            System.out.println();
//        }
//    }


}
