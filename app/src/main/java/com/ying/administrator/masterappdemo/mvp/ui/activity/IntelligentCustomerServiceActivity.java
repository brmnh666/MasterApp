package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.Chat;
import com.ying.administrator.masterappdemo.entity.SubAccount;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ChatAdapter;
import com.ying.administrator.masterappdemo.util.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntelligentCustomerServiceActivity extends BaseActivity implements View.OnClickListener, KeyboardUtil.OnGetSoftHeightListener, KeyboardUtil.OnSoftKeyWordShowListener {


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
    @BindView(R.id.fl_logo)
    FrameLayout mFlLogo;
    @BindView(R.id.rv_chat)
    RecyclerView mRvChat;
    @BindView(R.id.iv_speak)
    ImageView mIvSpeak;
    @BindView(R.id.et_question)
    EditText mEtQuestion;
    @BindView(R.id.iv_smiley_face)
    ImageView mIvSmileyFace;
    @BindView(R.id.iv_choose)
    ImageView mIvChoose;
    @BindView(R.id.ll_input_box)
    LinearLayout mLlInputBox;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_baby)
    LinearLayout mLlBaby;
    @BindView(R.id.ll_take_a_photo)
    LinearLayout mLlTakeAPhoto;
    @BindView(R.id.ll_album)
    LinearLayout mLlAlbum;
    @BindView(R.id.ll_short_video)
    LinearLayout mLlShortVideo;
    @BindView(R.id.ll_position)
    LinearLayout mLlPosition;
    @BindView(R.id.ll_features)
    LinearLayout mLlFeatures;
    @BindView(R.id.ll_mian)
    LinearLayout mLlMian;
    private List<Chat> chatList;
    public boolean isKeyboardVisible = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_intelligent_customer_service;
    }

    @Override
    protected void initData() {
        chatList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            chatList.add(new SubAccount());
//        }
        Chat f1=new Chat("hhhhhhhhhhhh");
        Chat f2=new Chat("hhhhhhhhhhhhhhhhhhhh");
        Chat f3=new Chat("hhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        Chat f4=new Chat("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        chatList.add(f1);
        chatList.add(f2);
        chatList.add(f3);
        chatList.add(f4);

        ChatAdapter chatAdapter = new ChatAdapter(R.layout.item_chat,chatList);
        mRvChat.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvChat.setAdapter(chatAdapter);

//        mLlMian.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//            }
//        });

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("智能客服");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
        mIvChoose.setOnClickListener(this);
        mEtQuestion.setOnClickListener(this);


    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.iv_choose:

                if (mLlFeatures.getVisibility() == View.GONE) {
                    InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
                    mLlFeatures.setVisibility(View.VISIBLE);

//                    if (isKeyboardVisible==false){
//                        mLlFeatures.setVisibility(View.VISIBLE);
//                    }else if (isKeyboardVisible==true) {
//                        mLlFeatures.setVisibility(View.VISIBLE);
//                        InputMethodManager imm = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
////                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
////                        if (imm.isActive()) {
////                            // 如果开启
////                            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
////
////                        }
//                    }

                } else if (mLlFeatures.getVisibility() == View.VISIBLE) {
                    mLlFeatures.setVisibility(View.GONE);
//                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                    if (im.isActive()) {
                        // 如果开启
                        im.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);

                    }

//                    imm.showSoftInput(getWindow().getDecorView(),InputMethodManager.SHOW_FORCED);
                }

                break;
            case R.id.et_question:
                mLlFeatures.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(getWindow().getDecorView(),InputMethodManager.SHOW_FORCED);
                break;
        }
    }


    //软键盘高度
    @Override
    public void onShowed(int height) {
        if (mLlFeatures.getHeight() != height) {

        }
    }

    //判断软件盘是否弹出
    @Override
    public void hasShow(boolean isShow) {
        isKeyboardVisible = isShow;
    }
}
