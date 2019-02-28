package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province implements Serializable {


    /**
     * Id : string
     * code : string
     * name : string
     * IsUse : string
     * Version : 0
     */

    private String Id;
    private String code;
    private String name;
    private String IsUse;
    private String Version;
    private List<City> cities = new ArrayList<City>();

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
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

