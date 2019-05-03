package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChageUserNameActivity extends BaseActivity<InfoManagePresenter, InfoMangeModel> implements InfoManageContract.View,View.OnClickListener {

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.et_username)
    EditText mEtUsername;

    @BindView(R.id.tv_change_username)
    TextView mTv_change_username;
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userID;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_username;
    }

    @Override
    protected void initData() {
        userID=spUtils.getString("userName");
    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("修改店铺名称");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTv_change_username.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                ChageUserNameActivity.this.finish();
                break;
            case R.id.tv_change_username:
                  if (mEtUsername.getText().toString().equals("")){//判断输入是否为空
                      Toast.makeText(ChageUserNameActivity.this,"昵称不能为空",Toast.LENGTH_LONG).show();
                  }else {
                     mPresenter.UpdateAccountNickName(userID,mEtUsername.getText().toString());
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

    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult) {

    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {
          switch (baseResult.getStatusCode()){
              case 200:
                  //修改成功
                  if (baseResult.getData().isItem1()){
                      Toast.makeText(ChageUserNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                      EventBus.getDefault().post("GetUserInfoList");
                      ChageUserNameActivity.this.finish();
                  }else {
                      Toast.makeText(ChageUserNameActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                  }

                  break;

                  default:
                      break;
          }
    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {

    }
}
