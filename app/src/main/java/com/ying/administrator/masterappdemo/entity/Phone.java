package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Phone implements Serializable {
    private String name;
    private String Telephone;

    public Phone(String name, String telephone) {
        this.name = name;
        Telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
