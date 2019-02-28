package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class City implements Serializable {


    /**
     * Id : string
     * code : string
     * name : string
     * parentcode : string
     * IsUse : string
     * Version : 0
     */

    private String Id;
    private String code;
    private String name;
    private String parentcode;
    private String IsUse;
    private String Version;

    private List<Area> counties = new ArrayList<Area>();

    public List<Area> getCounties() {
        return counties;
    }

    public void setCounties(List<Area> counties) {
        this.counties = counties;
    }
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}

