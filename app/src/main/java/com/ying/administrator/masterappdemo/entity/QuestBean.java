package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class QuestBean implements Serializable {


    /**
     * Id : 1
     * QuesID : 1
     * QuesType : 1
     * QuesCategory : 287
     * Content : 灭霸怎么死的
     * Case1 : A
     * Case2 : B
     * Case3 : C
     * Case4 : D
     * Case5 : E
     * Answer : A
     * Jieshi : null
     * UseAnswer : B
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
    private String Jieshi;
    private String UseAnswer;
    private String Value;
    private String IsUse;
    private String page;
    private String limit;
    private String Version;

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

    public String getJieshi() {
        return Jieshi;
    }

    public void setJieshi(String Jieshi) {
        this.Jieshi = Jieshi;
    }

    public String getUseAnswer() {
        return UseAnswer==null?"":UseAnswer;
    }

    public void setUseAnswer(String UseAnswer) {
        this.UseAnswer = UseAnswer;
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
