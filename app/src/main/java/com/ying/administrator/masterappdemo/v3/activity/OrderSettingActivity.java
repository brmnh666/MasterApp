package com.ying.administrator.masterappdemo.v3.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AddServiceAreaInfoActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MyInfoSkillActivity2;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.v3.adapter.OrderSettingAdapter;
import com.ying.administrator.masterappdemo.v3.bean.OrderSettingBean;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderSettingPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderSettingContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderSettingModel;
import com.ying.administrator.masterappdemo.v3.weight.AutoLineFeedLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderSettingActivity extends BaseActivity<OrderSettingPresenter, OrderSettingModel> implements View.OnClickListener, OrderSettingContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.switcher)
    Switch mSwitcher;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.tv_service_product)
    TextView mTvServiceProduct;
    @BindView(R.id.tv_service_area)
    TextView mTvServiceArea;
    @BindView(R.id.tv_person)
    TextView mTvPerson;
    @BindView(R.id.tv_truck)
    TextView mTvTruck;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_service_area)
    LinearLayout mLlServiceArea;
    @BindView(R.id.ll_service_product)
    LinearLayout mLlServiceProduct;
    @BindView(R.id.tv_team_size)
    LinearLayout mTvTeamSize;
    @BindView(R.id.ll_truck)
    LinearLayout mLlTruck;
    private List<OrderSettingBean> list = new ArrayList<>();
    private OrderSettingAdapter adapter;
    private AlertDialog cancelDialog;
    private EditText et_message;
    private String userID;
    private UserInfo.UserInfoDean userInfo;
    private PopupWindow mPopupWindow;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_order_setting;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 2; i++) {
            list.add(new OrderSettingBean());
        }
        adapter = new OrderSettingAdapter(R.layout.v3_item_service_area, list);
        mRvService.setLayoutManager(new AutoLineFeedLayoutManager());
        mRvService.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("接单设置");
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID, "1");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlServiceArea.setOnClickListener(this);
        mLlServiceProduct.setOnClickListener(this);
        mTvTeamSize.setOnClickListener(this);
        mLlTruck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_service_area:
                startActivity(new Intent(mActivity, AddServiceAreaInfoActivity.class));
                break;
            case R.id.ll_service_product:
//                if (userInfo.getIfAuth() == null) {//未实名认证
//                    ToastUtils.showShort("您暂未实名");
//                    return;
//                } else {
                startActivity(new Intent(this, MyInfoSkillActivity2.class));
//                }
                break;
            case R.id.tv_team_size:
                View Cancelview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel, null);
                et_message = Cancelview.findViewById(R.id.et_message);
                Button negtive = Cancelview.findViewById(R.id.negtive);
                Button positive = Cancelview.findViewById(R.id.positive);
                TextView title = Cancelview.findViewById(R.id.title);
                title.setText("团队人数");
                et_message.setHint("请添加或修改团队人数");
                et_message.setInputType(InputType.TYPE_CLASS_NUMBER);
                negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelDialog.dismiss();
                    }
                });

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = et_message.getText().toString();
                        if (message == null || "".equals(message)) {
                            ToastUtils.showShort("请添加或修改团队人数");
                        } else {
//                                    mPresenter.UpdateOrderState(OrderId, "-1",message);
                            mPresenter.updateTeamNumber(userID, message);
                            cancelDialog.dismiss();
                        }

                    }
                });

                cancelDialog = new AlertDialog.Builder(mActivity).setView(Cancelview).create();
                cancelDialog.show();
                Window window1 = cancelDialog.getWindow();
                WindowManager.LayoutParams layoutParams = window1.getAttributes();
                window1.setAttributes(layoutParams);
                window1.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.ll_truck:
                View view = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_truck, null);
                Button btn_yes = view.findViewById(R.id.btn_yes);
                Button btn_no = view.findViewById(R.id.btn_no);
                Button cancel_btn = view.findViewById(R.id.cancel_btn);
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.IsOrNoTruck(userID,"Y");
                        mPopupWindow.dismiss();
                    }
                });
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.IsOrNoTruck(userID,"N");
                        mPopupWindow.dismiss();
                    }
                });
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopupWindow.setFocusable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        MyUtils.setWindowAlpa(mActivity, false);
                    }
                });
                if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                    mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                }
                MyUtils.setWindowAlpa(mActivity, true);

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
    public void updateTeamNumber(BaseResult<Data<String>> baseObserver) {
        switch (baseObserver.getStatusCode()) {
            case 200:
                ToastUtils.showShort("设置成功");
                mPresenter.GetUserInfoList(userID, "1");
                break;
        }
    }

    @Override
    public void IsOrNoTruck(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort("设置成功");
                mPresenter.GetUserInfoList(userID,"1");
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                if ("0".equals(userInfo.getTeamNumber())) {
                    mTvPerson.setText("请填写团队人数");
                } else {
                    mTvPerson.setText(userInfo.getTeamNumber());
                }

                if (userInfo.getIsOrNoTruck() == null) {
                    mTvTruck.setText("请填写有无货车");
                } else if ("Y".equals(userInfo.getIsOrNoTruck())) {
                    mTvTruck.setText("有");
                } else {
                    mTvTruck.setText("无");

                }
                break;
        }
    }
}
