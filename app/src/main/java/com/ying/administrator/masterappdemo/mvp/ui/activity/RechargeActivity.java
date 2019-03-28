package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.mipush.sdk.Constants;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FaceValue;
import com.ying.administrator.masterappdemo.entity.PayResult;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.RechargeContract;
import com.ying.administrator.masterappdemo.mvp.model.RechargeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.RechargePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.FaceValueAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RechargeActivity extends BaseActivity<RechargePresenter, RechargeModel> implements View.OnClickListener, RechargeContract.View {

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.rl_recharge_amount)
    RecyclerView mRlRechargeAmount;
    @BindView(R.id.et_any_amount)
    EditText mEtAnyAmount;
    @BindView(R.id.tv_actual_arrival)
    TextView mTvActualArrival;
    @BindView(R.id.iv_aplipay)
    ImageView mIvAplipay;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.ll_wxpay)
    LinearLayout mLlWxpay;
    @BindView(R.id.tv_recharge_agreement)
    TextView mTvRechargeAgreement;
    @BindView(R.id.bt_recharge)
    Button mBtRecharge;
    @BindView(R.id.tv_title)
    TextView mTvTitle;


    @BindView(R.id.tv_freeze_money)
    TextView mTvFreezeMoney;
    @BindView(R.id.tv_presentation_money)
    TextView mTvPresentationMoney;
    @BindView(R.id.tv_total_money)
    TextView mTvTotalMoney;
    private List<FaceValue> faceValueList = new ArrayList<>();
    private FaceValueAdapter faceValueAdapter;
    private String[] faceValues = new String[]{"100", "300", "500", "1000", "2000", "3000"};
    private String value;
    private IWXAPI api;
    private int payway = 1;
    private SPUtils spUtils;
    private String userID;
    private String orderinfo;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge;
    }

//    @Override
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
////        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarView(mView);
//        mImmersionBar.keyboardEnable(true);
//        mImmersionBar.init();
//    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        mPresenter.GetUserInfoList(userID, "1");


        mIvAplipay.setSelected(true);//默认选中支付宝
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("在线充值");
        for (int i = 0; i < faceValues.length; i++) {
            faceValueList.add(new FaceValue(faceValues[i], false));
        }
        faceValueList.get(0).setSelect(true);
        value = faceValueList.get(0).getValue();
        mTvActualArrival.setText(value);
        faceValueAdapter = new FaceValueAdapter(R.layout.face_value_item, faceValueList);
        mRlRechargeAmount.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRlRechargeAmount.setAdapter(faceValueAdapter);
        faceValueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mEtAnyAmount.clearFocus();
                mEtAnyAmount.setText("");
                for (int i = 0; i < faceValueList.size(); i++) {
                    if (i == position) {
                        faceValueList.get(i).setSelect(true);
                    } else {
                        faceValueList.get(i).setSelect(false);
                    }
                }
                faceValueAdapter.notifyDataSetChanged();
                value = faceValueList.get(position).getValue();
                mTvActualArrival.setText(value);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mLlAlipay.setOnClickListener(this);
        mLlWxpay.setOnClickListener(this);
        mBtRecharge.setOnClickListener(this);
        mTvRechargeAgreement.setOnClickListener(this);
        mEtAnyAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < faceValueList.size(); i++) {
                        faceValueList.get(i).setSelect(false);
                    }
                    faceValueAdapter.notifyDataSetChanged();
                    value = "0";
                    mTvActualArrival.setText(value);
                }
            }
        });
        mEtAnyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    value = s.replace(0, 1, "").toString();
                } else {
                    if ("".equals(text)) {
                        value = "0";
                    } else {
                        value = text;
                    }
                }
                mTvActualArrival.setText(value);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_actionbar_return:
                finish();
                break;
            case R.id.ll_alipay:
                payway = 1;
                mIvAplipay.setSelected(true);
                mIvWechat.setSelected(false);
                break;
            case R.id.ll_wxpay:
                payway = 2;
                mIvAplipay.setSelected(false);
                mIvWechat.setSelected(true);
                break;
            case R.id.tv_recharge_agreement:

                break;
            case R.id.bt_recharge:
                if (value == null || "0".equals(value)) {
                    ToastUtils.showShort("请选择或输入充值金额");
                    return;
                }
                switch (payway) {
                    case 1:
                        mPresenter.GetOrderStr(userID, value);
//                        alipay();
                        break;
                    case 2:
//                        WXpay();
                        break;
                }
                break;
        }
    }

    /**
     * 支付宝支付业务
     */
    public void alipay() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        orderinfo="app_id=2019022063243925&biz_content=%7b%22body%22%3a%2218892621500%e5%85%85%e5%80%bc100%22%2c%22out_trade_no%22%3a%221551925667260075260075%22%2c%22product_code%22%3a%22QUICK_MSECURITY_PAY%22%2c%22subject%22%3a%22%e5%85%85%e5%80%bc%22%2c%22timeout_express%22%3a%2230m%22%2c%22total_amount%22%3a%220.01%22%7d&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=%e5%a4%96%e7%bd%91%e5%95%86%e6%88%b7%e5%8f%af%e4%bb%a5%e8%ae%bf%e9%97%ae%e7%9a%84%e5%bc%82%e6%ad%a5%e5%9c%b0%e5%9d%80&sign_type=RSA2&timestamp=2019-03-07+10%3a30%3a06&version=1.0&sign=TR2OmEdhQaN2BbKQr6GuiRboONI76S4WFFuim3ojWnKg4CcxdW3uxXyHOWl949Va3fucQ6OiQlU%2b8dx2VU%2fvUbhHRqAlCRJZ8%2fYdUD5kypTLElwA0ZS8BznnHmk3pn3DAtvNjUFPD2fO7YGytDJzo4FIkX5wnnBYuFx4Rf8N2HzVJC2JRlCnroS%2braPQluMcWxq9EhcaMHwGzUaegB73nfvQ55OI2jECWR1rOeH83OeLPX3wfwwigf1TmRsWuCuh5xjRq2hlV6DewlTl%2fg4NcYL1ZIwG26TXQ8tSjhCEHSbFgWhYYOk7UaRtyP2HSgoKuN%2bd3uVIU%2bE6%2fvxc2hoQbg%3d%3d";
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                Map<String, String> result = alipay.payV2(orderinfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    public void WXpay() {
        PayReq req = new PayReq();
//        req.appId			= json.getString("appid");
//        req.partnerId		= json.getString("partnerid");
//        req.prepayId		= json.getString("prepayid");
//        req.nonceStr		= json.getString("noncestr");
//        req.timeStamp		= json.getString("timestamp");
//        req.packageValue	= json.getString("package");
//        req.sign			= json.getString("sign");
        req.extData = "app data"; // optional
        api.sendReq(req);
    }

    /**
     * 支付宝支付结果回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                        EventBus.getDefault().post("");
                        RechargeActivity.this.finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 微信支付结果
     *
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                ToastUtils.showShort("支付成功");
                break;
            case -1:
                ToastUtils.showShort("支付错误");
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                break;
        }
    }

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    orderinfo = baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)) {
                        alipay();
                    }
                } else {
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    userInfo = baseResult.getData().getData().get(0);
                    String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                    mTvMoney.setText(format);
                    String FrozenMoney= String.valueOf(userInfo.getFrozenMoney());
                    mTvFreezeMoney.setText(FrozenMoney);
                    //赠送金额暂无
                    String TotalMoney= String.valueOf(userInfo.getTotalMoney());
                    mTvTotalMoney.setText(TotalMoney);


                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}