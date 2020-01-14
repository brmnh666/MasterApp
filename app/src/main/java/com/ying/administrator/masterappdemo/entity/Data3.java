package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Data3<T> implements Serializable {


    /**
     * Item1 : true
     * Item2 : 17
     */

    private int Item1;
    private T Item2;

    public int getItem1() {
        return Item1;
    }

    public void setItem1(int item1) {
        Item1 = item1;
    }

    public T getItem2() {
        return Item2;
    }

    public void setItem2(T item2) {
        Item2 = item2;
    }
}
