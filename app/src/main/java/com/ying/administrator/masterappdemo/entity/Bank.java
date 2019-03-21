package com.ying.administrator.masterappdemo.entity;


import android.widget.ImageView;

public class Bank {
private String bankname;
private int imageView;

    public Bank(String bankname, int imageView) {
        this.bankname = bankname;
        this.imageView = imageView;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
