package com.ying.administrator.masterappdemo.entity;

import com.ying.administrator.masterappdemo.v3.adapter.AccNoAdapter;

import java.io.Serializable;

public class AccessoriesNoEvent implements Serializable {

    AccNoAdapter adapter;
    int position;

    public AccessoriesNoEvent(AccNoAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
    }

    public AccNoAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AccNoAdapter adapter) {
        this.adapter = adapter;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
