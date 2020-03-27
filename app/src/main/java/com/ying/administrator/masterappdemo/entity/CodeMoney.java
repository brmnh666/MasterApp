package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class CodeMoney implements Serializable {

    /**
     * Id : 27
     * CodeId : 27
     * Code : ProtectionCost
     * CodeName : 保外单收取的费用
     * CodeValue : 10
     * Description : 就是说，师傅接保外单，要扣除师傅多少钱
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int CodeId;
    private String Code;
    private String CodeName;
    private String CodeValue;
    private String Description;
    private String IsUse;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCodeId() {
        return CodeId;
    }

    public void setCodeId(int CodeId) {
        this.CodeId = CodeId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String CodeName) {
        this.CodeName = CodeName;
    }

    public String getCodeValue() {
        return CodeValue;
    }

    public void setCodeValue(String CodeValue) {
        this.CodeValue = CodeValue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
