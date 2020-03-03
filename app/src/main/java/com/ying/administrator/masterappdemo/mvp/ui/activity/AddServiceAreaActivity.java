package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.entity.ServiceAddress;
import com.ying.administrator.masterappdemo.mvp.contract.AddServiceContract;
import com.ying.administrator.masterappdemo.mvp.model.AddServiceModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddServicePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.AreaAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.CityAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.DistrictAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ProvinceAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ServiceAddressAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddServiceAreaActivity extends BaseActivity<AddServicePresenter, AddServiceModel> implements View.OnClickListener, AddServiceContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_province)
    TextView mTvProvince;
    @BindView(R.id.ll_province)
    LinearLayout mLlProvince;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.ll_area)
    LinearLayout mLlArea;
    @BindView(R.id.tv_district)
    TextView mTvDistrict;
    @BindView(R.id.ll_town)
    LinearLayout mLlTown;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rv_region)
    RecyclerView mRvRegion;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private Province mProvince;
    private City mCity;
    private Area mArea;
    private District mDistrict;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<Area> areaList;
    private List<District> districtList;
    private List<ServiceAddress> serviceAddressList = new ArrayList<>();
    private List<ServiceAddress> tempServiceAddressList = new ArrayList<>();
    private ServiceAddressAdapter serviceAddressAdapter;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private AreaAdapter areaAdapter;
    private DistrictAdapter districtAdapter;
    private PopupWindow popupWindow;
    private String codestr = "";
    private String name;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_service_area;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvTitle.setText("添加服务区域");
        mTvProvince.setText("省");
        mTvCity.setText("市");
        mTvArea.setText("区");
        mTvDistrict.setText("街道");
        serviceAddressAdapter = new ServiceAddressAdapter(R.layout.item_region, serviceAddressList);
        mRvRegion.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvRegion.setAdapter(serviceAddressAdapter);
        serviceAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        serviceAddressList.remove(position);
                        serviceAddressAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlProvince.setOnClickListener(this);
        mLlCity.setOnClickListener(this);
        mLlArea.setOnClickListener(this);
        mLlTown.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_province:
                mPresenter.GetProvince();
                break;
            case R.id.ll_city:
                if (mProvince == null) {
                    ToastUtils.showShort("请选择省！");
                    return;
                }
                mPresenter.GetCity(mProvince.getCode());
                break;
            case R.id.ll_area:
                if (mCity == null) {
                    ToastUtils.showShort("请选择市！");
                    return;
                }
                mPresenter.GetArea(mCity.getCode());
                break;
            case R.id.ll_town:
                if (mCity == null) {
                    ToastUtils.showShort("请选择市！");
                    return;
                }
                if ("济源市".equals(mCity.getName())) {
                    mPresenter.GetDistrict("410881", 0);
                } else {
                    if (mArea == null) {
                        ToastUtils.showShort("请选择区！");
                        return;
                    }
                    mPresenter.GetDistrict(mArea.getCode(), 0);
                }
                break;
            case R.id.iv_add:
                if (mProvince == null) {
                    ToastUtils.showShort("请选择省！");
                    return;
                }
                if (mCity == null) {
                    ToastUtils.showShort("请选择市！");
                    return;
                }
                if (mArea == null) {
                    ToastUtils.showShort("请选择区！");
                    return;
                }
                if (mDistrict == null) {
                    mPresenter.GetDistrict(mArea.getCode(), 1);
                } else {
                    if (serviceAddressList.size() > 0) {
                        for (int i = 0; i < serviceAddressList.size(); i++) {
                            if (mDistrict.getCode().equals(serviceAddressList.get(i).getDistrict().getCode())) {
                                serviceAddressList.remove(i);
                            }
                        }
                        serviceAddressList.add(new ServiceAddress(mProvince, mCity, mArea, mDistrict));
                    } else {
                        serviceAddressList.add(new ServiceAddress(mProvince, mCity, mArea, mDistrict));
                    }
                    serviceAddressAdapter.notifyDataSetChanged();
                    mProvince = null;
                    mCity = null;
                    mArea = null;
                    mDistrict = null;
                    mTvProvince.setText("省");
                    mTvCity.setText("市");
                    mTvArea.setText("区");
                    mTvDistrict.setText("街道");
                }
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
                Intent intent = new Intent();
                intent.putExtra("codestr", codestr);
                setResult(317, intent);
                finish();
                break;
        }

    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                provinceList = baseResult.getData();
                provinceAdapter = new ProvinceAdapter(R.layout.category_item, provinceList);
                showPopWindow(mTvProvince, provinceAdapter, provinceList);
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<City>> data = baseResult.getData();
                if (data.isItem1()) {
                    cityList = data.getItem2();
                    cityAdapter = new CityAdapter(R.layout.category_item, cityList);
                    showPopWindow(mTvCity, cityAdapter, cityList);
                } else {
                    ToastUtils.showShort("获取市失败！");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<Area>> data = baseResult.getData();
                if (data.isItem1()) {
                    areaList = data.getItem2();
                    areaAdapter = new AreaAdapter(R.layout.category_item, areaList);
                    showPopWindow(mTvArea, areaAdapter, areaList);
                } else {
                    ToastUtils.showShort("获取区失败！");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult, int code) {
        switch (code) {
            case 0:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        Data<List<District>> data = baseResult.getData();
                        if (data.isItem1()) {
                            districtList = data.getItem2();
                            districtAdapter = new DistrictAdapter(R.layout.category_item, districtList);
                            showPopWindow(mTvDistrict, districtAdapter, districtList);
                        } else {
                            ToastUtils.showShort("获取街道失败！");
                        }
                        break;
                    case 401:
//                ToastUtils.showShort(baseResult.getData());
                        break;
                }
                break;
            case 1:
                switch (baseResult.getStatusCode()) {
                    case 200:
                        Data<List<District>> data = baseResult.getData();
                        if (data.isItem1()) {
                            districtList = data.getItem2();
                            if (serviceAddressList.size() > 0) {
                                for (int i = 0; i < serviceAddressList.size(); i++) {
                                    for (int j = 0; j < districtList.size(); j++) {
                                        if (serviceAddressList.get(i).getDistrict().getCode().equals(districtList.get(j).getCode())) {
                                            serviceAddressList.remove(i);
                                        }
                                    }
                                }
                            }
                            for (int j = 0; j < districtList.size(); j++) {
                                serviceAddressList.add(new ServiceAddress(mProvince, mCity, mArea, districtList.get(j)));
                            }
                            serviceAddressAdapter.notifyDataSetChanged();
                            mProvince = null;
                            mCity = null;
                            mArea = null;
                            mDistrict = null;
                            mTvProvince.setText("省");
                            mTvCity.setText("市");
                            mTvArea.setText("区");
                            mTvDistrict.setText("街道");
                        } else {
                            ToastUtils.showShort("获取街道失败！");
                        }
                        break;
                    case 401:
//                ToastUtils.showShort(baseResult.getData());
                        break;
                }
                break;
        }

    }

    @Override
    public void GetServiceRangeByUserID(BaseResult<List<Address>> baseResult) {

    }

    @Override
    public void AddorUpdateServiceArea(BaseResult<Data<String>> baseResult) {

    }

    public void showPopWindow(final TextView tv, BaseQuickAdapter adapter, final List list) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupWindow.dismiss();
                if (list.get(position) instanceof Province) {
                    mProvince = ((Province) list.get(position));
                    tv.setText(mProvince.getName());
                    mCity = null;
                    mArea = null;
                    mDistrict = null;
                    mTvCity.setText("市");
                    mTvArea.setText("区");
                    mTvDistrict.setText("街道");
                }
                if (list.get(position) instanceof City) {
                    mCity = ((City) list.get(position));
                    tv.setText(mCity.getName());
                    mArea = null;
                    mDistrict = null;
                    mTvArea.setText("区");
                    mTvDistrict.setText("街道");
                }
                if (list.get(position) instanceof Area) {
                    mArea = ((Area) list.get(position));
                    tv.setText(mArea.getName());
                    mDistrict = null;
                    mTvDistrict.setText("街道");
                }
                if (list.get(position) instanceof District) {
                    mDistrict = ((District) list.get(position));
                    tv.setText(mDistrict.getName());
                }
            }
        });
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(600);
        if (list.size() > 5) {
            popupWindow.setHeight(600);
        } else {
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(tv, 0, 10);
//            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }
}
