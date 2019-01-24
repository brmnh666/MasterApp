package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jmf.addsubutils.AddSubUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AccessoryData;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAccessoryActivity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements PendingOrderContract.View {

   private  Accessory accessory;
   private  FAccessory fAccessory;
    private List<Accessory> mList;
    private Map<Integer,FAccessory> map;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter ;
      private Button btn_addaccessory;

    private boolean[] ischeck;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList= new ArrayList<>();
        map=new HashMap<>();
        mRecyclerView = findViewById(R.id.addaccessory_recyclerview);
        btn_addaccessory=findViewById(R.id.btn_addaccessory);
        mPresenter.GetFactoryAccessory();
        adapter = new MyRecyclerAdapter(R.layout.item_addaccessory,mList);


        //设置线性布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        ischeck=new boolean[1000];
         adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
             @Override
             public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                 AddSubUtils viewadd = (AddSubUtils)adapter.getViewByPosition(mRecyclerView,position,R.id.addaccessory_addsubutil);
                 ImageView img_ac_select = (ImageView)adapter.getViewByPosition(mRecyclerView, position, R.id.img_ac_select); //选中图片
                 ImageView img_ac_unselect = (ImageView)adapter.getViewByPosition(mRecyclerView, position, R.id.img_ac_unselect);//未选中图片

                 switch (view.getId()){

                     case R.id.img_ac_unselect:
                     case R.id.img_ac_select:
                         if (ischeck[position]==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                             viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                             img_ac_unselect.setVisibility(View.INVISIBLE);
                             img_ac_select.setVisibility(View.VISIBLE);
                             ischeck[position]=true;
                             accessory=(Accessory)adapter.getItem(position);
                             fAccessory=new FAccessory();
                             fAccessory.setFAccessoryName(accessory.getAccessoryName());
                             fAccessory.setDiscountPrice(accessory.getAccessoryPrice()*viewadd.getNumber());

                             map.put(position,fAccessory);


                         }else {
                             viewadd.setVisibility(View.INVISIBLE); //数量选择器消失
                             img_ac_unselect.setVisibility(View.VISIBLE);
                             img_ac_select.setVisibility(View.INVISIBLE);
                             ischeck[position]=false;
                             map.remove(position);

                         }


                         break;





                 }
             }
         });




        btn_addaccessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Log.d("asasasas",qList.toString());
                for (int key : map.keySet()){
                    System.out.println("选择了"+map.get(key).getFAccessoryName()+"配件"+"  价格"+map.get(key).getDiscountPrice());

                }



            }
        });
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_addaccessory;
    }

    @Override
    protected void initData() {




    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {



    }

    @Override
    public void GetFactoryAccessory(BaseResult<AccessoryData<Accessory>> baseResult) {
       switch (baseResult.getStatusCode()){
           case 200:

               mList.addAll(baseResult.getData().getItem1());
               adapter.setNewData(mList);
               Log.d("mlistmlist",mList.toString());
               break;
               default:
                   Toast.makeText(AddAccessoryActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                   break;

       }


    }
}
