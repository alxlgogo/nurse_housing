package com.jing.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jing.exception.UserExceptionHandler;
import com.mysql.cj.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String birthday;
    private String userType;
    private String registerTime;
    private String gender;
    private Date checkinTime;
    private Date checkoutTime;

    private String firstName;
    private String lastName;


    public static void validateUsername(String userName) throws UserExceptionHandler {
        if (StringUtils.isNullOrEmpty(userName)) {
            throw new UserExceptionHandler("userName should not be null");
        } else if (userName.length() < 2) {
            throw new UserExceptionHandler("the minimum of username should be grater 2");
        } else if (userName.length() > 30) {
            throw new UserExceptionHandler("the maximum of username should be less 30");
        }
    }

    public static void validatePassword(String password) throws UserExceptionHandler {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new UserExceptionHandler("email should not be null");
        } else if (password.length() < 3) {
            throw new UserExceptionHandler("the minimum of password should be grater 2");
        } else if (password.length() > 20) {
            throw new UserExceptionHandler("the maximum of password should be less 30");
        }
    }

    public static void validateEmail(String email) throws UserExceptionHandler {
        if (StringUtils.isNullOrEmpty(email)) {
            throw new UserExceptionHandler("email should not be null");
        } else if (!email.contains("@")) {
            throw new UserExceptionHandler("email should contain @");
        } else if (email.length() < 10) {
            throw new UserExceptionHandler("the minimum of email should be grater 10");
        } else if (email.length() > 30) {
            throw new UserExceptionHandler("the maximum of email should be less 30");
        }
    }


    @TableField(exist = false)
    private List<Permission> permissionList;
    @TableField(exist = false)
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userType='" + userType + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", gender='" + gender + '\'' +
                ", checkinTime=" + checkinTime +
                ", checkoutTime=" + checkoutTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", permissionList=" + permissionList +
                ", roleList=" + roleList +
                '}';
    }
}
