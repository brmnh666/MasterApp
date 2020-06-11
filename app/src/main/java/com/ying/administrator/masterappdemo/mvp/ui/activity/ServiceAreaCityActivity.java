package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.MyServiceArea;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.mvp.contract.AddServiceContract;
import com.ying.administrator.masterappdemo.mvp.model.AddServiceModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddServicePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ServiceAreaCityAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceAreaCityActivity extends BaseActivity<AddServicePresenter, AddServiceModel> implements AddServiceContract.View, View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_all_check)
    TextView mTvAllCheck;
    @BindView(R.id.rv_addrlist)
    RecyclerView mRvAddrlist;
    @BindView(R.id.btn_ok)
    Button mBtnOk;

    private ServiceAreaCityAdapter cityAdapter;

    private List<City> list = new ArrayList<>();//市集合
    private List<Area> areaList = new ArrayList<>();//区集合
    private String parentCode;
    private String name;
    private boolean isAllCheck = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_addrlist;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        mRvAddrlist.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddrlist.setHasFixedSize(true);
        mRvAddrlist.setNestedScrollingEnabled(false);
        cityAdapter = new ServiceAreaCityAdapter(R.layout.item_service_area, list);
        mRvAddrlist.setAdapter(cityAdapter);
        cityAdapter.setEmptyView(getEmptyView());
        showProgress();
        parentCode = getIntent().getStringExtra("parentCode");
        name = getIntent().getStringExtra("name");
        mTvTitle.setText(name);
        mPresenter.GetCity(parentCode);
        cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ServiceAreaAreaActivity.class);
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("parentCode", list.get(position).getCode());
                startActivityForResult(intent,100);
            }
        });
        cityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_check:
                        list.get(position).setSelect(!list.get(position).isSelect());
                        cityAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvAllCheck.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ok:
                Intent intent=new Intent();
                intent.putExtra("parentCode", parentCode);
                intent.putExtra("list", (Serializable) list);
                setResult(100,intent);
                finish();
                break;
            case R.id.tv_all_check:
                isAllCheck = !isAllCheck;
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(isAllCheck);
                }
                cityAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {

    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list.addAll(baseResult.getData().getItem2());
                cityAdapter.setNewData(list);
                hideProgress();
                break;
            default:
                break;
        }
    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {

    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult, int code) {

    }

    @Override
    public void GetServiceRangeByUserID(BaseResult<MyServiceArea> baseResult) {

    }

    @Override
    public void AddorUpdateServiceArea(BaseResult<Data<String>> baseResult) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if (resultCode==100){
                    areaList= (List<Area>) data.getSerializableExtra("list");
                    String parentCode=data.getStringExtra("parentCode");
                    for (int i = 0; i < list.size(); i++) {
                        if (parentCode.equals(list.get(i).getCode())){
                            list.get(i).setCounties(areaList);
                            cityAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
        }
    }
}
