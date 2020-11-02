package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Address;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.MyServiceArea;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.entity.ServiceAddress;
import com.ying.administrator.masterappdemo.mvp.contract.AddServiceContract;
import com.ying.administrator.masterappdemo.mvp.model.AddServiceModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddServicePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ServiceAddressAdapter;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddServiceAreaInfoActivity extends BaseActivity<AddServicePresenter, AddServiceModel> implements View.OnClickListener, AddServiceContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.btn_clear)
    Button mBtnClear;
    @BindView(R.id.rv_region)
    RecyclerView mRvRegion;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private List<Province> provinceList;

    private List<Address> getserviceaddresslist = new ArrayList<>(); //得到已选的地址
    private List<ServiceAddress> serviceAddressList = new ArrayList<>();
    private ServiceAddressAdapter serviceAddressAdapter;
    private String codestr = "";
    private String userId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_service_area_info;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

    }

    @Override
    public void initView() {
        showProgress();
        mTvTitle.setText("修改服务区域");


        serviceAddressAdapter = new ServiceAddressAdapter(R.layout.item_region, serviceAddressList);
        mRvRegion.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvRegion.setAdapter(serviceAddressAdapter);
        serviceAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_delete:
                        serviceAddressList.remove(position);
                        serviceAddressAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        mPresenter.GetServiceRangeByUserID(userId);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_clear:
                if (serviceAddressList.size()==0){
                    return;
                }
                final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                dialog.setMessage("是否确认清空")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        serviceAddressList.clear();
                        serviceAddressAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.btn_add:
                startActivityForResult(new Intent(mActivity, ServiceAreaProvinceActivity.class), 100);
                break;
            case R.id.btn_save:
                for (int i = 0; i < serviceAddressList.size(); i++) {
                    codestr += serviceAddressList.get(i).getCodestr() + ",";
                }
                if (codestr.contains(",")) {
                    codestr = codestr.substring(0, codestr.lastIndexOf(","));
                }
                if ("".equals(codestr)) {
                    ToastUtils.showShort("请添加至少一个服务区域");
                    return;
                }
                Log.d("===============>codestr", codestr);
                showProgress();
                mPresenter.AddorUpdateServiceArea(userId, codestr);

                break;
        }

    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {

    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {

    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {

    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult, int code) {

    }


    /*获取服务区域*/
    @Override
    public void GetServiceRangeByUserID(BaseResult<MyServiceArea> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    hideProgress();
                } else {
                    hideProgress();
                    getserviceaddresslist.addAll(baseResult.getData().getItem1());

                    for (int i = 0; i < getserviceaddresslist.size(); i++) {

                        /*public ServiceAddress(Province province, City city, Area area, District district) {*/
                        Province province = new Province();
                        province.setCode(getserviceaddresslist.get(i).getProvinceCode());
                        province.setName(getserviceaddresslist.get(i).getProvinceName());
                        City city = new City();
                        city.setCode(getserviceaddresslist.get(i).getCityCode());
                        city.setName(getserviceaddresslist.get(i).getCityName());
                        Area area = new Area();
                        area.setCode(getserviceaddresslist.get(i).getAreaCode());
                        area.setName(getserviceaddresslist.get(i).getAreaName());
                        District district = new District();
                        district.setCode(getserviceaddresslist.get(i).getDistrictCode());
                        district.setName(getserviceaddresslist.get(i).getDistrictName());

                        ServiceAddress serviceAddress = new ServiceAddress(province, city, area, district);

                        serviceAddressList.add(serviceAddress);


                    }

                   /*      serviceAddressAdapter=new ServiceAddressAdapter(R.layout.item_region,serviceAddressList);
                         mRvRegion.setLayoutManager(new LinearLayoutManager(mActivity));
                         mRvRegion.setAdapter(serviceAddressAdapter);*/
                    serviceAddressAdapter.notifyDataSetChanged();

                }
                break;
            default:
                break;
        }

    }

    /*更新服务范围*/
    @Override
    public void AddorUpdateServiceArea(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    hideProgress();
                    AddServiceAreaInfoActivity.this.finish();

                } else {
                    hideProgress();
                }
                break;
            default:
                hideProgress();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == 100) {
                    provinceList = (List<Province>) data.getSerializableExtra("list");
                    for (int i = 0; i < provinceList.size(); i++) {
                        if (provinceList.get(i).isSelect()) {
                            ServiceAddress addr = new ServiceAddress(provinceList.get(i), null, null, null);
                            if (serviceAddressList.size() > 0) {
                                for (int j = 0; j < serviceAddressList.size(); j++) {
                                    if (addr.getCodestr().equals(serviceAddressList.get(j).getCodestr())) {
                                        serviceAddressList.remove(j);
                                    }
                                }
                                serviceAddressList.add(0, addr);
                            } else {
                                serviceAddressList.add(0, addr);
                            }
                        } else {
                            for (int j = 0; j < provinceList.get(i).getCities().size(); j++) {
                                if (provinceList.get(i).getCities().get(j).isSelect()) {
                                    ServiceAddress addr = new ServiceAddress(provinceList.get(i), provinceList.get(i).getCities().get(j), null, null);
                                    if (serviceAddressList.size() > 0) {
                                        for (int k = 0; k < serviceAddressList.size(); k++) {
                                            if (addr.getCodestr().equals(serviceAddressList.get(k).getCodestr())) {
                                                serviceAddressList.remove(k);
                                            }
                                        }
                                        serviceAddressList.add(0, addr);
                                    } else {
                                        serviceAddressList.add(0, addr);
                                    }
                                } else {
                                    for (int k = 0; k < provinceList.get(i).getCities().get(j).getCounties().size(); k++) {
                                        if (provinceList.get(i).getCities().get(j).getCounties().get(k).isSelect()) {
                                            ServiceAddress addr = new ServiceAddress(provinceList.get(i), provinceList.get(i).getCities().get(j), provinceList.get(i).getCities().get(j).getCounties().get(k), null);
                                            if (serviceAddressList.size() > 0) {
                                                for (int l = 0; l < serviceAddressList.size(); l++) {
                                                    if (addr.getCodestr().equals(serviceAddressList.get(l).getCodestr())) {
                                                        serviceAddressList.remove(l);
                                                    }
                                                }
                                                serviceAddressList.add(0, addr);
                                            } else {
                                                serviceAddressList.add(0, addr);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    serviceAddressAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
