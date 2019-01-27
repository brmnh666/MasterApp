package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class GetFactoryData<T> {
    private List<T> Item1;
    private String Item2;


    public List<T> getItem1() {
        return Item1;
    }

    public void setItem1(List<T> item1) {
        Item1 = item1;
    }

    public String getItem2() {
        return Item2;
    }

    public void setItem2(String item2) {
        Item2 = item2;
    }
}
