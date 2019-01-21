package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.magic_news)
    MagicIndicator mMagicNews;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPagerNews;
    Unbinder unbinder;

    private String mContentText;
    private String[] mTitleDataList = new String[]{
            "消息", "通知"
    };
    private CommonNavigator commonNavigator;
    private List<Fragment> mNewsList;


    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    public void init(){
        mNewsList = new ArrayList<>();
        int i=0;
        mNewsList.add(InformationFragment.newInstance(mTitleDataList[i],""));
        int a=1;
        mNewsList.add(NoticeFragment.newInstance(mTitleDataList[a],""));
//        for (int i = 0; i < 2; i++) {
//            mNewsList.add(MessageFragment.newInstance(mTitleDataList[i],""));
//        }
        mViewPagerNews.setOffscreenPageLimit(mTitleDataList.length);
        mViewPagerNews.setAdapter(new MyPagerAdapter(this.getFragmentManager(),mTitleDataList,mNewsList));
        commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.WHITE);
                colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
                colorTransitionPagerTitleView.setTextSize(20);
                colorTransitionPagerTitleView.setLeft(10000);
                colorTransitionPagerTitleView.setText(mTitleDataList[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPagerNews.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        mMagicNews.setBackgroundResource(R.color.color_custom_01);
        mMagicNews.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(mActivity, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));

//        ViewPagerHelper.bind(mMagicNews, mViewPagerNews);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
