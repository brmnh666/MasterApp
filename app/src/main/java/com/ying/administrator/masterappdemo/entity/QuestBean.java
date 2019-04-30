package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class QuestBean implements Serializable {

    /**
     * Id : 16
     * QuesID : 16
     * QuesType : 1
     * QuesCategory : 311
     * Content : 测试
     * Case1 : 测试1
     * Case2 : 测试2
     * Case3 : null
     * Case4 : null
     * Case5 : null
     * Answer : 测试1
     * Value : 5
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String QuesID;
    private String QuesType;
    private String QuesCategory;
    private String Content;
    private String Case1;
    private String Case2;
    private String Case3;
    private String Case4;
    private String Case5;
    private String Answer;
    private String MyAnswer;
    private String Value;
    private String IsUse;
    private String page;
    private String limit;
    private String Version;

    public String getMyAnswer() {
        return MyAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        MyAnswer = myAnswer;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getQuesID() {
        return QuesID;
    }

    public void setQuesID(String QuesID) {
        this.QuesID = QuesID;
    }

    public String getQuesType() {
        return QuesType;
    }

    public void setQuesType(String QuesType) {
        this.QuesType = QuesType;
    }

    public String getQuesCategory() {
        return QuesCategory;
    }

    public void setQuesCategory(String QuesCategory) {
        this.QuesCategory = QuesCategory;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getCase1() {
        return Case1;
    }

    public void setCase1(String Case1) {
        this.Case1 = Case1;
    }

    public String getCase2() {
        return Case2;
    }

    public void setCase2(String Case2) {
        this.Case2 = Case2;
    }

    public String getCase3() {
        return Case3;
    }

    public void setCase3(String Case3) {
        this.Case3 = Case3;
    }

    public String getCase4() {
        return Case4;
    }

    public void setCase4(String Case4) {
        this.Case4 = Case4;
    }

    public String getCase5() {
        return Case5;
    }

    public void setCase5(String Case5) {
        this.Case5 = Case5;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
