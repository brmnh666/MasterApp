package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.MySkills;

import java.util.List;

public class StudyAdapter extends BaseQuickAdapter<MySkills,BaseViewHolder> {

    public StudyAdapter(int layoutResId, @Nullable List<MySkills> data) {
        super(R.layout.item_kills, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MySkills item) {
        helper .setText(R.id.tv_kill_name,item.getCategory().getFCategoryName())
                .setText(R.id.tv_kill_detail_one,item.getSimple())
                .setText(R.id.tv_kill_detail_two,item.getDetail());
        helper.setGone(R.id.iv_choose,false);

    }
}
