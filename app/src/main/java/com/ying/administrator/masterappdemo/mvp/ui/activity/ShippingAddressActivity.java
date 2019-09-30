package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.mvp.contract.AddressContract;
import com.ying.administrator.masterappdemo.mvp.model.AddressModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddressPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ShippingAddressAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressActivity extends BaseActivity<AddressPresenter, AddressModel> implements View.OnClickListener, AddressContract.View {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    private List<AddressList> addressList = new ArrayList<>();
    private SPUtils spUtils;
    private String userId;
    private String type;
    private ShippingAddressAdapter addressAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected void initData() {
//        for (int i = 0; i < 10; i++) {
//            addressList.add(new Address());
//        }
        type = getIntent().getStringExtra("type");
        addressAdapter = new ShippingAddressAdapter(R.layout.item_address, addressList);
        mRvAddress.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddress.setAdapter(addressAdapter);
        addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_edit:
                        Intent intent = new Intent(mActivity, AddAddressActivity.class);
                        intent.putExtra("address", addressList.get(position));
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        if (type != null) {
            addressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("address", addressList.get(position));
                    setResult(100, intent);
                    finish();
                }
            });
        }
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userId);
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("我的收货地址");
        mTvSave.setText("添加新地址");
        mTvSave.setVisibility(View.VISIBLE);
        mTvActionbarTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_save:
                startActivity(new Intent(mActivity, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void AddAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void DeleteAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                AddressList address = new AddressList();
                address = null;
                List<AddressList> address2 = new ArrayList<>();
                for (int i = 0; i < baseResult.getData().size(); i++) {
                    if ("1".equals(baseResult.getData().get(i).getIsDefault())) {
                        address = baseResult.getData().get(i);
                    } else {
                        address2.add(baseResult.getData().get(i));
                    }

                }
                if (address == null) {
                    addressList.addAll(address2);
                } else {
                    addressList.add(address);
                    addressList.addAll(address2);
                }

//                addressList = baseResult.getData();
                addressAdapter.setNewData(addressList);
                break;
            default:
                ToastUtils.showShort("获取失败");
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
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"address".equals(message)) {
            return;
        }
        addressList.clear();
        mPresenter.GetAccountAddress(userId);
    }
}
