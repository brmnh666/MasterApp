package com.ying.administrator.masterappdemo.v3.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.AccessoriesNoEvent;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPreview;

public class ProdAdapter extends BaseQuickAdapter<WorkOrder.OrderProductModelsBean, BaseViewHolder> {


    private OnItemClickListener listener;
    private AccessoriesPictureAdapter accessoriesPictureAdapter;
    private List<String> list;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProdAdapter(int layoutResId, @Nullable List<WorkOrder.OrderProductModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final WorkOrder.OrderProductModelsBean item) {
        String name=item.getProductType()+"（编号："+item.getOrderProdcutID()+")";
        helper.setText(R.id.tv_product_name,name);
        helper.setText(R.id.tv_cate,"类别："+item.getSubCategoryName());
        helper.setText(R.id.tv_brand,"品牌："+item.getBrandName());
        helper.setText(R.id.tv_product_type,"型号："+item.getProdModelName());
        helper.setText(R.id.tv_memo,"服务要求："+item.getMemo());
        if (item.getProductState()==5){
            helper.setGone(R.id.iv_complete,true);
            helper.setGone(R.id.ll_complete,true);
            helper.setText(R.id.tv_result,"鉴定结果："+item.getRepairExplain());
            helper.setText(R.id.tv_bak,"服务说明："+item.getEndRemark());
            helper.setText(R.id.tv_barcode,"条形码："+item.getBarCode());
            if (item.getRepairExplain().isEmpty()){
                helper.setGone(R.id.tv_result,false);
            }else{
                helper.setGone(R.id.tv_result,true);
            }
            if (item.getEndRemark().isEmpty()){
                helper.setGone(R.id.tv_bak,false);
            }else{
                helper.setGone(R.id.tv_bak,true);
            }
            if (item.getBarCode().isEmpty()){
                helper.setGone(R.id.tv_barcode,false);
            }else{
                helper.setGone(R.id.tv_barcode,true);
            }
            list=item.getEndImgUrls();
            RecyclerView rv=helper.getView(R.id.rv_icon);
            accessoriesPictureAdapter = new AccessoriesPictureAdapter(R.layout.v3_item_accessories_picture, list);
            rv.setLayoutManager(new GridLayoutManager(mContext, 5));
            rv.setAdapter(accessoriesPictureAdapter);
            accessoriesPictureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    PhotoPreview.builder()
                            .setPhotos((ArrayList<String>) list)
                            .setShowDeleteButton(false)
                            .setCurrentItem(position)
                            .start((Activity) mContext);
                }
            });
        }else{
            helper.setGone(R.id.ll_complete,false);
            helper.setGone(R.id.iv_complete,false);
        }
        if(item.getAccessoryData()!=null){
            if (item.getAccessoryData().size()>0){
                helper.setGone(R.id.ll_acc,true);
                RecyclerView rv=helper.getView(R.id.rv_acc);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                AccNoAdapter adapter = new AccNoAdapter(R.layout.acc_no_item,item.getAccessoryData());
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        EventBus.getDefault().post(new AccessoriesNoEvent((AccNoAdapter) adapter,position));
                    }
                });
            }else{
                helper.setGone(R.id.ll_acc,false);
            }
        }else{
            helper.setGone(R.id.ll_acc,false);
        }
        if (item.getEndvideo().size()>0){
            helper.setGone(R.id.ll_video,true);
            Glide.with(mContext).load(item.getEndvideo().get(0)).into((ImageView) helper.getView(R.id.iv_video));
        }else{
            helper.setGone(R.id.ll_video,false);
        }
        helper.addOnClickListener(R.id.ll_course);
        helper.addOnClickListener(R.id.fl_video);

    }
}
