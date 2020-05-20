package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Phone2 implements Serializable {
    private List<Phone> array;

    public List<Phone> getArray() {
        return array;
    }

    public void setArray(List<Phone> array) {
        this.array = array;
    }
}
