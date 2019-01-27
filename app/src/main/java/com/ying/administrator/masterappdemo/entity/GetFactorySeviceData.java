package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class GetFactorySeviceData<T> {
    private boolean Item1;
    private List<T> Item2;

    public boolean isItem1() {
        return Item1;
    }

    public void setItem1(boolean item1) {
        Item1 = item1;
    }

    public List<T> getItem2() {
        return Item2;
    }

    public void setItem2(List<T> item2) {
        Item2 = item2;
    }
}
