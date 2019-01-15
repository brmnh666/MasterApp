package com.ying.administrator.masterappdemo.mvp.service.view;

import com.ying.administrator.masterappdemo.entity.Order_Entity;

public interface GetOrderInfoListView extends View {
    void onSuccess(Order_Entity mOrder_Entity);
    void onError(String result);
       /*
    可以看到在里面定义两个方法，一个onSuccess，如果presenter请求成功，将向该方法传入请求下来的实体类，
    也就是Book，view拿到这个数据实体类后，就可以进行关于这个数据的展示或其他的一些操作。
    如果请求失败，就会向这个view传入失败信息，你可以弹个Toast来提示请求失败。
    通常这两个方法比较常用，当然你可以根据项目需要来定义一些其他的方法。
    * */
}
