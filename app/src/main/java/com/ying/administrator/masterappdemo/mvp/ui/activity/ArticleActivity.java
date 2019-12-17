package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.contract.ArticleContract;
import com.ying.administrator.masterappdemo.mvp.model.ArticleModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ArticlePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ArticleAdapter;

import java.util.List;

import butterknife.BindView;

public class ArticleActivity extends BaseActivity<ArticlePresenter, ArticleModel> implements View.OnClickListener, ArticleContract.View {


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
    @BindView(R.id.rv_article)
    RecyclerView mRvArticle;
    private List<Article.DataBean> list;
    private String CategoryID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initData() {

        CategoryID =getIntent().getStringExtra("CategoryID");
        switch(CategoryID){
            case "7":
                mTvActionbarTitle.setText("系统消息");
                break;
            case "8":
                mTvActionbarTitle.setText("平台政策");
                break;
            case "9":
                mTvActionbarTitle.setText("平台新闻");
                break;
            case "10":
                mTvActionbarTitle.setText("接单必读");
                break;
            default:
                break;
        }
        mPresenter.GetListCategoryContentByCategoryID(CategoryID,"1","999");
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch(baseResult.getStatusCode()){
            case 200:
                list =baseResult.getData().getData();
                ArticleAdapter articleAdapter=new ArticleAdapter(R.layout.item_order_must_read, list);
                mRvArticle.setLayoutManager(new LinearLayoutManager(mActivity));
                articleAdapter.setEmptyView(getEmptyView());
                mRvArticle.setAdapter(articleAdapter);
                articleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent=new Intent(mActivity,WebActivity.class);
                        intent.putExtra("Url",list.get(position).getContent());
                        intent.putExtra("Title",list.get(position).getTitle());
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {

    }
}
