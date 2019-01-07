package com.ying.administrator.masterappdemo.common;


public interface DefineView {
    void initView();      //初始化界面元素
    void initValidata();  //初始化变量
    void initListener();  //初始化监听器
    void bindData();      //绑定数据
}
