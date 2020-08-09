package com.ying.administrator.masterappdemo.v3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;
import com.ying.administrator.masterappdemo.mvp.model.NewAddAccessoriesModel;
import com.ying.administrator.masterappdemo.mvp.presenter.NewAddAccessoriesPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Acc_List_Adapter;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 添加配件
 */
public class AccListActivity extends BaseActivity<NewAddAccessoriesPresenter, NewAddAccessoriesModel> implements View.OnClickListener, NewAddAccessoriesContract.View, Acc_List_Adapter.OnItemEditTextChangedListener {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.rv_acc)
    RecyclerView mRvAcc;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.btn_add)
    Button mBtnAdd;

    private Acc_List_Adapter acc_list_adapter;
    private List<Accessory> list_accessory = new ArrayList<>();// 获取第一次全部所有的配件
    private List<Accessory> list_accessory_select = new ArrayList<>();// 数量大于0的配件
    private String SubCategoryID;
    private AlertDialog dialog_add_accessories;
    private String cj_or_zg;
    private Accessory acc;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_acc_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mRvAcc.setLayoutManager(new LinearLayoutManager(mActivity));
        acc_list_adapter = new Acc_List_Adapter(R.layout.acc_number_adder, list_accessory);
        mRvAcc.setAdapter(acc_list_adapter);
        acc_list_adapter.setListener(this);

        acc_list_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Accessory acc=list_accessory.get(position);
                int count=acc.getCount();
                switch (view.getId()) {
                    case R.id.btn_add://+
                        acc.setCount(++count);
                        break;
                    case R.id.btn_reduce://-
                        acc.setCount(--count);
                        break;
                }
                acc_list_adapter.notifyDataSetChanged();
            }
        });
        cj_or_zg = getIntent().getStringExtra("cj_or_zg");
        if ("厂寄".equals(cj_or_zg)){
            mTvTitle.setText("厂家寄件申请");
        }else{
            mTvTitle.setText("师傅自购件申请");
        }
        SubCategoryID = getIntent().getStringExtra("SubCategoryID");
        showProgress();
        mPresenter.GetFactoryAccessory(SubCategoryID);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add:
                View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_add_accessories, null);
                final EditText et_accessories_name = view.findViewById(R.id.et_accessories_name);
                final EditText et_accessories_count = view.findViewById(R.id.et_accessories_count);
                Button btn_add = view.findViewById(R.id.btn_add);
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=et_accessories_name.getText().toString();
                        String count=et_accessories_count.getText().toString();
                        if (name.isEmpty()){
                            ToastUtils.showShort("请输入名称");
                            return;
                        }
                        if (count.isEmpty()){
                            ToastUtils.showShort("请输入数量");
                            return;
                        }
                        Accessory accessory = new Accessory();
                        accessory.setFAccessoryID("");
                        accessory.setAccessoryName(name);
                        accessory.setNeedPlatformAuth("Y");
                        if ("厂寄".equals(cj_or_zg)){
                            accessory.setCj_or_zg(0);
                        }else{
                            accessory.setCj_or_zg(1);
                        }
                        accessory.setFCategoryID(list_accessory.get(0).getFCategoryID());
                        accessory.setCount(Integer.parseInt(count));
                        list_accessory.add(0,accessory);
                        acc_list_adapter.notifyDataSetChanged();
                        dialog_add_accessories.dismiss();
                    }
                });
                dialog_add_accessories = new AlertDialog.Builder(mActivity).setView(view)
                        .create();
                dialog_add_accessories.show();
                Window window = dialog_add_accessories.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                window.setAttributes(lp);
                window.setBackgroundDrawable(new ColorDrawable());
                dialog_add_accessories.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                        if (getCurrentFocus()==null){
                            return;
                        }
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
                    }
                });
                break;
            case R.id.btn_submit:
                Intent intent=new Intent();
                list_accessory_select.clear();
                for (int i = 0; i < list_accessory.size(); i++) {
                    if (list_accessory.get(i).getCount()>0){
                        list_accessory_select.add(list_accessory.get(i));
                    }
                }
                for (int i = 0; i < list_accessory_select.size(); i++) {
                    if (!"厂寄".equals(cj_or_zg)){
                        if (list_accessory_select.get(i).getAccessoryPrice()==0){
                            MyUtils.showToast(mActivity,"请输入配件价格");
                            return;
                        }
                    }
                }
                intent.putExtra("list", (Serializable) list_accessory_select);
                setResult(100,intent);
                finish();
                break;
        }
    }

    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                hideProgress();
                if (baseResult.getData().getData() != null) {
                    list_accessory.addAll(baseResult.getData().getData());
                    for (int i = 0; i < list_accessory.size(); i++) {
                        if ("厂寄".equals(cj_or_zg)){
                            list_accessory.get(i).setCj_or_zg(0);
                        }else{
                            list_accessory.get(i).setCj_or_zg(1);
                        }
                    }
                    acc_list_adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void ApplyAccessoryphotoUpload(BaseResult<Data2> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult) {

    }
    @Override
    public void onEditTextAfterTextChanged(String price, int position) {
        acc = list_accessory.get(position);
        acc.setAccessoryPrice(Double.parseDouble(price));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
