package com.ying.administrator.masterappdemo.entity;

public class GrabSheet_Entity {
    private String time; //传来的时间
    private String status; //状态  维修和 安装
    private String phone;  //电话
    private String authentication;//认证
    private String factory_online_status; //工厂在线状态
    private String head_url;//头像
    private String username; //用户名
    private String distance; //距离
    private String reason;//故障原因
    private String address;//地址

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getFactory_online_status() {
        return factory_online_status;
    }

    public void setFactory_online_status(String factory_online_status) {
        this.factory_online_status = factory_online_status;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
