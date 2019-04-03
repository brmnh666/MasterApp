package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.mvp.contract.ArticleContract;
import com.ying.administrator.masterappdemo.mvp.model.ArticleModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ArticlePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ArticleActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.TransactionMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends BaseLazyFragment<ArticlePresenter, ArticleModel> implements View.OnClickListener, ArticleContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tv_work_order_message)
    TextView mTvWorkOrderMessage;
    @BindView(R.id.ll_work_order_message)
    LinearLayout mLlWorkOrderMessage;
    @BindView(R.id.tv_trading_information)
    TextView mTvTradingInformation;
    @BindView(R.id.ll_transaction_news)
    LinearLayout mLlTransactionNews;
    @BindView(R.id.tv_system_information)
    TextView mTvSystemInformation;
    @BindView(R.id.ll_announcement)
    LinearLayout mLlAnnouncement;
    Unbinder unbinder;

    private View view;
    private String mContentText;

    public InformationFragment() {
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
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        if (view == null) {
//
//            view = inflater.inflate(R.layout.fragment_information, container, false);
//            initListner();
//
//        }
//
//
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
        mLlWorkOrderMessage.setOnClickListener(this);
        mLlTransactionNews.setOnClickListener(this);
        mLlAnnouncement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_work_order_message://工单消息

                startActivity(new Intent(getActivity(), OrderMessageActivity.class));
                break;
            case R.id.ll_transaction_news://交易信息
                startActivity(new Intent(getActivity(), TransactionMessageActivity.class));
                break;
            case R.id.ll_announcement:
               Intent intent = new Intent(mActivity, ArticleActivity.class);
                intent.putExtra("CategoryID","7");
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
    }
}
