package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Data<T> implements Serializable {


    /**
     * Item1 : true
     * Item2 : 17
     */

    private boolean Item1;
    private T Item2;

    public boolean isItem1() {
        return Item1;
    }

    public void setItem1(boolean item1){
            Item1 = item1;
    }

    public T getItem2() {
        return Item2;
    }

    public void setItem2(T item2) {
        Item2 = item2;
    }
}
