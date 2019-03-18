package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryData implements Serializable {

    /**
     * code : 0
     * msg : success
     * count : 8
     * data : [{"Id":250,"FCategoryID":250,"FCategoryName":"冰箱","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":262,"FCategoryID":262,"FCategoryName":"冷柜","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":266,"FCategoryID":266,"FCategoryName":"酒柜","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":268,"FCategoryID":268,"FCategoryName":"除湿机","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":270,"FCategoryID":270,"FCategoryName":"家用制冰机","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":275,"FCategoryID":275,"FCategoryName":"酿酒机压缩机","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":276,"FCategoryID":276,"FCategoryName":"热饮柜","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0},{"Id":281,"FCategoryID":281,"FCategoryName":"洗衣机","ParentID":999,"ParentName":null,"IsUse":"Y","InitPrice":0,"page":1,"limit":999,"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<Category> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Category> getData() {
        return data==null?new ArrayList<Category>():data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

}
