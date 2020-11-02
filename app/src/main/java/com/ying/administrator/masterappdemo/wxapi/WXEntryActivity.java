package com.ying.administrator.masterappdemo.wxapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.ying.administrator.masterappdemo.app.MyApplication;
import com.ying.administrator.masterappdemo.entity.WXAccessTokenBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";
    private static final int RETURN_MSG_TYPE_LOGIN = 1; //登录
    private static final int RETURN_MSG_TYPE_SHARE = 2; //分享
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        //这句没有写,是不能执行回调的方法的
        MyApplication.mWxApi.handleIntent(getIntent(), this);
    }


    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        Log.i(TAG, "onResp:------>");
        Log.i(TAG, "error_code:---->" + baseResp.errCode);
        int type = baseResp.getType(); //类型：分享还是登录
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                ToastUtils.showShort("拒绝授权微信登录");
//                ToastUtils.showToast(mContext, "");
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "";
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    message = "取消了微信登录";
                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    message = "取消了微信分享";
                }
                ToastUtils.showShort(message);
//                ToastUtils.showToast(mContext, message);
                break;
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                if (type == RETURN_MSG_TYPE_LOGIN) {
                    //用户换取access_token的code，仅在ErrCode为0时有效
                    String code = ((SendAuth.Resp) baseResp).code;
                    Log.i(TAG, "code:------>" + code);
                    EventBus.getDefault().post(code);
                    finish();
                    //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
//                    WXLoginUtils().getWXLoginResult(code, this);
//                    getAccessToken(code);

                } else if (type == RETURN_MSG_TYPE_SHARE) {
                    ToastUtils.showShort("微信分享成功");
//                    ToastUtils.showToast(mContext, "微信分享成功");
                }
                break;
        }
    }

    /**
     * 获取access_token：
     *
     * @param code 用户或取access_token的code，仅在ErrCode为0时有效
     */
    private void getAccessToken(final String code) {
        //接口
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd22da3eb42259071&secret=dac072c98881a324665bfbaa7f7e7c76&code=" + code + "&grant_type=authorization_code";

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
//                System.out.println(str);
                ToastUtils.showShort(str);
                Log.i(TAG, "onCompleted:-------->" + str);

                Gson gson = new Gson();
                WXAccessTokenBean result = gson.fromJson(str.replaceAll(" ", ""), WXAccessTokenBean.class);
                String access_token = result.getAccess_token(); //接口调用凭证
                String openid = result.getOpenid(); //授权用户唯一标识
                //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
                String unionid = result.getUnionid();
                Log.i(TAG, "access_token:----->" + access_token);
                Log.i(TAG, "openid:----->" + openid);
                Log.i(TAG, "unionid:----->" + unionid);
//                getWXUserInfo(access_token, openid, unionid);
            }
        });


//        Map<String, Object> params = new HashMap();
//        params.put("appid", "wxd22da3eb42259071");
//        params.put("secret", "dac072c98881a324665bfbaa7f7e7c76");
//        params.put("code", code);
//        params.put("grant_type", "authorization_code");
//        ApiRetrofit.getDefault().getWXAccessTokenBean("https://api.weixin.qq.com/sns/oauth2/access_token", params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WXAccessTokenBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted:-------->");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.i(TAG, "onError:-------->" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(WXAccessTokenBean wxAccessTokenBean) {
//                        Log.i(TAG, "onNext: ----->");
//                        String access_token = wxAccessTokenBean.getAccess_token(); //接口调用凭证
//                        String openid = wxAccessTokenBean.getOpenid(); //授权用户唯一标识
//                        //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
//                        String unionid = wxAccessTokenBean.getUnionid();
//                        Log.i(TAG, "access_token:----->" + access_token);
//                        Log.i(TAG, "openid:----->" + openid);
//                        Log.i(TAG, "unionid:----->" + unionid);
//                        getWXUserInfo(access_token, openid, unionid);
//                    }
//                });
    }


    /**
     * 获取微信登录，用户授权后的个人信息
     *
     * @param access_token
     * @param openid
     * @param unionid
     */
    private void getWXUserInfo(final String access_token, final String openid, final String unionid) {

        //接口
        String path = "https://api.weixin.qq.com//sns/userinfo?access_token="+access_token+"&openid=" + openid ;

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
//                System.out.println(str);
                ToastUtils.showShort(str);
                Log.i(TAG, ":-------->" + str);

//                Gson gson = new Gson();
//                WXAccessTokenBean result = gson.fromJson(str.replaceAll(" ", ""), WXAccessTokenBean.class);
//                String access_token = result.getAccess_token(); //接口调用凭证
//                String openid = result.getOpenid(); //授权用户唯一标识
//                //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
//                String unionid = result.getUnionid();
//                Log.i(TAG, "access_token:----->" + access_token);
//                Log.i(TAG, "openid:----->" + openid);
//                Log.i(TAG, "unionid:----->" + unionid);
//                getWXUserInfo(access_token, openid, unionid);
//                if (result.isValidated()){
//                    mPresenter.AddorUpdateAccountPayInfo(userId, phone, bankname, cardNo,name);
//                }else{
//                    ToastUtils.showShort("请输入有效的银行卡号");
//                    hideProgress();
//                }


            }
        });
//        Map<String, Object> params = new HashMap();
//        params.put("access_token", access_token);
//        params.put("openid", openid);
//        HttpUtils.getWXUserInfoBean(URLConstant.URL_WX_BASE, params)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WXUserInfoBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "getWXUserInfo:--------> onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.i(TAG, "getWXUserInfo:--------> onError" + throwable.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(WXUserInfoBean wxUserInfoBean) {
//                        Log.i(TAG, "getWXUserInfo:--------> onNext");
//                        String country = wxUserInfoBean.getCountry(); //国家
//                        String province = wxUserInfoBean.getProvince(); //省
//                        String city = wxUserInfoBean.getCity(); //市
//                        String nickname = wxUserInfoBean.getNickname(); //用户名
//                        int sex = wxUserInfoBean.getSex(); //性别
//                        String headimgurl = wxUserInfoBean.getHeadimgurl(); //头像url
//                        Log.i(TAG, "country:-------->" + country);
//                        Log.i(TAG, "province:-------->" + province);
//                        Log.i(TAG, "city:-------->" + city);
//                        Log.i(TAG, "nickname:-------->" + nickname);
//                        Log.i(TAG, "sex:-------->" + sex);
//                        Log.i(TAG, "headimgurl:-------->" + headimgurl);
//                    }
//                });
    }
}
