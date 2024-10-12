package com.jing.pojo;

import java.io.Serializable;

public class UserSurvey implements Serializable {
    private int id;
    private String gender;
    private String age;
    private String price;
    private String hobby;
    private String consideration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getConsideration() {
        return consideration;
    }

    public void setConsideration(String consideration) {
        this.consideration = consideration;
    }

    @Override
    public String toString() {
        return "UserSurvey{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", price='" + price + '\'' +
                ", hobby='" + hobby + '\'' +
                ", consideration='" + consideration + '\'' +
                '}';
    }
}
