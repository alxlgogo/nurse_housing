package com.jing.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jing.pojo.Role;
import com.jing.pojo.User;
import com.jing.pojo.UserRole;
import com.jing.service.RoleService;
import com.jing.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.RConnectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private com.jing.service.UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        userService.loadUserByUsername(username);
        System.out.println("login....");
        return "login success";
    }

    @RequestMapping("/register")
    public String register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(user.getPassword().trim());
        user.setPassword(password);
        user.setUserType("normal");
        userService.insertUser(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserName(user.getUserName());
        userRole.setRoleId(2);
        userRoleService.insertUserRole(userRole);

        System.out.println("register....");
        return "/registerSuccess";
    }

    @Secured({"ROLE_Admin", "ROLE_Normal", "ROLE_VIP"})
    @RequestMapping("/selectUser")
    public String selectUser(Model model) {
        List<User> users = userService.selectList(null);
        sendUserInfoToPage(model);

        model.addAttribute("users", users);
        System.out.println("selectUsers....");
        return "pages/userList";
    }


    @RequestMapping("/show")
    public String show(Model model) throws Exception {

//        RConnection rConnection = RConnectionUtils.getRConnectionUtils().getConnection();
        RConnectionUtils.ageStay_time("");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> users = userService.selectList(null);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Date checkinTime = user.getCheckinTime();
            Date checkoutTime = user.getCheckoutTime();
            long diff = checkoutTime.getTime() - checkinTime.getTime();
            long stay_time = diff / (24 * 60 * 60 * 1000 * 365);

            String birthday = user.getBirthday();
            Date birthdayDate = format.parse(birthday);
            long diff2 =  checkoutTime.getTime()- birthdayDate.getTime();
            long age = diff2 / (24 * 60 * 60 * 1000 * 365);
            System.out.println();

        }


        System.out.println("show....");
        return "chart";
    }


    @Secured({"ROLE_Admin", "ROLE_Normal", "ROLE_VIP"})
    @RequestMapping("/userEdit")
    public String userEdit(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String username = userDetails.getUsername();
        User user = userService.selectOne(username);
        model.addAttribute("users", user);

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("authorities", authorities);

        System.out.println("userEdit....");
        return "pages/userEdit";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        User user = userService.selectOne(username);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate1 = null;
        Date checkOutDate1 = null;
        try {
            checkInDate1 = sdf.parse(checkInDate);
            checkOutDate1 = sdf.parse(checkOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCheckinTime(checkInDate1);
        user.setCheckoutTime(checkOutDate1);

        int updateUser = userService.updateByID(user);
        if (updateUser == 1) {
            System.out.println("updateUser success");
        } else {
            System.out.println("updateUser failure");
        }

        return "redirect:/userEdit";
    }


    private void sendUserInfoToPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("authorities", authorities);
    }

    @RequestMapping("/home")
    public String home(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        System.out.println("selectUsers....");
        return "pages/home";
    }

    @RequestMapping("/pay")
    public String pay() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.selectOne(username);
        Long userId = user.getId();

        UpdateWrapper<UserRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        UserRole userRole = new UserRole();
        userRole.setRoleId(3);
        userRoleService.updateUserRole(userRole, updateWrapper);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        List<Role> roles = roleService.selectRoleByUsername(username);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            stringBuffer.append("ROLE_" + role.getName());
            if (i < roles.size() - 1) {
                stringBuffer.append(",");
            }
        }
        SimpleGrantedAuthority role_new_role = new SimpleGrantedAuthority(stringBuffer.toString());
        updatedAuthorities.add(role_new_role);//add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        //update user chick_in time
        //update user user_type


        return "redirect:/selectUser";
    }


}
