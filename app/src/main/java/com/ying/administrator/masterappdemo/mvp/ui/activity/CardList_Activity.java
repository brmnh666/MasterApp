package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
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
    @BindView(R.id.ll_add_card)
    LinearLayout mLlAddCard;
    @BindView(R.id.tv_tips)
    TextView mTvTips;


    private String userId;
    private MyCardAdapter myCardAdapter;
    private List<BankCard> list=new ArrayList<>();

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
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void initView() {
        mRvCardList.setLayoutManager(new LinearLayoutManager(mActivity));
        myCardAdapter = new MyCardAdapter(R.layout.item_mycard, list, mActivity);
        mRvCardList.setAdapter(myCardAdapter);
//        myCardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
//                switch (view.getId()){
//                    case R.id.ll_card:
//                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
//                        dialog.setMessage("是否删除该银行卡")
//                                //.setImageResId(R.mipmap.ic_launcher)
//                                .setTitle("提示")
//                                .setSingle(true)
//                                /*.setNegtive("重设密码")*/
//                                .setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
//                                    @Override
//                                    public void onPositiveClick() {//重新登录
//                                        mPresenter.DeleteAccountPayInfo(userId,list.get(position).getPayInfoCode(),list.get(position).getPayInfoName(),list.get(position).getPayNo(),list.get(position).getPayName(),"N");
//                                        dialog.dismiss();
//                                    }
//
//                                    @Override
//                                    public void onNegtiveClick() {
//                                        dialog.dismiss();
//                                    }
//
//                           /* @Override
//                            public void onNegtiveClick() {//取消
//                                startActivity(new Intent(mActivity, ForgetPasswordActivity.class));
//                                getActivity().finish();
//                                dialog.dismiss();
//                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
//                            }*/
//                                }).show();
//                        break;
//                }
//            }
//        });
        myCardAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
                        @Override
                        public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
                            switch (view.getId()){
                                case R.id.ll_card:
                                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                                    dialog.setMessage("是否删除该银行卡")
                                            //.setImageResId(R.mipmap.ic_launcher)
                                            .setTitle("提示")
                                            .setSingle(true)
                                            /*.setNegtive("重设密码")*/
                                            .setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                                @Override
                                                public void onPositiveClick() {//重新登录
                                                    mPresenter.DeleteAccountPayInfo(userId,list.get(position).getPayInfoCode(),list.get(position).getPayInfoName(),list.get(position).getPayNo(),list.get(position).getPayName(),"N",list.get(position).getAccountPayID());
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void onNegtiveClick() {
                                                    dialog.dismiss();
                                                }

                           /* @Override
                            public void onNegtiveClick() {//取消
                                startActivity(new Intent(mActivity, ForgetPasswordActivity.class));
                                getActivity().finish();
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }*/
                                            }).show();
                                    break;
                            }

                            return true;
                        }
                    });
    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mTvAddCard.setOnClickListener(this);
        mLlAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_actionbar_return:
                CardList_Activity.this.finish();
                break;
            case R.id.ll_add_card:
            case R.id.tv_add_card:
                Intent intent=new Intent(this, Add_Card_Activity.class);
                intent.putExtra("cardlist", (Serializable) list);
                startActivityForResult(intent, 2002);
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
    public void GetAccountPayInfoList(final BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list.clear();
                if (baseResult.getData() == null) {
                    return;
                } else {
                    for (int i = 0; i < baseResult.getData().size(); i++) {
                        if ("Y".equals(baseResult.getData().get(i).getIsUse())){
                            list.add(baseResult.getData().get(i));
                        }
                    }

                    myCardAdapter.setNewData(list);
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
    public void DeleteAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort("删除成功");
                mPresenter.GetAccountPayInfoList(userId);
                EventBus.getDefault().post("card");
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2000) {
            if (requestCode == 2002) {//添加卡的请求
                mPresenter.GetAccountPayInfoList(userId);
            }
        }
    }
}
