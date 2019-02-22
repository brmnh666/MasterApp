package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class MySkills implements Serializable {
    private boolean selected;
    private Category Category;
    private List<Category> categoryArrayList;
    private String simple="";
    private String detail="";
    private String NodeIds="";

    public MySkills(boolean selected, Category category, List<Category> categoryArrayList) {
        this.selected = selected;
        Category = category;
        this.categoryArrayList = categoryArrayList;

    }

    public String getNodeIds() {
        return NodeIds;
    }

    public void setNodeIds(String nodeIds) {
        NodeIds = nodeIds;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }

    public List<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(List<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
        for (int i = 0; i < categoryArrayList.size(); i++) {
            detail+=categoryArrayList.get(i).getFCategoryName()+"/";
            NodeIds+=categoryArrayList.get(i).getFCategoryID()+",";
        }
        if (detail.contains("/")){
            detail=detail.substring(0,detail.lastIndexOf("/"));
        }
        if (NodeIds.contains(",")){
            NodeIds=NodeIds.substring(0,NodeIds.lastIndexOf(","));
        }
        if (detail.length()>50){
            simple=detail.substring(0,50);
        }else{
            simple=detail;
        }
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
