package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.InfoManageContract;
import com.ying.administrator.masterappdemo.mvp.model.InfoMangeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.InfoManagePresenter;

public class Personal_Information_Activity extends BaseActivity<InfoManagePresenter, InfoMangeModel> implements InfoManageContract.View {
    private LinearLayout ll_return;
    private TextView tv_actionbar_title;
    private ImageView img_actionbar_message;
    private String userID;//用户id
    private ImageView iv_avatar;//头像
    private TextView tv_nickname;//昵称
    private TextView tv_name;//真实姓名
    private TextView tv_certification;//已认证
    private TextView tv_un_certification;//未认证
    private TextView tv_phone;//手机号
    private TextView tv_id_card;//身份证
    private TextView tv_shop_address;//身份证
    private ImageView img_male_select;//男图选中
    private ImageView img_male_unselect;//男图未选中
    private ImageView img_female_select;//女图选中
    private ImageView img_female_unselect;//女图未选中


    SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initData() {
        userID=spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID,"1");

    }

    @Override
    protected void initView() {
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        tv_actionbar_title.setText("个人信息管理");
        img_actionbar_message=findViewById(R.id.img_actionbar_message);
        img_actionbar_message.setVisibility(View.INVISIBLE);
        ll_return=findViewById(R.id.ll_return);
        iv_avatar=findViewById(R.id.iv_avatar);//头像
        tv_nickname=findViewById(R.id.tv_nickname);//昵称
        tv_name=findViewById(R.id.tv_name);//真实姓名
        tv_certification=findViewById(R.id.tv_certification);//已认证
        tv_un_certification=findViewById(R.id.tv_un_certification);//未认证
        tv_phone=findViewById(R.id.tv_phone);//手机号
        tv_id_card=findViewById(R.id.tv_id_card);//身份证
        tv_shop_address=findViewById(R.id.tv_shop_address);//店铺地址
        img_male_select=findViewById(R.id.img_male_select);
        img_male_unselect=findViewById(R.id.img_male_unselect);
        img_female_select=findViewById(R.id.img_female_select);
        img_female_unselect=findViewById(R.id.img_female_unselect);

    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickListnear());
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo=baseResult.getData().getData().get(0);
                /*设置头像*/
                if (userInfo.getAvator()==null){//显示默认头像
                    return;
                }else {
                    Glide.with(this)
                            .load(Config.HEAD_URL+userInfo.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(iv_avatar);
                }
                /*设置昵称*/
                if (userInfo.getNickName()==null){//测试账号可能出现昵称为null的情况 这里暂不处理
                    return;

                }else {
                    tv_nickname.setText(userInfo.getNickName());
                }
                /*真实姓名*/
                if (userInfo.getTrueName()==null){ //如果为空说明未认证
                    tv_name.setVisibility(View.INVISIBLE);
                    tv_certification.setVisibility(View.GONE);
                    tv_un_certification.setVisibility(View.VISIBLE);

                }else {
                    tv_name.setText(userInfo.getTrueName());
                    tv_certification.setVisibility(View.VISIBLE);
                    tv_un_certification.setVisibility(View.GONE);
                }
                /*手机号*/
                if (userInfo.getPhone()==null){
                    tv_phone.setText("");
                }else {
                    tv_phone.setText(userInfo.getPhone());
                }
                /*身份证*/
                if (userInfo.getIDCard()==null){
                    tv_id_card.setText("");
                }else {
                    tv_id_card.setText(userInfo.getIDCard());
                }
                /*店铺地址*/
                if (userInfo.getAddress()==null){
                    tv_shop_address.setText("");
                }else {
                    tv_shop_address.setText(userInfo.getAddress());
                }
                /*性别*/
                if (userInfo.getSex()==null){
                    img_male_unselect.setVisibility(View.VISIBLE);
                    img_male_select.setVisibility(View.GONE);
                    img_female_select.setVisibility(View.GONE);
                    img_female_unselect.setVisibility(View.VISIBLE);
                }else if (userInfo.getSex().equals("男")){
                    img_male_unselect.setVisibility(View.GONE);
                    img_male_select.setVisibility(View.VISIBLE);
                    img_female_unselect.setVisibility(View.VISIBLE);
                    img_female_select.setVisibility(View.GONE);
                }else {//女
                    img_male_unselect.setVisibility(View.VISIBLE);
                    img_male_select.setVisibility(View.GONE);
                    img_female_unselect.setVisibility(View.GONE);
                    img_female_select.setVisibility(View.VISIBLE);

                }






                break;
                default:
                    break;
        }
    }

    public class CustomOnclickListnear implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
                    Personal_Information_Activity.this.finish();
                    break;
            }
        }
    }
}
