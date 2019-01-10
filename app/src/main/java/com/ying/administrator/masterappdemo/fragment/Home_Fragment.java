package com.ying.administrator.masterappdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ying.administrator.masterappdemo.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment.Pending_appointment_fragment;
import com.ying.administrator.masterappdemo.widget.BadgeView;

public class Home_Fragment extends BaseFragment implements DefineView {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

     private String mContentText;
     private View view;
     private LinearLayout ll_home_have_appointment;//已预约
     private LinearLayout ll_home_inservice;
     private LinearLayout ll_home_pending_appointment;
     private LinearLayout ll_home_forcollection;
     private LinearLayout ll_home_quality;
     private LinearLayout ll_home_return;
     private LinearLayout ll_home_finished;
     private LinearLayout ll_home_take;
     public Home_Fragment() {
        // Required empty public constructor
    }

    public static Home_Fragment newInstance(String param1) {
        Home_Fragment fragment = new Home_Fragment();
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
            view = inflater.inflate(R.layout.fragment_home, container, false);
            Log.d("ying","调用了onCreateView");
            initView();
            initListener();

        }

        return view;
    }

    @Override
    public void initView() {
        ll_home_have_appointment=view.findViewById(R.id.ll_home_have_appointment);
        ll_home_inservice=view.findViewById(R.id.ll_home_inservice);
        ll_home_pending_appointment=view.findViewById(R.id.ll_home_pending_appointment);
        ll_home_forcollection=view.findViewById(R.id.ll_home_forcollection);
        ll_home_quality=view.findViewById(R.id.ll_home_quality);
        ll_home_return=view.findViewById(R.id.ll_home_return);
        ll_home_finished=view.findViewById(R.id.ll_home_finished);
        ll_home_take=view.findViewById(R.id.ll_home_take);


    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        ll_home_have_appointment.setOnClickListener(new CustomListnear());
        ll_home_inservice.setOnClickListener(new CustomListnear());
        ll_home_pending_appointment.setOnClickListener(new CustomListnear());
        ll_home_forcollection.setOnClickListener(new CustomListnear());
        ll_home_quality.setOnClickListener(new CustomListnear());
        ll_home_return.setOnClickListener(new CustomListnear());
        ll_home_finished.setOnClickListener(new CustomListnear());
        ll_home_take.setOnClickListener(new CustomListnear());


    }

    @Override
    public void bindData() {

    }

    /*自定义监听*/
    public class CustomListnear implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_home_have_appointment://已预约
                    Intent intent =new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent.putExtra("intent","have_appointment");
                    startActivity(intent);

                    break;
                case R.id.ll_home_inservice:  //服务中
                    Intent intent1=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent1.putExtra("intent","in_service");
                    startActivity(intent1);
                    break;
                case R.id.ll_home_pending_appointment: //待预约
                    Intent intent2=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent2.putExtra("intent","pending_appointment");
                    startActivity(intent2);
                    break;
                case R.id.ll_home_forcollection:
                    break;
                case R.id.ll_home_quality:
                    break;
                case R.id.ll_home_return://待返件
                    Intent intent5=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent5.putExtra("intent","return");
                    startActivity(intent5);
                    break;
                case R.id.ll_home_finished:
                    break;
                case R.id.ll_home_take:
                    break;
                    default:
                        break;


            }


        }
    }
}
