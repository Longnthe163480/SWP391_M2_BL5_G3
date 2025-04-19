/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author legen
 */
public class Mentor {
    private int id;
    private int accountid;
    private String name;
    private String address;
    private String phone;
    private Date birthday;
    private String sex;
    private String introduce;
    private String achievement;
    private String avatar;
    private float cost;
    //more information
    private float averageStar;
    
    public Mentor() {
    }

    public Mentor(int id, int accountid, String name, String address, String phone, Date birthday, String sex, String introduce, String achievement, String avatar, float cost) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.achievement = achievement;
        this.avatar = avatar;
        this.cost = cost;
    }

    public Mentor(int id, int accountid, String name, String address, String phone, Date birthday, String sex, String introduce, String achievement, String avatar, float cost, float averageStar) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
        this.introduce = introduce;
        this.achievement = achievement;
        this.avatar = avatar;
        this.cost = cost;
        this.averageStar = averageStar;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(float averageStar) {
        this.averageStar = averageStar;
    }
    
    
    
}
