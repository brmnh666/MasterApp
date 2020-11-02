package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.PreviewActivity;
import com.dmcbig.mediapicker.entity.Media;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.PicResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.MessageContract;
import com.ying.administrator.masterappdemo.mvp.model.MessageModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MessagePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.LeaveMessageAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.PicAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.util.imageutil.ImageCompress;
import com.ying.administrator.masterappdemo.v3.bean.GetLeaveMsgListResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LeaveMsgActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener, MessageContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.message_rv)
    RecyclerView mMessageRv;
    @BindView(R.id.et_message)
    EditText mEtMessage;
    @BindView(R.id.rv_icons)
    RecyclerView mRvIcons;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    private String userID;
    private String orderId;
    private WorkOrder.DataBean data;
    private List<GetLeaveMsgListResult.DataBeanX.DataBean> list = new ArrayList<>();
    private ArrayList<String> piclist = new ArrayList<>();
    private ArrayList<String> selectpiclist = new ArrayList<>();
    private ArrayList<String> successpiclist = new ArrayList<>();
    private ArrayList<Media> select = new ArrayList<>();
    private LeaveMessageAdapter leaveMessageAdapter;
    private ArrayList<String> permissions;

    private HashMap<Integer, File> img = new HashMap<>();

    private Bitmap bitmap;
    private PicAdapter picAdapter;
    private int k;
    private String message;
    private int size;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("留言");

        SPUtils spUtils = SPUtils.getInstance("token");
        //获取用户id
        userID = spUtils.getString("userName");
        orderId = getIntent().getStringExtra("orderId");
        showProgress();
        mPresenter.GetLeaveMsgList(orderId);

        leaveMessageAdapter = new LeaveMessageAdapter(R.layout.leave_message_item, list);
        mMessageRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mMessageRv.setAdapter(leaveMessageAdapter);
        leaveMessageAdapter.setEmptyView(getMessageEmptyView());
//        leaveMessageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()) {
//                    case R.id.img:
//                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
//                        intent.putExtra("PhotoUrl", Config.Leave_Message_URL);
//                        startActivity(intent);
//                        break;
//                }
//            }
//        });

        piclist.add("add");
        picAdapter = new PicAdapter(R.layout.item_picture, piclist);
        mRvIcons.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvIcons.setAdapter(picAdapter);
        picAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img:
                        if ("add".equals(adapter.getItem(position))) {
                            if (requestPermissions()) {
                                goImage();
                            } else {
                                requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                            }
                        } else {
                            goPreviewActivity();
                        }
                        break;
                    case R.id.ll_delete:
                        select.remove(position);
                        ArrayList<String> list = new ArrayList<>();
                        Log.i("select", "select.size" + select.size());
                        for (Media media : select) {
                            list.add(media.path);
                            Log.i("media", media.path);
                            Log.e("media", "s:" + media.size);
                        }
                        loadAdpater(list);
                        break;
                }
            }
        });
    }

    private void loadAdpater(ArrayList<String> paths) {
        piclist.clear();
        piclist.addAll(paths);
        selectpiclist.clear();
        for (int i = 0; i < paths.size(); i++) {
            selectpiclist.add(ImageCompress.compressImage(paths.get(i), Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg", 80));
        }
        if (piclist.size() != 5) {
            piclist.add("add");
        }
        System.out.println(selectpiclist);
        picAdapter.setNewData(piclist);
    }

    void goImage() {
        Intent intent = new Intent(mActivity, PickerActivity.class);
        intent.putExtra(PickerConfig.SELECT_MODE, PickerConfig.PICKER_IMAGE);//default image and video (Optional)
        long maxSize = 188743680L;//long long long
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE, maxSize); //default 180MB (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 5 - piclist.size() + 1);  //default 40 (Optional)
//        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,select); // (Optional)
        startActivityForResult(intent, 200);
    }

    void goPreviewActivity() {
        Intent intent = new Intent(mActivity, PreviewActivity.class);
        intent.putExtra(PickerConfig.PRE_RAW_LIST, select);//default image and video (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT, select.size());//default image and video (Optional)
        startActivityForResult(intent, 300);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mEtMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.et_message:
                        // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                        if (canVerticalScroll(mEtMessage))
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }
                }
                return false;
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    public static boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @SingleClick
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                uploadImg(selectpiclist);
                break;

        }
    }

    @Override
    public void AddLeaveMessageForOrder(BaseResult<Data<String>> baseResult) {
        try {
            switch (baseResult.getStatusCode()) {
                case 200:
                    hideProgress();
                    ToastUtils.showShort(baseResult.getData().getItem2());
                    mEtMessage.setText("");
                    select.clear();
                    piclist.clear();
                    selectpiclist.clear();
                    loadAdpater(piclist);
                    mPresenter.GetLeaveMsgList(orderId);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                mPresenter.LeaveMessageWhetherLook(orderId);
                data = baseResult.getData();
//                if (data.getLeavemessageList().size() == 0) {
//                } else {
//                    list = data.getLeavemessageList();
//                    Collections.reverse(list);
//                    leaveMessageAdapter.setNewData(list);
//                }
                hideProgress();
                break;
        }
    }

    @Override
    public void GetLeaveMsgList(GetLeaveMsgListResult baseResult) {
        if (baseResult.getStatusCode()==200){
            if (baseResult.getData().getCode()==0){
                mPresenter.LeaveMessageWhetherLook(orderId);
                list=baseResult.getData().getData();
                leaveMessageAdapter.setNewData(list);
            }
        }
        hideProgress();
    }

    @Override
    public void LeaveMessageImg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    String message = mEtMessage.getText().toString();
                    if (message == null || "".equals(message)) {
                        ToastUtils.showShort("请输入留言内容");
                    } else {
                        mPresenter.AddLeaveMessageForOrder(userID, orderId, message, baseResult.getData().getItem2());
                    }

                }
                break;
            default:
                ToastUtils.showShort("图片上传失败");
                break;
        }
    }

    @Override
    public void DeleteLeaveMessageImg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                break;
        }
    }

    @Override
    public void LeaveMessageWhetherLook(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                EventBus.getDefault().post("read");
                break;
        }
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size == grantResults.length) {//允许
                    goImage();
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                    MyUtils.toSelfSetting(mActivity);
                }
                break;
            default:
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        if (requestCode == 200 && resultCode == PickerConfig.RESULT_CODE) {
            select.addAll((ArrayList) data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT));
            ArrayList<String> list = new ArrayList<>();
            Log.i("select", "select.size" + select.size());
            for (Media media : select) {
                list.add(media.path);
                Log.i("media", media.path);
                Log.e("media", "s:" + media.size);
            }
            loadAdpater(list);
        }
        if (requestCode == 300) {
            select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
            ArrayList<String> list = new ArrayList<>();
            Log.i("select", "select.size" + select.size());
            for (Media media : select) {
                list.add(media.path);
                Log.i("media", media.path);
                Log.e("media", "s:" + media.size);
            }
            loadAdpater(list);
        }
    }

    public void uploadImg(List<String> selectimg) {
        message = mEtMessage.getText().toString();
        if (message == null || "".equals(message)) {
            ToastUtils.showShort("请输入留言内容");
            return;
        }
        showProgress();
        if (selectimg.size() == 0) {
            mPresenter.AddLeaveMessageForOrder(userID, orderId, message, "");
        } else {
            successpiclist.clear();
            for (int i = 0; i < selectimg.size(); i++) {
                File file = new File(selectimg.get(i));
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("img/png"), file));
                MultipartBody requestBody = builder.build();
                //接口
                String path = Config.BASE_URL + "Upload/LeaveMessageImg";
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .build();
                final Request request = new Request.Builder()
                        .url(path)
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        hideProgress();
                        ToastUtils.showShort("留言失败，请稍后重试");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        System.out.println(str);
                        Gson gson = new Gson();
                        PicResult result = gson.fromJson(str.replaceAll(" ", ""), PicResult.class);
                        if (result.getData().isItem1()) {
                            successpiclist.add(result.getData().getItem2());
                            if (successpiclist.size() == selectpiclist.size()) {
                                String photo = "";
                                for (int i = 0; i < successpiclist.size(); i++) {
                                    photo += successpiclist.get(i) + ",";
                                }
                                photo = photo.substring(0, photo.lastIndexOf(","));
                                mPresenter.AddLeaveMessageForOrder(userID, orderId, message, photo);
                            }
                        } else {
                            hideProgress();
                            ToastUtils.showShort("留言失败，请稍后重试");
                        }
                    }
                });
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Bitmap name) {
        bitmap = name;
        ToastUtils.showShort(bitmap + "");
    }
}
