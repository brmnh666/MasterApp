package com.ying.administrator.masterappdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyPackageAdapter;

import java.util.List;

/**
 * Data:2019/7/30
 * Time:15:22
 * author:ying
 **/
public class MyPackagePopup extends BottomPopupView {
    private MyPackageAdapter myPackageAdapter;
    private List<Accessory> list;
    private Context mcontext;
    private RecyclerView mRvpopup;

    public MyPackageAdapter getMyPackageAdapter() {
        if (myPackageAdapter==null){
            myPackageAdapter=new MyPackageAdapter(R.layout.item_mypackage,list);
        }
        return myPackageAdapter;
    }

    public MyPackagePopup(@NonNull Context context, List<Accessory> list, Context mcontext) {
        super(context);
        this.list=list;
        this.mcontext=mcontext;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mRvpopup=findViewById(R.id.rv_popup);
        myPackageAdapter=new MyPackageAdapter(R.layout.item_mypackage,list);
        mRvpopup.setLayoutManager(new LinearLayoutManager(mcontext));
        mRvpopup.setAdapter(myPackageAdapter);

    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext())*.6f);
    }


}
