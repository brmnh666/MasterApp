package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.v3.bean.CourseResult;

import java.util.List;

public class CourseAdapter extends BaseQuickAdapter<CourseResult.DataBeanX.DataBean.FactoryDataBean, BaseViewHolder> {

    public CourseAdapter(int layoutResId, @Nullable List<CourseResult.DataBeanX.DataBean.FactoryDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseResult.DataBeanX.DataBean.FactoryDataBean item) {
       helper.setText(R.id.tv_name,item.getTitle());
    }
}
