package com.ying.administrator.masterappdemo.entity;

import java.io.File;
import java.io.Serializable;

public class AccessoriesName implements Serializable {

    String name;
    File pic;

    public AccessoriesName(String name, File pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getPic() {
        return pic;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }
}
