package com.ying.administrator.masterappdemo.widget.sign;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.PayResult;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.FileSizeUtil;
import com.ying.administrator.masterappdemo.v3.bean.GetContractSigningDepositMoneyResult;
import com.ying.administrator.masterappdemo.v3.bean.GetSignContractManageResult;
import com.ying.administrator.masterappdemo.v3.bean.SaveAutographResult;
import com.ying.administrator.masterappdemo.v3.bean.UploadAutographPicUrlResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.SignPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SignContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.SignModel;
import com.ying.administrator.masterappdemo.widget.CustomWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignActivity extends BaseActivity<SignPresenter, SignModel> implements SignContract.View, View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.webview)
    CustomWebView mWebview;
    @BindView(R.id.signature_pad)
    SignatureView mSignaturePad;
    @BindView(R.id.clear_button)
    Button mClearButton;
    @BindView(R.id.save_button)
    Button mSaveButton;
    @BindView(R.id.buttons_container)
    LinearLayout mButtonsContainer;
    @BindView(R.id.btn_recharge)
    Button mBtnRecharge;
    @BindView(R.id.fl_signature_pad)
    FrameLayout mFlSignaturePad;
    private ArrayList<String> permissions;
    private int size;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private String userID;
    private String value;
    private String orderinfo;
    private WXpayInfo wXpayInfo;
    private SPUtils spUtils;
    private IWXAPI api;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("电子合同");

        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        api = WXAPIFactory.createWXAPI(this, "wxd22da3eb42259071");

        mPresenter.GetSignContractManage();
        mSignaturePad.setMinWidth(5);

//        mSignaturePad.setBackgroundResource(R.drawable.signbg);

        mSignaturePad.setOnSignedListener(new SignatureView.OnSignedListener() {
            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
        mBtnRecharge.setOnClickListener(this);
    }

    private void webview(String content) {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "\n" +
                "\t<title>detail</title>\n" +
                "\n" +
                "\t<style>body{border:0;padding:0;margin:0;}img{max-width:100%;border:0;display:block;vertical-align: middle;padding:0;margin:0;}p{border:0;padding:0;margin:0;}div{border:0;padding:0;margin:0;}</style>\n" +
                "</head>"
                + "<body>"
                + content + "</body>" + "</html>";
        mWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        WebSettings webSettings = mWebview.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;

                try {
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToPng(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        stream.close();
    }

    public boolean addSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.png", System.currentTimeMillis()));
            saveBitmapToPng(signature, photo);
            uploadImg(photo);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            SignActivity.this.sendBroadcast(mediaScanIntent);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 支付宝还是微信
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.pay_layout, null);
        Button alipay_btn = popupWindow_view.findViewById(R.id.alipay_btn);
        Button wechat_btn = popupWindow_view.findViewById(R.id.wechat_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);

        alipay_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                mPresenter.GetOrderStr(userID, value);
                mPopupWindow.dismiss();
            }
        });
        wechat_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                mPresenter.GetWXOrderStr(userID, value);
                mPopupWindow.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
//            popupWindow.showAsDropDown(tv, 0, 10);
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge:
                showMoneyDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.clear_button:
                mSignaturePad.clear();
                break;
            case R.id.save_button:
                if (requestPermissions()) {
                    save();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10003);
                }
                break;
        }
    }

    private void save() {
        Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
//        MyUtils.dealBackground(signatureBitmap);
        addSignatureToGallery(signatureBitmap);
        System.out.println("签名文件大小："+FileSizeUtil.getFileOrFilesSize(MyUtils.getFile(signatureBitmap).getPath(),FileSizeUtil.SIZETYPE_MB));
//        uploadImg(MyUtils.getFile(signatureBitmap));
//        if (addSignatureToGallery(signatureBitmap)) {
//            Toast.makeText(SignActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(SignActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
//        }
    }

    public void uploadImg(File f) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
        MultipartBody requestBody = builder.build();
        mPresenter.UploadAutographPicUrl(requestBody);
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 10003:
                if (size == grantResults.length) {//允许
                    save();
                } else {//拒绝
                    toSetting();
                }
                break;
            default:
                break;

        }
    }

    //设置权限
    private void toSetting() {

        new AlertDialog.Builder(this).setTitle("存储权限未授权")
                .setMessage("是否前往设置权限？")
                //  取消选项
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                //  确认选项
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到手机原生设置页面
                        MyUtils.toSelfSetting(mActivity);
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void GetSignContractManage(GetSignContractManageResult baseResult) {
        if (baseResult.getStatusCode() == 200) {
            if (baseResult.getData().isStatus()) {
                webview(baseResult.getData().getData().getContract());
                int state = baseResult.getData().getData().getState();
                value = Double.toString(baseResult.getData().getData().getSincerityMoney());
//                State: 0--未签约,1--待审核，2--审核通过待支付，4--签约完成
                if (state == 0) {
                    mFlSignaturePad.setVisibility(View.VISIBLE);
                    mButtonsContainer.setVisibility(View.VISIBLE);
                } else {
                    mFlSignaturePad.setVisibility(View.GONE);
                    mButtonsContainer.setVisibility(View.GONE);
                }
                if (state == 2) {
                    showMoneyDialog();
                    mBtnRecharge.setText("缴纳诚意金" + value + "元");
                    mBtnRecharge.setVisibility(View.VISIBLE);
                } else {
                    mBtnRecharge.setVisibility(View.GONE);
                }
            } else {
                MyUtils.showToast(mActivity, baseResult.getData().getMsg());
            }
        }
    }

    private void showMoneyDialog() {
        new AlertDialog.Builder(this).setTitle("缴纳诚意金")
                .setMessage("您的签名已审核通过，是否缴纳" + value + "元诚意金？")
                //  取消选项
                .setNegativeButton("查看合同", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 关闭dialog
                        dialogInterface.dismiss();
                    }
                })
                //  确认选项
                .setPositiveButton("缴纳诚意金", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showPopupWindow();
                        // 关闭dialog
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void UploadAutographPicUrl(UploadAutographPicUrlResult baseResult) {
        if (baseResult.getStatusCode() == 200) {
            if (baseResult.getData().isItem1()) {
                mPresenter.SaveAutograph(baseResult.getData().getItem2());
            } else {
                MyUtils.showToast(mActivity, baseResult.getData().getItem2());
            }
        }
    }

    @Override
    public void SaveAutograph(SaveAutographResult baseResult) {
        if (baseResult.getStatusCode() == 200) {
            if (baseResult.getData().isStatus()) {
                MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                mPresenter.GetSignContractManage();
            } else {
                MyUtils.showToast(mActivity, baseResult.getData().getMsg());
            }
        }
    }

    @Override
    public void GetContractSigningDepositMoney(GetContractSigningDepositMoneyResult baseResult) {

    }

    @Override
    public void GetSigningSuccessContract(GetSignContractManageResult baseResult) {

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
    public void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo != null) {
                        WXpay();
                        EventBus.getDefault().post("GetUserInfoList");
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
                PayTask alipay = new PayTask(SignActivity.this);
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
        api.registerApp("wxd22da3eb42259071");
        PayReq req = new PayReq();
        req.appId = wXpayInfo.getAppid();
        req.partnerId = wXpayInfo.getPartnerid();
        req.prepayId = wXpayInfo.getPrepayid();
        req.nonceStr = wXpayInfo.getNoncestr();
        req.timeStamp = wXpayInfo.getTimestamp();
        req.packageValue = wXpayInfo.getPackageX();
        req.sign = wXpayInfo.getSign();
        //req.extData			= "app data"; // optional
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
                        EventBus.getDefault().post("GetUserInfoList");
                        SignActivity.this.finish();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
