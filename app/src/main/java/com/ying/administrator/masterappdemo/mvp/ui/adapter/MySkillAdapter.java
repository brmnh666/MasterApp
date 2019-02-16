package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.MySkills;

import java.util.List;

public class MySkillAdapter extends BaseQuickAdapter<MySkills,BaseViewHolder> {

    public MySkillAdapter(int layoutResId, @Nullable List<MySkills> data) {
        super(R.layout.item_kills, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MySkills item) {
        helper.setOnClickListener(R.id.fl_drop_down, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.getView(R.id.tv_kill_detail_one).getVisibility()==View.VISIBLE){
                    helper.getView(R.id.tv_kill_detail_one).setVisibility(View.GONE);
                    helper.getView(R.id.tv_kill_detail_two).setVisibility(View.VISIBLE);
                }else{
                    helper.getView(R.id.tv_kill_detail_one).setVisibility(View.VISIBLE);
                    helper.getView(R.id.tv_kill_detail_two).setVisibility(View.GONE);
                }
            }
        });
        helper.setOnClickListener(R.id.iv_drop_down, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.getView(R.id.tv_kill_detail_one).getVisibility()==View.VISIBLE){
                    helper.getView(R.id.tv_kill_detail_one).setVisibility(View.GONE);
                    helper.getView(R.id.tv_kill_detail_two).setVisibility(View.VISIBLE);
                }else{
                    helper.getView(R.id.tv_kill_detail_one).setVisibility(View.VISIBLE);
                    helper.getView(R.id.tv_kill_detail_two).setVisibility(View.GONE);
                }
            }
        });
    }
}
