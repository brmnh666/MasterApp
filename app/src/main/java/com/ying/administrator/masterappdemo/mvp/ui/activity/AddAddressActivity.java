package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
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
import com.ying.administrator.masterappdemo.mvp.ui.adapter.AreaAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.CityAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.DistrictAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ProvinceAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAddressActivity extends BaseActivity<AddressPresenter, AddressModel> implements View.OnClickListener, AddressContract.View {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_receiver)
    EditText mEtReceiver;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.et_cellphone_number)
    EditText mEtCellphoneNumber;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.ll_pick_address)
    LinearLayout mLlPickAddress;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.switcher_default)
    Switch mSwitcherDefault;
    @BindView(R.id.ll_delete)
    LinearLayout mLlDelete;
    private SPUtils spUtils;
    private String userId;
    private String UserName;
    private String Phone;
    private String Address;
    private String Default = "0";
    private List<Province> provinceList;
    private ProvinceAdapter provinceAdapter;
    private String ProvinceName;
    private String ProvinceCode;
    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_area;
    private TextView tv_district;
    private TextView tv_choose;
    private RecyclerView rv_address_choose;
    private ImageView iv_close;
    private PopupWindow popupWindow;
    private List<District> districtList;
    private DistrictAdapter districtAdapter;
    private String DistrictName;
    private String DistrictCode;
    private List<Area> areaList;
    private AreaAdapter areaAdapter;
    private String AreaName;
    private String AreaCode;
    private List<City> cityList;
    private CityAdapter cityAdapter;
    private String CityName;
    private String CityCode;
    private String Pcad;
    private String AddressDetail;
    private AddressList address;
    private View delete_view;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private TextView tv_message;
    private AlertDialog delete_dialog;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        address = (AddressList) getIntent().getSerializableExtra("address");
        if (address != null) {
            mLlDelete.setVisibility(View.VISIBLE);
            mTvTitle.setText("修改收货地址");
            mEtReceiver.setText(address.getUserName());
            mEtCellphoneNumber.setText(address.getPhone());
            ProvinceName = address.getProvince();
            CityName = address.getCity();
            AreaName = address.getArea();
            DistrictName = address.getDistrict();
            Pcad = address.getProvince() + address.getCity() + address.getArea() + address.getDistrict();
            mTvArea.setText(Pcad);
            mEtAddress.setText(address.getAddress());
            if ("1".equals(address.getIsDefault())) {
                mSwitcherDefault.setChecked(true);
                Default = "1";
            } else {
                mSwitcherDefault.setChecked(false);
                Default = "0";
            }
        } else {
            mLlDelete.setVisibility(View.GONE);
            mTvTitle.setText("新增收货地址");
        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mLlDelete.setOnClickListener(this);

        mTvSave.setOnClickListener(this);
        mLlPickAddress.setOnClickListener(this);
        mSwitcherDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Default = "1";
                } else {
                    Default = "0";
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_delete:
                delete_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_home, null);
                btn_negtive = delete_view.findViewById(R.id.negtive);
                btn_positive = delete_view.findViewById(R.id.positive);
                tv_title = delete_view.findViewById(R.id.title);
                tv_message = delete_view.findViewById(R.id.message);
                delete_dialog = new AlertDialog.Builder(mActivity)
                        .setView(delete_view)
                        .create();
                delete_dialog.show();
                tv_title.setText("提示");
                tv_message.setText("确定要删除改地址吗？");
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.DeleteAccountAddress(address.getAccountAdressID());
                    }
                });
                break;
            case R.id.ll_pick_address:
                mPresenter.GetProvince();
                break;
            case R.id.tv_province:
                mPresenter.GetProvince();
                break;
            case R.id.tv_city:
                mPresenter.GetCity(ProvinceCode);
                break;
            case R.id.tv_area:
                mPresenter.GetArea(CityCode);
                break;
            case R.id.tv_save:
                UserName = mEtReceiver.getText().toString().trim();
                Phone = mEtCellphoneNumber.getText().toString().trim();
                Address = mEtAddress.getText().toString().trim();
                if ("".equals(UserName)) {
                    MyUtils.showToast(mActivity, "请填写收货人");
                    return;
                }
                if ("".equals(Phone)) {
                    MyUtils.showToast(mActivity, "请填写手机号码");
                    return;
                }
                if ("".equals(Address)) {
                    MyUtils.showToast(mActivity, "请填写详细地址");
                    return;
                }
                if (address != null) {
                    mPresenter.UpdateAccountAddress(address.getAccountAdressID(), userId, ProvinceName, CityName, AreaName, DistrictName, Address, Default, UserName, Phone);
                } else {
                    mPresenter.AddAccountAddress(userId, ProvinceName, CityName, AreaName, DistrictName, Address, Default, UserName, Phone);
                }
                break;
            case R.id.iv_add:
//                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 0);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    Cursor cursor = getContentResolver()
                            .query(uri,
                                    new String[]{
                                            ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                    null, null, null);
                    while (cursor.moveToNext()) {
                        String number = cursor.getString(0);
                        String name = cursor.getString(1);
                        mEtCellphoneNumber.setText(number);
//                        if (mEtReceiver.getText().toString().isEmpty()) {
                        mEtReceiver.setText(name);
//                        }

                    }
                }
            }

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
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort("添加成功");
                    EventBus.getDefault().post("address");
                    finish();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateAccountAddress(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort("修改成功");
                    EventBus.getDefault().post("address");
                    finish();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void DeleteAccountAddress(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort("删除成功");
                    EventBus.getDefault().post("address");
                    finish();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                provinceList = baseResult.getData();
                provinceAdapter = new ProvinceAdapter(R.layout.category_item, provinceList);
//                showPopWindow(mTvProvince, provinceAdapter, provinceList);
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        rv_address_choose.setAdapter(provinceAdapter);
                        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ProvinceName = provinceList.get(position).getName();
                                ProvinceCode = provinceList.get(position).getCode();
                                mPresenter.GetCity(ProvinceCode);
                                tv_province.setText(ProvinceName);
                                tv_province.setVisibility(View.VISIBLE);
                                tv_city.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        showPopWindowGetAddress(mTvArea);
                    }
                } else {
                    showPopWindowGetAddress(mTvArea);
                }

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
                    rv_address_choose.setAdapter(cityAdapter);
                    tv_choose.setText("选择城市");
                    cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            CityName = cityList.get(position).getName();
                            CityCode = cityList.get(position).getCode();
                            mPresenter.GetArea(CityCode);
                            tv_city.setText(CityName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
//                    showPopWindow(mTvCity, cityAdapter, cityList);
                } else {
                    MyUtils.showToast(mActivity, "获取市失败！");
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
                    rv_address_choose.setAdapter(areaAdapter);
                    tv_choose.setText("选择区/县");
                    areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            AreaName = areaList.get(position).getName();
                            AreaCode = areaList.get(position).getCode();
                            mPresenter.GetDistrict(AreaCode);
                            tv_area.setText(AreaName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
//                    showPopWindow(mTvArea, areaAdapter, areaList);
                } else {
                    MyUtils.showToast(mActivity, "获取区失败！");
                }

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<District>> data = baseResult.getData();
                if (data.isItem1()) {
                    districtList = data.getItem2();
                    districtAdapter = new DistrictAdapter(R.layout.category_item, districtList);
                    rv_address_choose.setAdapter(districtAdapter);
                    tv_choose.setText("选择街道/乡/镇");
                    districtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            DistrictName = districtList.get(position).getName();
                            DistrictCode = districtList.get(position).getCode();
                            tv_district.setText(DistrictName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                            tv_district.setVisibility(View.VISIBLE);
                            popupWindow.dismiss();
                            mTvArea.setText(ProvinceName + CityName + AreaName + DistrictName);
                        }
                    });
                } else {
                    MyUtils.showToast(mActivity, "获取街道/乡/镇失败");
                }
                break;
            case 401:
                break;
        }
    }

    public void showPopWindowGetAddress(final TextView tv) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.address_pop, null);
        tv_province = contentView.findViewById(R.id.tv_province);
        tv_city = contentView.findViewById(R.id.tv_city);
        tv_area = contentView.findViewById(R.id.tv_area);
        tv_district = contentView.findViewById(R.id.tv_district);
        tv_city.setOnClickListener(this);
        tv_province.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_choose = contentView.findViewById(R.id.tv_choose);
        tv_choose.setText("选择省份/地区");
        rv_address_choose = contentView.findViewById(R.id.rv_address_choose);
        iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        rv_address_choose.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_address_choose.setAdapter(provinceAdapter);
        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProvinceName = provinceList.get(position).getName();
                ProvinceCode = provinceList.get(position).getCode();
                mPresenter.GetCity(ProvinceCode);
                tv_province.setText(ProvinceName);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
//                try {
//                    JSONObject json=new JSONObject("");
//                    json.g
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        });
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() - 700);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                MyUtils.backgroundAlpha(mActivity,1);
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }
}
