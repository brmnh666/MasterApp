package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class MySkills implements Serializable {
    private String id;
    private String skillNmae;
    private String skillType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillNmae() {
        return skillNmae;
    }

    public void setSkillNmae(String skillNmae) {
        this.skillNmae = skillNmae;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }
}
