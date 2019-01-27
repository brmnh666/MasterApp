package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyRecyclerAdapter;
import com.ying.administrator.masterappdemo.widget.adderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAccessoryActivity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements PendingOrderContract.View  {

      private  Accessory accessory;
      private  FAccessory fAccessory;
      private List<Accessory> mList;
      private Map<Integer,FAccessory> map;
      private List<FAccessory> fList;//用于存放返回的数据
      private RecyclerView mRecyclerView;
      private MyRecyclerAdapter adapter ;
      private TextView btn_addaccessory; //提交

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
       //Log.d("mlistsize", String.valueOf(mList.size()));

         adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
             @Override
             public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                  //final AddSubUtils viewadd = (AddSubUtils)adapter.getViewByPosition(mRecyclerView,position,R.id.addaccessory_addsubutil);
                 ImageView img_ac_select = (ImageView)adapter.getViewByPosition(mRecyclerView, position, R.id.img_ac_select); //选中图片
                 ImageView img_ac_unselect = (ImageView)adapter.getViewByPosition(mRecyclerView, position, R.id.img_ac_unselect);//未选中图片
                 adderView adderView =(adderView)adapter.getViewByPosition(mRecyclerView, position, R.id.adderView);

                 //View view_addaccessory=adapter.getViewByPosition(mRecyclerView,position,R.id.view_addaccessory);
                 //final EditText et_input_addaccessory = (EditText)adapter.getViewByPosition(mRecyclerView,position, R.id.et_input);

                 switch (view.getId()){
                     case R.id.img_ac_unselect:
                     case R.id.img_ac_select:
                     case R.id.tv_accessory_name:
                         if (ischeck[position]==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                             //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                             adderView.setVisibility(View.VISIBLE);
                             img_ac_unselect.setVisibility(View.INVISIBLE);
                             img_ac_select.setVisibility(View.VISIBLE);
                             ischeck[position]=true;

                             //没选选择时间默认数量为1
                             accessory=(Accessory)adapter.getItem(position);
                             fAccessory=new FAccessory();
                             fAccessory.setFAccessoryName(accessory.getAccessoryName());
                             fAccessory.setQuantity("1"); //默认数字为1
                             fAccessory.setDiscountPrice(accessory.getAccessoryPrice());
                             map.put(position,fAccessory);
                             //选择了时间数量根据输入框中的来
                             adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                 @Override
                                 public void onValueChange(int value) {
                                     //没选选择时间默认数量为1
                                     accessory=(Accessory)adapter.getItem(position);
                                     fAccessory=new FAccessory();
                                     fAccessory.setFAccessoryName(accessory.getAccessoryName());
                                     fAccessory.setQuantity(String.valueOf(value));
                                     fAccessory.setDiscountPrice(accessory.getAccessoryPrice()*value);
                                     map.put(position,fAccessory);
                                 }
                             });

                         }else {
                             //viewadd.setVisibility(View.INVISIBLE); //数量选择器消失
                             adderView.setVisibility(View.INVISIBLE);
                             img_ac_unselect.setVisibility(View.VISIBLE);
                             img_ac_select.setVisibility(View.INVISIBLE);
                             adderView.setValue(1); //但用户取消时将值设置为默认为1
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
                    System.out.println("选择了"+map.get(key).getFAccessoryName()+"配件"+"数量"+map.get(key).getQuantity()+"总价格"+map.get(key).getDiscountPrice());
                    System.out.println("选择了-------------------------------------------");
                }

                //将选中的数据返回回  预结单里
                fList=new ArrayList<>(map.values());
                Intent intent=new Intent(AddAccessoryActivity.this,Order_details_Activity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("ARRAYLIST", (Serializable) fList);
                intent.putExtra("BUNDLE",bundle);
                startActivity(intent);


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
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
       switch (baseResult.getStatusCode()){
           case 200:

               mList.addAll(baseResult.getData().getItem1());
               Log.d("mlistsize", String.valueOf(mList.size()));
               ischeck=new boolean[mList.size()];
               adapter.setNewData(mList);
               break;
               default:
                   ischeck=new boolean[10000];
                   Toast.makeText(AddAccessoryActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                   break;

       }


    }

    @Override
    public void GetFactoryService(BaseResult<GetFactorySeviceData<Service>> baseResult) {

    }




}
