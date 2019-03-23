package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.model.CardModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CardPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyCardAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardList_Activity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_add_card)
    TextView mTvAddCard;
    @BindView(R.id.rv_card_list)
    RecyclerView mRvCardList;


private String userId;
private MyCardAdapter myCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_cardlist;
    }

    @Override
    protected void initData() {
        SPUtils spUtils=SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mTvAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_actionbar_return:
                CardList_Activity.this.finish();
                break;
            case R.id.tv_add_card:
                startActivityForResult(new Intent(this,Add_Card_Activity.class),2002);
                //startActivity(new Intent(this,Add_Card_Activity.class));
                break;



        }

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {

    }


    /*获取银行卡*/
    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
     switch (baseResult.getStatusCode()){
         case 200:
             if (baseResult.getData()==null){
                 return;
             }else {
                 mRvCardList.setLayoutManager(new LinearLayoutManager(mActivity));
                 myCardAdapter=new MyCardAdapter(R.layout.item_mycard,baseResult.getData(),mActivity);
                 mRvCardList.setAdapter(myCardAdapter);
             }
             break;
             default:
                 break;
     }
    }

    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2000){
          if (requestCode==2002){//添加卡的请求
              mPresenter.GetAccountPayInfoList(userId);
          }
        }
    }
}
