package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.mvp.contract.ArticleContract;
import com.ying.administrator.masterappdemo.mvp.model.ArticleModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ArticlePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ArticleActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends BaseLazyFragment<ArticlePresenter, ArticleModel> implements ArticleContract.View, View.OnClickListener {


    @BindView(R.id.ll_platform_policy)
    LinearLayout mLlPlatformPolicy;
    @BindView(R.id.ll_news)
    LinearLayout mLlNews;
    @BindView(R.id.ll_order_must_read)
    LinearLayout mLlOrderMustRead;
    private String param1;
    private String param2;
    private Intent intent;

    public NoticeFragment() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString(ARG_PARAM1);
            param2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlPlatformPolicy.setOnClickListener(this);
        mLlNews.setOnClickListener(this);
        mLlOrderMustRead.setOnClickListener(this);
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }

    @Override
    public void onClick(View v) {
        intent = new Intent(mActivity, ArticleActivity.class);
        switch(v.getId()){
            case R.id.ll_platform_policy:
                intent.putExtra("CategoryID","8");
                break;
            case R.id.ll_news:
                intent.putExtra("CategoryID","9");
                break;
            case R.id.ll_order_must_read:
                intent.putExtra("CategoryID","10");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
    }
}
