package com.ying.administrator.masterappdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.widget.BadgeView;
import com.ying.administrator.masterappdemo.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class Workbench_Fragment extends BaseFragment implements DefineView {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

     private String mContentText;
     private View view;
    public Workbench_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static Workbench_Fragment newInstance(String param1) {
        Workbench_Fragment fragment = new Workbench_Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_workbench, container, false);
            Log.d("ying","调用了onCreateView");
            initView();

        }

        return view;
    }

    @Override
    public void initView() {

        /*广告轮播*/
        Banner banner = view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        ArrayList<String> images=new ArrayList();
        images.add("https://img.zcool.cn/community/01f92b58b8dbd9a801219c77596518.jpg@1280w_1l_2o_100sh.jpg");
        images.add("https://img.zcool.cn/community/0135d858b8dbeba801219c7775d498.jpg@1280w_1l_2o_100sh.jpg");
        images.add("https://img.zcool.cn/community/01681658b8dbf8a801219c77ca9dff.jpg@1280w_1l_2o_100sh.jpg");
        images.add("https://img.zcool.cn/community/01a58d58b8dc28a801219c77d19ff8.jpg@1280w_1l_2o_100sh.jpg");
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.setDelayTime(3000); //
        banner.start();
        /*广告轮播*/

        //显示未读消息红点
        BadgeView badgeView =new BadgeView(getContext());
        badgeView.setTargetView(view.findViewById(R.id.img_forservice));
        badgeView.setBadgeCount(8);
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
