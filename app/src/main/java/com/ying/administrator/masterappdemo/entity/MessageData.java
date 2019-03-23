package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class MessageData<T> implements Serializable {



    private T Item1;
    private int Item2;

    public T getItem1() {
        return Item1;
    }

    public void setItem1(T item1) {
        Item1 = item1;
    }

    public int getItem2() {
        return Item2;
    }

    public void setItem2(int item2) {
        Item2 = item2;
    }
}
