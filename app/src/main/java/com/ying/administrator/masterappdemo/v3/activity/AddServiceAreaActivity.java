package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.Area2;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.City2;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.MyServiceArea;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.entity.Province2;
import com.ying.administrator.masterappdemo.mvp.contract.AddServiceContract;
import com.ying.administrator.masterappdemo.mvp.model.AddServiceModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddServicePresenter;
import com.ying.administrator.masterappdemo.v3.adapter.ExpandListViewAdapter;

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
    @BindView(R.id.lv)
    ExpandableListView mLv;
    private List<Province2> provinceList = new ArrayList<>();
    private List<City2> cityList = new ArrayList<>();
    private List<Area2> areaList = new ArrayList<>();
    private List<Province> provinceList1 = new ArrayList<>();
    private ExpandListViewAdapter mAdapter;
    private int position;
    private List<City> cityList1 = new ArrayList<>();
    private int position2;
    private List<Area> areaList1=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_addservicearea;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.GetProvince();

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                provinceList1 = baseResult.getData();
                for (int i = 0; i < provinceList1.size(); i++) {
                    provinceList.add(new Province2(false, provinceList1.get(i), new ArrayList<City2>()));
                }
                mAdapter = new ExpandListViewAdapter(provinceList, this);
                mLv.setAdapter(mAdapter);
                //点击父级请求子级数据
                mLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                        if (cityList.size()==0){
                        String province_id = mAdapter.getGroup(groupPosition).getProvince().getCode();
                        position = groupPosition;
                        mPresenter.GetCity(province_id);

//                        }else {
//                            String cityCode = mAdapter.getGroup(position).getCities().get(groupPosition).getCity().getCode();
//                            position2 = groupPosition;
//                            mPresenter.GetArea(cityCode);
//                        }

//                        if (mAdapter.getChild(groupPosition,position2)!=null) {
//                            if (parent.isGroupExpanded(groupPosition)) {
//                                parent.collapseGroup(groupPosition);
//                            } else {
//                                parent.expandGroup(groupPosition);
//                            }
//                        } else {
//                            mPresenter.GetCity(province_id);
//                        }
                        return true;
                    }
                });
                mAdapter.OnGroupClickListener(new ExpandListViewAdapter.OnGroupClickListener() {
                    @Override
                    public void onGropClick(ExpandableListView parent, View v, int groupPosition, long id, BaseExpandableListAdapter adapter) {
                        String cityCode = mAdapter.getGroup(position).getCities().get(groupPosition).getCity().getCode();
                        position2 = groupPosition;
                        if (adapter.getChildrenCount(groupPosition)> 0) {
                            if (parent.isGroupExpanded(groupPosition)) {
                                parent.collapseGroup(groupPosition);
                            } else {
                                parent.expandGroup(groupPosition);
                            }
                        } else {
                            mPresenter.GetArea(cityCode);

                        }
                    }
                });
                mAdapter.OnChildClickListener(new ExpandListViewAdapter.OnChildClickListener() {
                    @Override
                    public void OnChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id, BaseExpandableListAdapter adapter) {

                    }
                });

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {
        cityList1.clear();
        cityList.clear();
        cityList1 = baseResult.getData().getItem2();
        for (int i = 0; i < cityList1.size(); i++) {
            cityList.add(new City2(cityList1.get(i), false, new ArrayList<Area2>()));
        }
        mAdapter.addAllChild(position, cityList);
        mLv.expandGroup(position);
    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {
        areaList.clear();

        areaList1 = baseResult.getData().getItem2();
        for (int i = 0; i < areaList1.size(); i++) {
            areaList.add(new Area2(areaList1.get(i), false));
        }
        mAdapter.addAllChild2(position, position2, areaList);
//        mLv.expandGroup(position2);
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
}
