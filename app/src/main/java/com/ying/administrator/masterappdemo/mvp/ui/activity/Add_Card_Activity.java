package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IsCardNo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.model.CardModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CardPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ChooseBankAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.util.imageutil.FileUtil;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog_ChooseBank;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/*绑定银行卡页面*/
public class Add_Card_Activity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    //    @BindView(R.id.tv_add_card_name)
//    TextView mTvAddCardName;
    @BindView(R.id.tv_add_card_bankname)
    TextView mTvAddCardBankname;
    @BindView(R.id.et_add_card_phone)
    EditText met_add_card_phone;
    /*    @BindView(R.id.et_add_card_verification_code)
        EditText mEtAddCardVerificationCode;*/
   /* @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;*/
    /*@BindView(R.id.tv_getcode)
    TextView mtv_getcode;*/
    @BindView(R.id.tv_bind_card)
    TextView mtv_bind_card;
    @BindView(R.id.et_banknumber)
    EditText met_banknumber;
    @BindView(R.id.et_add_card_name)
    EditText mEtAddCardName;

    @BindView(R.id.img_scan_card)
    ImageView mimg_scan_card;

    /* private TimeCount time;*/
    private CustomDialog_ChooseBank customDialog_chooseBank;//选择银行dialog
    private RecyclerView recyclerView_custom_bank;//显示银行的RecyclerView
    private ChooseBankAdapter chooseBankAdapter;
    private String userId;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();
    public static int REQUEST_CODE_CAMERA=100;
    private String bankname;
    private String name;
    private String phone;
    private List<BankCard> list=new ArrayList<>();//已经绑定的银行卡

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");
        initAccessTokenWithAkSk();
        list= (List<BankCard>) getIntent().getSerializableExtra("cardlist");
    }
    public void isCardNo(final String cardNo){
        //接口
        String path = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="+cardNo+"&cardBinCheck=true";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                System.out.println(str);
//                ToastUtils.showShort(str);
                Gson gson=new Gson();
                IsCardNo result=gson.fromJson(str.replaceAll(" ",""),IsCardNo.class);
                if (result.isValidated()){
                    mPresenter.AddorUpdateAccountPayInfo(userId, phone, bankname, cardNo,name);
                }else{
                    ToastUtils.showShort("请输入有效的银行卡号");
                    hideProgress();
                }
            }
        });
    }

    @Override
    protected void initView() {
        customDialog_chooseBank = new CustomDialog_ChooseBank(mActivity);
        /*time = new TimeCount(60000, 1000);*/
    }

    @Override
    protected void setListener() {
        // mLlChooseBank.setOnClickListener(this);
        mImgActionbarReturn.setOnClickListener(this);
        /*  mtv_getcode.setOnClickListener(this);*/
        mtv_bind_card.setOnClickListener(this);
        mimg_scan_card.setOnClickListener(this);
        met_banknumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//设置过滤器，限制输入字长，及不能输入空格
                met_banknumber.setFilters(
                        new InputFilter[] { new InputFilter.LengthFilter(19), filter });

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() > 0) {
                    mPresenter.GetBankNameByCardNo(s.toString());
                } else {
                    mTvAddCardBankname.setText("");
                }

            }
        });
    }

    /**
     * EditText禁止输入空格，使用：mEditText.setFilters(new InputFilter[] { TextUtils.filter });
     */
    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart, int dend) {
            if (source.equals(" ")) {
                return "";
            } else {
                return null;
            }
        }
    };

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.ll_choose_bank:
                customDialog_chooseBank.getWindow().setBackgroundDrawableResource(R.color.transparent);
                customDialog_chooseBank.show();
                Window window = customDialog_chooseBank.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                Display d = window.getWindowManager().getDefaultDisplay();
                wlp.height = (d.getHeight());
                wlp.width = (d.getWidth());
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                recyclerView_custom_bank = customDialog_chooseBank.findViewById(R.id.recyclerView_custom_choosebank);
                recyclerView_custom_bank.setLayoutManager(new LinearLayoutManager(mActivity));
                chooseBankAdapter = new ChooseBankAdapter(R.layout.item_bank, BankUtil.getBankList(), mActivity);
                recyclerView_custom_bank.setAdapter(chooseBankAdapter);


                chooseBankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ll_choose_bank:
                                mTvAddCardBankname.setText(((Bank) adapter.getItem(position)).getBankname());
                                customDialog_chooseBank.dismiss();
                                break;
                        }

                    }
                });


                break;*/
            case R.id.img_actionbar_return:
                Add_Card_Activity.this.finish();

                break;
       /*     case R.id.tv_getcode:
                time.start();
                break;*/
            case R.id.tv_bind_card:
                showProgress();
                name = mEtAddCardName.getText().toString();
                bankname = mTvAddCardBankname.getText().toString();
                String num=met_banknumber.getText().toString();
                phone = met_add_card_phone.getText().toString();
                if (name.isEmpty()){
                    ToastUtils.showShort("请输入姓名");
                    hideProgress();
                    return;
                }
                if (num.isEmpty()){
                    ToastUtils.showShort("请输入银行卡号");
                    hideProgress();
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (num.equals(list.get(i).getPayNo())){
                        ToastUtils.showShort("该银行卡已绑定");
                        hideProgress();
                        return;
                    }
                }
                if (phone.isEmpty()){
                    ToastUtils.showShort("请输入手机号码");
                    hideProgress();
                    return;
                }
//                if (!MyUtils.isPhoneNum(phone)){
//                    ToastUtils.showShort("请输入正确的手机号码");
//                    return;
//                }
                isCardNo(num);
//                if ("".equals(name)||name==null){
//                    ToastUtils.showShort("请输入姓名");
//                }else if (mTvAddCardBankname.getText().toString().length() == 0 || met_banknumber.getText().toString().length() == 0 || met_add_card_phone.getText() == null) {
//                    Toast.makeText(this, "请选择银行并输入卡号和手机号", Toast.LENGTH_SHORT).show();
//                } else {
//                    mPresenter.AddorUpdateAccountPayInfo(userId, "Bank", mTvAddCardBankname.getText().toString(), met_banknumber.getText().toString(),name);
//                }
                break;
            case R.id.img_scan_card:
                // 生成intent对象
                Intent intent = new Intent(mActivity, CameraActivity.class);

                // 设置临时存储
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                // 调用拍摄银行卡的activity
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;

            default:
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
                    if (userInfo.getTrueName() == null) {
                        return;
                    } else {
//                         mTvAddCardName.setText(userInfo.getTrueName());
                        if (userInfo.getPhone() == null) {
                            return;
                        } else {
                            met_add_card_phone.setText(userInfo.getPhone());
                        }

                    }


                }
                break;

            default:
                break;
        }


    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    setResult(2000);
                    EventBus.getDefault().post("GetAccountPayInfoList");
                    Add_Card_Activity.this.finish();

                } else {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

                }
                hideProgress();
                break;
            default:
                break;
        }

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }

    /*根据卡号判断是否支持银行*/
    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1() && !baseResult.getData().getItem2().equals("")) {
                    if (mTvAddCardBankname!=null){
                        mTvAddCardBankname.setText(baseResult.getData().getItem2()); //绑定银行名
                    }

                } else {//不支持的银行

//                    if (met_banknumber!=null){
//                        met_banknumber.setText("");
//                    }
//                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
//                    dialog.setMessage("暂时不支持绑定该银行")
//                            //.setImageResId(R.mipmap.ic_launcher)
//                            .setTitle("提示")
//                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
//                        @Override
//                        public void onPositiveClick() {//拨打电话
//                            dialog.dismiss();
//                        }
//
//                        @Override
//                        public void onNegtiveClick() {//取消
//                            dialog.dismiss();
//                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
//                        }
//                    }).show();

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void DeleteAccountPayInfo(BaseResult<Data<String>> baseResult) {

    }

    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
//                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
//                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(),  "UwG0gIryO8ldGU8nZKxzlUMG", "cSGnPUjWwR7qDKhLGaDQbFBQRvsXtNb3");
    }
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (data==null){
            return;
        }
        // 获取调用参数
        String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
        // 通过临时文件获取拍摄的图片
        String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
        // 判断拍摄类型（通用，身份证，银行卡等）
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            // 判断是否是银行卡
            if (CameraActivity.CONTENT_TYPE_BANK_CARD.equals(contentType)){
                // 获取图片文件调用sdk数据接口，见数据接口说明
                // 银行卡识别参数设置
                BankCardParams param = new BankCardParams();
                param.setImageFile(new File(filePath));

// 调用银行卡识别服务
                OCR.getInstance(mActivity).recognizeBankCard(param, new OnResultListener<BankCardResult>() {
                    @Override
                    public void onResult(BankCardResult result) {
                        Log.d("=====",result.getBankName());
//                        ToastUtils.showShort(result.getBankName());
                        // 调用成功，返回BankCardResult对象
                        // 示例
//                        {
//                            "log_id": 3207866271;
//                            result: {
//                                "bank_card_number": "6226 2288 8888 8888",
//                                        "bank_card_type": 1,
//                                        "bank_name": "\U5de5\U5546\U94f6\U884c"
//                            };
//                        }
                        mTvAddCardBankname.setText(result.getBankName());
                        met_banknumber.setText(result.getBankCardNumber().replaceAll(" ",""));
                    }
                    @Override
                    public void onError(OCRError error) {
                        // 调用失败，返回OCRError对象
                        ToastUtils.showShort("识别失败");
                    }
                });
            }
        }
    }

}
