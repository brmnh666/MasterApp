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
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.TransactionMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    private View view;
    private String mContentText;
    private LinearLayout mll_work_order_message;
    private LinearLayout mll_transaction_news;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null){

            view = inflater.inflate(R.layout.fragment_information, container, false);
           initView();
            initListner();
            
        }


        return view;
    }

    private void initView() {
        mll_work_order_message=view.findViewById(R.id.ll_work_order_message);
        mll_transaction_news=view.findViewById(R.id.ll_transaction_news);
    }

    private void initListner() {
        mll_work_order_message.setOnClickListener(this);
        mll_transaction_news.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.ll_work_order_message://工单消息

             startActivity(new Intent(getActivity(), OrderMessageActivity.class));
            break;
        case R.id.ll_transaction_news://交易信息
            startActivity(new Intent(getActivity(), TransactionMessageActivity.class));
            break;
            default:
                break;


    }
    }
}
