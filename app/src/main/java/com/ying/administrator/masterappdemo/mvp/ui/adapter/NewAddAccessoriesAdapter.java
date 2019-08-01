package com.ying.administrator.masterappdemo.mvp.ui.adapter;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import java.util.List;

public class NewAddAccessoriesAdapter extends BaseQuickAdapter<Accessory,BaseViewHolder> {
    public NewAddAccessoriesAdapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {
        helper.setText(R.id.tv_ac_name,item.getAccessoryName());
        helper.addOnClickListener(R.id.img_add);
        helper.addOnLongClickListener(R.id.img_add);

    }
}
