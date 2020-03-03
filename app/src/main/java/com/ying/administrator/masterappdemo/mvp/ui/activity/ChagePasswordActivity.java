package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.InfoManageContract;
import com.ying.administrator.masterappdemo.mvp.model.InfoMangeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.InfoManagePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChagePasswordActivity extends BaseActivity<InfoManagePresenter, InfoMangeModel> implements View.OnClickListener, InfoManageContract.View {


    @BindView(R.id.tv_change_password)
    TextView mTv_change_password;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_new_password_again)
    EditText mEtNewPasswordAgain;


    private String old_password;
    private String new_password;
    private String new_password_again;
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userID;
    private UserInfo.UserInfoDean userInfoDean = new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData() {
        userID = spUtils.getString("userName");

        mPresenter.GetUserInfoList(userID, "1");
    }

    @Override
    public void initView() {

        mTvTitle.setText("修改密码");

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTv_change_password.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                ChagePasswordActivity.this.finish();
                break;
            //修改密码
            case R.id.tv_change_password:
                old_password = mEtOldPassword.getText().toString();
                new_password = mEtNewPassword.getText().toString();
                new_password_again = mEtNewPasswordAgain.getText().toString();

                if (old_password.equals("") || new_password.equals("") || new_password_again.equals("")) {
                    Toast.makeText(ChagePasswordActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {

                    // Log.d("用户的密码为11",userInfoDean.getPassWord());
                    if (!old_password.equals(userInfoDean.getPassWord())) {
                        Toast.makeText(ChagePasswordActivity.this, "请输入正确的密码", Toast.LENGTH_LONG).show();

                    } else {

                        if (!new_password.equals(new_password_again)) { //两次输入的账号密码不一致
                            Toast.makeText(ChagePasswordActivity.this, "两次输入的密码不一致", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(ChagePasswordActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                            mPresenter.UpdatePassword(userID, new_password);
                        }

                    }

                }


                break;
            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfoDean = baseResult.getData().getData().get(0);

                break;
            default:
                break;
        }
    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult) {

    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    startActivity(new Intent(ChagePasswordActivity.this, Login_New_Activity.class));
                    spUtils.put("passWord", "");
                    spUtils.put("isLogin", false);

                    ActivityUtils.finishAllActivities();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {

    }
}
