package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.PreviewActivity;
import com.dmcbig.mediapicker.entity.Media;
import com.google.zxing.integration.android.IntentIntegrator;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ScanActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.PicAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.FileSizeUtil;
import com.ying.administrator.masterappdemo.util.imageutil.ImageCompress;
import com.ying.administrator.masterappdemo.v3.adapter.AccessoriesPictureAdapter;
import com.ying.administrator.masterappdemo.v3.adapter.V4_AccessoriesAdapter;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReceiptResult;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReturnResult;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.bean.UpdateAccessoryResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.AccessoriesDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.AccessoriesDetailsModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.ying.administrator.masterappdemo.util.MyUtils.showToast;

public class AccessoriesDetailsActivity extends BaseActivity<AccessoriesDetailsPresenter, AccessoriesDetailsModel> implements View.OnClickListener, AccessoriesDetailsContract.View {
    private static final String TAG = "AccessoriesDetailsActiv";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_outbound_logistics)
    TextView mTvOutboundLogistics;
    @BindView(R.id.ll_outbound_logistics)
    LinearLayout mLlOutboundLogistics;
    @BindView(R.id.tv_return_logistics)
    TextView mTvReturnLogistics;
    @BindView(R.id.ll_return_logistics)
    LinearLayout mLlReturnLogistics;
    @BindView(R.id.rv_accessories)
    RecyclerView mRvAccessories;
    @BindView(R.id.rv_accessories_picture)
    RecyclerView mRvAccessoriesPicture;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.ll_accessories_picture)
    LinearLayout mLlAccessoriesPicture;
    @BindView(R.id.tv_acc_state)
    TextView mTvAccState;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.tv_dsh)
    TextView mTvDsh;
    @BindView(R.id.tv_dfj)
    TextView mTvDfj;
    @BindView(R.id.tv_dqh)
    TextView mTvDqh;
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.tv_dwc)
    TextView mTvDwc;
    @BindView(R.id.bar_one)
    SeekBar mBarOne;
    @BindView(R.id.tv_dsh2)
    TextView mTvDsh2;
    @BindView(R.id.tv_dfj2)
    TextView mTvDfj2;
    @BindView(R.id.tv_dqh2)
    TextView mTvDqh2;
    @BindView(R.id.tv_dwc2)
    TextView mTvDwc2;
    @BindView(R.id.bar_two)
    SeekBar mBarTwo;
    @BindView(R.id.ll_one)
    LinearLayout mLlOne;
    @BindView(R.id.ll_two)
    LinearLayout mLlTwo;
    @BindView(R.id.ll_logistics)
    LinearLayout mLlLogistics;
    @BindView(R.id.btn_ConfirmReceipt)
    Button mBtnConfirmReceipt;
    @BindView(R.id.btn_ConfirmReturn)
    Button mBtnConfirmReturn;
    @BindView(R.id.btn_cancel)
    Button mBtnCancel;
    private List<String> list = new ArrayList<>();
    private AccessoriesPictureAdapter accessoriesPictureAdapter;
    private Intent intent;
    private SimpleTarget<Bitmap> simpleTarget;
    private V4_AccessoriesAdapter accessoriesAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private accessoryDataBean data;
    private View puchsh_view;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private EditText et_expressno;
    private EditText et_post_money;
    private LinearLayout ll_post_money;
    private LinearLayout ll_scan;
    private TextView tv_remind;
    private AlertDialog push_dialog;
    private String expressno;
    private String post_money;
    private ArrayList<String> permissions;
    private int size;
    private RecyclerView rv_icons;
    private ArrayList<String> piclist = new ArrayList<>();
    private ArrayList<String> selectpiclist = new ArrayList<>();
    private ArrayList<Media> select = new ArrayList<>();
    private PicAdapter picAdapter;
    private String targetPath;
    private String compressImage;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_accessories_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        data = (accessoryDataBean) getIntent().getSerializableExtra("acc_data");
        mTvTitle.setText("配件单详情（" + data.getAccessoryNo() + "）");
        accessoriesAdapter = new V4_AccessoriesAdapter(R.layout.v3_item_accessories, data.getAccessoryDetailModels(), data.getAccessoryState());
        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAccessories.setAdapter(accessoriesAdapter);
        accessoriesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.ll_delete:
                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                        dialog.setMessage("是否确认删除" + data.getAccessoryDetailModels().get(position).getFAccessoryName())
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                mPresenter.DeleteAccessory(data.getAccessoryDetailModels().get(position).getAccessoryID() + "");
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                            }
                        }).show();
                        break;
                    case R.id.btn_edit:

                        break;
                }
            }
        });
        list.clear();
        for (int i = 0; i < data.getOrderAttachments().size(); i++) {
            list.add(data.getOrderAttachments().get(i).getUrl());
        }
//        for (int i = 0; i < 12; i++) {
//            list.add("https://img.xigyu.com/Pics/Accessory/d208e5b9f7684d2990f69a9f2a42076f.jpeg");
//            list.add("https://img.xigyu.com/Pics/Accessory/b38a4b514162436da5323698fd4be9b2.jpeg");
//        }
        accessoriesPictureAdapter = new AccessoriesPictureAdapter(R.layout.v3_item_accessories_picture, list);
        mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvAccessoriesPicture.setAdapter(accessoriesPictureAdapter);
        accessoriesPictureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PhotoPreview.builder()
                        .setPhotos((ArrayList<String>) list)
                        .setShowDeleteButton(false)
                        .setCurrentItem(position)
                        .start(AccessoriesDetailsActivity.this);
            }
        });

        mBarOne.setClickable(false);
        mBarOne.setEnabled(false);
        mBarTwo.setClickable(false);
        mBarTwo.setEnabled(false);

        refreshData();
    }

    private void refreshData() {
        if ("-2".equals(data.getState()) || "-1".equals(data.getState())) {//配件单拒绝或者被终止
            mLlOne.setVisibility(View.GONE);
            mLlTwo.setVisibility(View.GONE);
            mLlLogistics.setVisibility(View.GONE);
        } else {
            if ("0".equals(data.getState())) {
                mLlLogistics.setVisibility(View.GONE);
                mTvDsh.setText("待审核");
                mTvDfj.setText("待发件");
                mTvDqh.setText("待收件");
                mTvReturn.setText("待返件");
                mTvDwc.setText("待完成");
                mBarOne.setProgress(0);
                mBarTwo.setProgress(0);
                mTvDsh2.setText("待审核");
                mTvDfj2.setText("待发件");
                mTvDqh2.setText("待收件");
                mTvDwc2.setText("待完成");
            } else if ("1".equals(data.getState()) || "2".equals(data.getState())) {
                mLlLogistics.setVisibility(View.VISIBLE);
                mLlOutboundLogistics.setVisibility(View.VISIBLE);
                mLlReturnLogistics.setVisibility(View.GONE);
                mTvDsh.setText("已审核");
                mTvDfj.setText("已发件");
                mTvDqh.setText("待收件");
                mTvReturn.setText("待返件");
                mTvDwc.setText("待完成");
                mBarOne.setProgress(25);
                mBarTwo.setProgress(33);
                mTvDsh2.setText("已审核");
                mTvDfj2.setText("已发件");
                mTvDqh2.setText("待收件");
                mTvDwc2.setText("待完成");
            } else if ("3".equals(data.getState()) || "4".equals(data.getState()) || "5".equals(data.getState())) {
                mLlLogistics.setVisibility(View.VISIBLE);
                mLlOutboundLogistics.setVisibility(View.VISIBLE);
                mLlReturnLogistics.setVisibility(View.GONE);
                mTvDsh.setText("已审核");
                mTvDfj.setText("已发件");
                mTvDqh.setText("已收件");
                mTvReturn.setText("待返件");
                mTvDwc.setText("待完成");
                mBarOne.setProgress(50);
            } else if ("6".equals(data.getState()) || "7".equals(data.getState())) {
                mLlLogistics.setVisibility(View.VISIBLE);
                mLlOutboundLogistics.setVisibility(View.VISIBLE);
                mLlReturnLogistics.setVisibility(View.VISIBLE);
                mTvDsh.setText("已审核");
                mTvDfj.setText("已发件");
                mTvDqh.setText("已收件");
                mTvReturn.setText("已返件");
                mTvDwc.setText("待完成");
                mBarOne.setProgress(75);
                mBarTwo.setProgress(66);
                mTvDsh2.setText("已审核");
                mTvDfj2.setText("已发件");
                mTvDqh2.setText("已收件");
                mTvDwc2.setText("待完成");
            } else if ("8".equals(data.getState())) {
                mLlLogistics.setVisibility(View.VISIBLE);
                mLlOutboundLogistics.setVisibility(View.VISIBLE);
                mLlReturnLogistics.setVisibility(View.VISIBLE);
                mTvDsh.setText("已审核");
                mTvDfj.setText("已发件");
                mTvDqh.setText("已收件");
                mTvReturn.setText("已返件");
                mTvDwc.setText("已完成");
                mBarOne.setProgress(100);
                mBarTwo.setProgress(100);
                mTvDsh2.setText("已审核");
                mTvDfj2.setText("已发件");
                mTvDqh2.setText("已收件");
                mTvDwc2.setText("已完成");
            }
            if ("2".equals(data.getIsReturn())) {//0不需要返件四个进度，1需要返件五个进度
                mLlOne.setVisibility(View.VISIBLE);
                mLlTwo.setVisibility(View.GONE);
                mLlReturnLogistics.setVisibility(View.GONE);
            } else {
                mLlOne.setVisibility(View.GONE);
                mLlTwo.setVisibility(View.VISIBLE);
                mLlReturnLogistics.setVisibility(View.VISIBLE);
            }
        }
        if ("1".equals(data.getState()) || "2".equals(data.getState())) {//已发件配件待签收
            mBtnConfirmReceipt.setVisibility(View.VISIBLE);
        } else {
            mBtnConfirmReceipt.setVisibility(View.GONE);
        }
        if ("3".equals(data.getState()) || "4".equals(data.getState())) {//待返旧件
            mBtnConfirmReturn.setVisibility(View.VISIBLE);
        } else {
            mBtnConfirmReturn.setVisibility(View.GONE);
        }
        if ("0".equals(data.getState())){
            mBtnCancel.setVisibility(View.VISIBLE);
        }else{
            mBtnCancel.setVisibility(View.GONE);
        }
        mTvOutboundLogistics.setText("快递单号（" + data.getExpressNo() + "）");
        mTvReturnLogistics.setText("快递单号（" + data.getReturnExpressNo() + "）");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlOutboundLogistics.setOnClickListener(this);
        mLlReturnLogistics.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnConfirmReceipt.setOnClickListener(this);
        mBtnConfirmReturn.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
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
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add:
                showPopupWindow();
                break;
            case R.id.ll_outbound_logistics:
                intent = new Intent(mActivity, LogisticsActivity.class);
                intent.putExtra("number", data.getExpressNo() + "");
                startActivity(intent);
                break;
            case R.id.ll_return_logistics:
                intent = new Intent(mActivity, LogisticsActivity.class);
                intent.putExtra("number", data.getReturnExpressNo() + "");
                startActivity(intent);
                break;
            case R.id.btn_ConfirmReceipt://确认签收
                mPresenter.ConfirmReceipt(data.getAccessoryID() + "");
                break;
            case R.id.btn_ConfirmReturn://确认返件
                confirmReturn();
                break;
            case R.id.btn_cancel://取消
                final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                dialog.setMessage("是否确认取消该配件单申请？")
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        mPresenter.DeleteAccessory(data.getAccessoryID() + "");
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }

    private void confirmReturn() {
        puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
        btn_negtive = puchsh_view.findViewById(R.id.negtive);
        btn_positive = puchsh_view.findViewById(R.id.positive);
        tv_title = puchsh_view.findViewById(R.id.title);
        et_expressno = puchsh_view.findViewById(R.id.et_expressno);
        et_post_money = puchsh_view.findViewById(R.id.et_post_money);
        ll_post_money = puchsh_view.findViewById(R.id.ll_post_money);
        rv_icons = puchsh_view.findViewById(R.id.rv_icons);
        piclist.clear();
        selectpiclist.clear();
        select.clear();
        piclist.add("add");
        picAdapter = new PicAdapter(R.layout.item_picture, piclist);
        rv_icons.setLayoutManager(new GridLayoutManager(mActivity, 5));
        rv_icons.setAdapter(picAdapter);
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
        ll_scan = puchsh_view.findViewById(R.id.ll_scan);
        tv_remind = puchsh_view.findViewById(R.id.tv_remind);
        tv_remind.setText(Html.fromHtml(mActivity.getResources().getString(R.string.gray_white, "为避免产生不必要的纠纷，请在返件的快递单中填写所完成的", "工单号、用户姓名及电话号码", "，并在下面的输入框中填写正确的快递单号")));
        push_dialog = new AlertDialog.Builder(mActivity)
                .setView(puchsh_view)
                .create();
        push_dialog.show();
        tv_title.setText("填写快递单号");
        if ("2".equals(data.getPostPayType())) {
            ll_post_money.setVisibility(View.VISIBLE);
        } else {
            ll_post_money.setVisibility(View.GONE);
        }
        btn_negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push_dialog.dismiss();
            }
        });
        ll_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AccessoriesDetailsActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                expressno = et_expressno.getText().toString().trim();
                post_money = et_post_money.getText().toString();
                if ("".equals(expressno)) {
                    showToast(mActivity, "请填写快递单号");
                    hideProgress();
                    return;
                }
                if ("2".equals(data.getPostPayType())) {
                    if ("".equals(post_money)) {
                        showToast(mActivity, "请填写邮费");
                        hideProgress();
                        return;
                    } else {
                        reqReturn();
                    }
                } else {
                    post_money = "0";
                    reqReturn();
                }
            }
        });
    }
    //开始返件
    public void reqReturn() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < selectpiclist.size(); i++) {
            File img=new File(selectpiclist.get(i));
            builder.addFormDataPart("img"+i, img.getName(), RequestBody.create(MediaType.parse("img/png"), img));
        }
        builder.addFormDataPart("AccessoryID", Integer.toString(data.getAccessoryID()));
        builder.addFormDataPart("ReturnExpressNo", expressno);
        builder.addFormDataPart("PostMoney", post_money);
        MultipartBody requestBody = builder.build();
        mPresenter.ConfirmReturn(requestBody);

    }

    private void loadAdpater(ArrayList<String> paths) {
        piclist.clear();
        piclist.addAll(paths);
        selectpiclist.clear();
        for (int i = 0; i < paths.size(); i++) {
            targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg";
            compressImage = ImageCompress.compressImage(paths.get(i), targetPath, 80);
            Log.i(TAG, "原大小"+FileSizeUtil.getFileOrFilesSize(paths.get(i),FileSizeUtil.SIZETYPE_MB)+"Mb");
            Log.i(TAG, "压缩后大小"+FileSizeUtil.getFileOrFilesSize(compressImage,FileSizeUtil.SIZETYPE_MB)+"Mb");
            selectpiclist.add(compressImage);
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
    /**
     * 弹出Popupwindow,选择厂家寄件还是自购件
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.cj_or_zg_layout, null);
        Button btn_cj = popupWindow_view.findViewById(R.id.btn_cj);
        Button btn_zg = popupWindow_view.findViewById(R.id.btn_zg);
        Button btn_complete = popupWindow_view.findViewById(R.id.btn_complete);
        Button btn_cancel = popupWindow_view.findViewById(R.id.btn_cancel);

        btn_cj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                intent = new Intent(mActivity, ApplyAccActivity.class);
                intent.putExtra("OrderID", data.getOrderID() + "");
//                intent.putExtra("prodID", data.getOrderProdcutID()+"");
                intent.putExtra("list_prod", data);
//                intent.putExtra("prodName", data.getSubCategoryName()+"(编号："+ data.getOrderProdcutID()+")");
//                intent.putExtra("SubCategoryID", data.getProductTypeID()+"");
                intent.putExtra("cj_or_zg", "厂寄");
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        btn_zg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                intent = new Intent(mActivity, ApplyAccActivity.class);
                intent.putExtra("OrderID", data.getOrderID() + "");
//                intent.putExtra("prodID", data.getOrderProdcutID()+"");
                intent.putExtra("list_prod", data);
//                intent.putExtra("prodName", data.getSubCategoryName()+"(编号："+ data.getOrderProdcutID()+")");
//                intent.putExtra("SubCategoryID", data.getProductTypeID()+"");
                intent.putExtra("cj_or_zg", "自购");
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        //直接完结工单
        btn_complete.setVisibility(View.GONE);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                for (int i = 0; i < baseResult.getData().getOrderProductModels().size(); i++) {
                    for (int j = 0; j < baseResult.getData().getOrderProductModels().get(i).getAccessoryData().size(); j++) {
                        if (baseResult.getData().getOrderProductModels().get(i).getAccessoryData().get(j).getAccessoryID() == data.getAccessoryID()) {
                            data = baseResult.getData().getOrderProductModels().get(i).getAccessoryData().get(j);
                            accessoriesAdapter.setNewData(data.getAccessoryDetailModels());
                            refreshData();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void UpdateAccessory(UpdateAccessoryResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isStatus()) {
                    showToast(mActivity, "更换成功");
                    EventBus.getDefault().post(21);
                } else {
                    showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
        }
    }

    @Override
    public void DeleteAccessory(DeleteAccessoryResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isStatus()) {
                    showToast(mActivity, "取消成功");
                    EventBus.getDefault().post(21);
                    finish();
                } else {
                    showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
        }
    }

    @Override
    public void ConfirmReceipt(ConfirmReceiptResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode() == 0) {
                    showToast(mActivity, "操作成功");
                    EventBus.getDefault().post(21);
                } else {
                    showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
        }
    }

    @Override
    public void ConfirmReturn(ConfirmReturnResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode() == 0) {
                    showToast(mActivity, "操作成功");
                    EventBus.getDefault().post(21);
                    push_dialog.dismiss();
                } else {
                    showToast(mActivity, baseResult.getData().getMsg());
                }
                hideProgress();
                break;
        }
    }

    public void scaleview(String url) {
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                    super Bitmap> transition) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                rxDialogScaleView.setImage(resource);
                rxDialogScaleView.show();
            }
        };

        Glide.with(mActivity)
                .asBitmap()
                .load(url)
                .into(simpleTarget);
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
                    toSetting();
                }
                break;
            default:
                break;

        }
    }
    //设置权限
    private void toSetting() {

        new AlertDialog.Builder(this).setTitle("相机和存储权限未授权")
                .setMessage("是否前往设置权限？")
                //  取消选项
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                //  确认选项
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到手机原生设置页面
                        MyUtils.toSelfSetting(mActivity);
                    }
                })
                .setCancelable(false)
                .show();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer name) {
        switch (name) {
            case 21:
                mPresenter.GetOrderInfo(data.getOrderID() + "");
                break;
        }
    }
}
