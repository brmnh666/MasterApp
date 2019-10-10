package com.ying.administrator.masterappdemo.mvp.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.SAccessory;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;
import com.ying.administrator.masterappdemo.mvp.model.NewAddAccessoriesModel;
import com.ying.administrator.masterappdemo.mvp.presenter.NewAddAccessoriesPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.NewAddAccessoriesAdapter;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.widget.MyPackagePopup;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　┣┓
//    ┃　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

/**
 * Data:2019/7/30
 * Time:10:55
 * author:ying
 **/
/*添加配件新页面*/
public class NewAddAccessoriesActivity extends BaseActivity<NewAddAccessoriesPresenter, NewAddAccessoriesModel> implements NewAddAccessoriesContract.View, View.OnClickListener {

    @BindView(R.id.tv_choose)
    TextView mTvChoose;
    @BindView(R.id.tv_address_return)
    TextView mTvAddressReturn;
    @BindView(R.id.tv_modify)
    TextView mTvModify;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    setAnim(ball, startLocation);// 开始执行动画
                    break;
            }
        }
    };


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_search)
    EditText mEtsearch;
    @BindView(R.id.ll_my_package)
    RelativeLayout mLlmy_package;
    @BindView(R.id.img_package)
    ImageView mImgpackage;
    @BindView(R.id.tv_num_bg)
    TextView mTvnum_bg;
    @BindView(R.id.tv_next)
    TextView mTvnext;
    @BindView(R.id.img_return)
    ImageView mImgreturn;

    //动画
    private int[] startLocation;
    private ImageView ball;// 小圆点
    private ViewGroup viewGroup;//动画层
    //数字圆点

    private int num;


    private NewAddAccessoriesAdapter newAddAccessoriesAdapter;
    private List<Accessory> list_accessory = new ArrayList<>();// 获取第一次全部所有的配件
    private List<Accessory> list_search = new ArrayList<>();//搜索到的配件

    private List<Accessory> list_collect = new ArrayList<>(); //收藏的配件
    private List<Accessory> list_add = new ArrayList<>(); //收藏的配件
    private Map<Integer, Accessory> map_collect = new HashMap<>();//收藏的配件map集合
    FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 用于存放预接单页面显示的数据


    private ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private AlertDialog underReviewDialog;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<Object> permissions;
    private ImageView iv_host;
    private ImageView iv_accessories;
    private List<Uri> mSelected;
    private Uri uri;
    private View addpic_view;
    private AlertDialog addpic_dialog;
    private HashMap<Integer, File> accessories_picture = new HashMap<>();
    private String count;
    private int position;
    private View addview;
    private String AddressBack;
    private List<AddressList> addressList;
    private String userId;
    private String select_state;
    private String OrderID;
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private String money;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_newaddaccessories;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userId);

        String subCategoryID = getIntent().getStringExtra("SubCategoryID");
        select_state = getIntent().getStringExtra("select_state");
        OrderID = getIntent().getStringExtra("orderId");
        if ("0".equals(select_state)) {
            mTvTitle.setText("厂家寄件申请");
        } else {
            mTvTitle.setText("师傅自购件申请");
            mTvModify.setVisibility(View.GONE);
            mLlAddress.setVisibility(View.GONE);
        }
        showLoading();
        mPresenter.GetFactoryAccessory(subCategoryID);

        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        newAddAccessoriesAdapter = new NewAddAccessoriesAdapter(R.layout.item_newaddaccessories, list_search);
        rv.setAdapter(newAddAccessoriesAdapter);

        newAddAccessoriesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_add:
                        addPic(view, position,select_state);
                        break;

                }
            }
        });

    }

    @Override
    protected void setListener() {
        mLlmy_package.setOnClickListener(this);
        mTvnext.setOnClickListener(this);
        mImgreturn.setOnClickListener(this);
        mTvChoose.setOnClickListener(this);
        mTvModify.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);

        mEtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if ("".equals(s.toString())) {
                    //没内容
                    list_search.clear();
                    list_search.addAll(list_accessory);
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                } else {
                    list_search.clear();
                    list_search.addAll(searchAccessory(s.toString(), list_accessory));
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    /**
     * 添加配件图片
     */
    public void addPic(final View view, final int position, final String selectState) {
        addview = view;
        this.position = position;
        addpic_view = LayoutInflater.from(mActivity).inflate(R.layout.addpic, null);
        Button btn_negtive = addpic_view.findViewById(R.id.negtive);
        Button btn_positive = addpic_view.findViewById(R.id.positive);
        LinearLayout ll_host = addpic_view.findViewById(R.id.ll_host);
        LinearLayout ll_accessories = addpic_view.findViewById(R.id.ll_accessories);
        LinearLayout ll_money=addpic_view.findViewById(R.id.ll_money);
        final EditText et_money=addpic_view.findViewById(R.id.et_money);
        final EditText et_count = addpic_view.findViewById(R.id.et_count);
        iv_host = addpic_view.findViewById(R.id.iv_host);
        iv_accessories = addpic_view.findViewById(R.id.iv_accessories);
        ll_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(801, 809);
            }
        });
        ll_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(901, 909);
            }
        });

        if ("0".equals(select_state)){
            ll_money.setVisibility(View.GONE);
        }else {
            ll_money.setVisibility(View.VISIBLE);
        }

        addpic_dialog = new AlertDialog.Builder(mActivity)
                .setView(addpic_view)
                .create();
        addpic_dialog.show();
        btn_negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addpic_dialog.dismiss();
            }
        });
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (accessories_picture.size() != 2) {
                    ToastUtils.showShort("请添加图片");
                    return;
                }

                money = et_money.getText().toString();
                if ("1".equals(selectState)){
                    if (money.isEmpty()){
                        ToastUtils.showShort("请输入配件价格");
                        return;
                    }
                }
                count = et_count.getText().toString();
                if (count.isEmpty()) {
                    ToastUtils.showShort("请输入配件数量");
                    return;
                } else {
                    ApplyAccessoryphotoUpload(accessories_picture);
                }

            }
        });
    }

    /*获取配件*/
    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                cancelLoading();
                if (baseResult.getData().getData() != null) {
                    list_accessory.addAll(baseResult.getData().getData());
                    list_search.addAll(list_accessory);
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                }
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void ApplyAccessoryphotoUpload(BaseResult<Data2> baseResult) {
        if (baseResult.getStatusCode() == 200) {
            Accessory acc = newAddAccessoriesAdapter.getData().get(position);
            acc.setCount(Integer.parseInt(count));
            acc.setImg1(baseResult.getData().getItem1());
            acc.setImg2(baseResult.getData().getItem2());
            if (map_collect.get(position) == null) {
                map_collect.put(position, acc);
                num = num + Integer.parseInt(count);
            } else {
                map_collect.replace(position, acc);
            }
            list_collect.clear();
            //将map对象转为list
            Collection<Accessory> collection = map_collect.values();
            Iterator<Accessory> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Accessory value = iterator.next();
                list_collect.add(value);
            }

            startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
            addview.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
            ball = new ImageView(NewAddAccessoriesActivity.this);// buyImg是动画的图片，我的是一个小球（R.drawable.sign）
            getBallImageResource(ball);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }).start();
            addpic_dialog.dismiss();
        }
    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    for (int i = 0; i < addressList.size(); i++) {
                        if ("1".equals(addressList.get(i).getIsDefault())) {
                            AddressBack = addressList.get(i).getProvince() + addressList.get(i).getCity() + addressList.get(i).getArea() + addressList.get(i).getDistrict() + addressList.get(i).getAddress() + "(" + addressList.get(i).getUserName() + "收)" + addressList.get(i).getPhone();
                            mTvAddressReturn.setText(AddressBack);
//                            mTvModify.setText("修改地址");
                        } else {
                            AddressBack = addressList.get(0).getProvince() + addressList.get(0).getCity() + addressList.get(0).getArea() + addressList.get(0).getDistrict() + addressList.get(0).getAddress() + "(" + addressList.get(0).getUserName() + "收)" + addressList.get(0).getPhone();
                            mTvAddressReturn.setText(AddressBack);
//                            mTvModify.setText("修改地址");
                        }
//                        else {
//                            AddressBack = "";
//                            mTvAddressReturn.setText(AddressBack);
////                            mTvModify.setText("添加地址");
//                        }
                    }
                } else {
                    AddressBack = "";
                    mTvAddressReturn.setText(AddressBack);
//                    mTvModify.setText("添加地址");
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
                    EventBus.getDefault().post(5);
                    finish();
//                    ApplyAccessoryphotoUpload(accessories_picture);
                } else {
                    if ("您账户余额不足，请尽快充值以免影响配件审核,充值最低金额为：200".equals(baseResult.getData().getItem2())) {
                        customdialog_home_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_home, null);
                        customdialog_home_dialog = new AlertDialog.Builder(mActivity)
                                .setView(customdialog_home_view)
                                .create();
                        customdialog_home_dialog.show();
                        TextView title = customdialog_home_view.findViewById(R.id.title);
                        TextView message = customdialog_home_view.findViewById(R.id.message);
                        TextView negtive = customdialog_home_view.findViewById(R.id.negtive);
                        TextView positive = customdialog_home_view.findViewById(R.id.positive);
                        title.setText("温馨提示");
                        message.setText(baseResult.getData().getItem2() + "。当工单完结后返还，是否充值？");
                        negtive.setText("否");
                        positive.setText("是");
                        negtive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customdialog_home_dialog.dismiss();
                            }
                        });
                        positive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mActivity, RechargeActivity.class));
                                customdialog_home_dialog.dismiss();
                            }
                        });
                    } else {
                        ToastUtils.showShort((String) baseResult.getData().getItem2());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult) {

    }

    public List searchAccessory(String name, List list) {
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < list.size(); i++) {
            Matcher matcher = pattern.matcher(((Accessory) list.get(i)).getAccessoryName());
            if (matcher.find()) {
                results.add(list.get(i));
            }
        }
        return results;
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("获取配件中请稍等...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancelLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }


    private void setAnim(final View v, int[] startLocation) {
        viewGroup = null;
        viewGroup = createAnimLayout();
        viewGroup.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(viewGroup, v,
                startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        mLlmy_package.getLocationInWindow(endLocation);// mLlmy_package是那个抛物线最后掉落的控件

        // 计算位移
        int endX = 0 - startLocation[0] + 80;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.2f);
        alphaAnimation.setDuration(800);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setFillAfter(true);


        final AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.addAnimation(alphaAnimation);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {

                if (num != 0) {
                    mTvnum_bg.setVisibility(View.VISIBLE);
                    mTvnum_bg.setText(num + "");
                }

                v.setVisibility(View.GONE);
                set.cancel();
                animation.cancel();


            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_my_package:
                new XPopup.Builder(mActivity)
                        .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                        .asCustom(new MyPackagePopup(mActivity, list_collect, mActivity))/*.enableDrag(false)*/
                        .show();

                break;
            case R.id.tv_next://下一步
                String returnAddress = mTvAddressReturn.getText().toString().trim();

                if (list_collect.isEmpty()) {
                    Toast.makeText(mActivity, "请先选择配件", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent = new Intent();
//                    intent.putExtra("list_collect", (Serializable) list_collect);
//                    setResult(Config.APPLY_RESULT, intent);
//                    NewAddAccessoriesActivity.this.finish();

                    Gson gson = new Gson();
                    if ("0".equals(select_state)) {
                        if ("".equals(returnAddress)) {
                            ToastUtils.showShort("请选择收货地址");
                        }
                    }
                    for (int i = 0; i < list_collect.size(); i++) {
                        mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                        mfAccessory.setPhoto1(list_collect.get(i).getImg1());//配件照片
                        mfAccessory.setPhoto2(list_collect.get(i).getImg2());//整机照片
                        mfAccessory.setFAccessoryID(list_collect.get(i).getFAccessoryID());//获取id
                        mfAccessory.setFAccessoryName(list_collect.get(i).getAccessoryName()); //获取名字
                        mfAccessory.setFCategoryID(list_collect.get(i).getFCategoryID() + ""); //分类id
                        mfAccessory.setQuantity(list_collect.get(i).getCount() + ""); //数量 默认数字为1
                        mfAccessory.setPrice(Double.valueOf("0"));//原价
                        mfAccessory.setDiscountPrice(Double.valueOf("0"));//折扣价
//                        mfAccessory.setSizeID("1");//小修中修大修
                        mfAccessory.setSendState("N");
                        mfAccessory.setRelation("");
                        mfAccessory.setState("0");
                        mfAccessory.setIsPay("N");
                        mfAccessory.setExpressNo("");
                        mfAccessory.setNeedPlatformAuth("N");
                        if ("0".equals(select_state)) {//厂家自购
                            mfAccessory.setPrice(list_collect.get(i).getAccessoryPrice());//原价
                            mfAccessory.setDiscountPrice(list_collect.get(i).getAccessoryPrice());//原价
                        } else if ("1".equals(select_state) ) {//师傅自购 还要判断保内保外
                            if ("".equals(money)) {
                                ToastUtils.showShort("请输入配件价格");
                                return;
                            }
                            mfAccessory.setPrice(Double.parseDouble(money));
                            mfAccessory.setDiscountPrice(Double.parseDouble(money));
                        }
                        fAcList.add(mfAccessory);
                    }


//                        if (accessories_picture.size() > 0) {
                    FAccessory.OrderAccessoryStrBean orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                    orderAccessoryStrBean.setOrderAccessory(fAcList);
                    orderAccessoryStrBean.setAccessoryMemo("");
                    String s1 = gson.toJson(orderAccessoryStrBean);
                    SAccessory sAccessory = new SAccessory();
                    sAccessory.setOrderID(OrderID);
                    sAccessory.setAccessorySequency(select_state);
                    sAccessory.setOrderAccessoryStr(s1);
                    String s = gson.toJson(sAccessory);
                    Log.d("添加的配件有", s);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                    mPresenter.AddOrderAccessory(body);
                    if ("0".equals(select_state)){
                        mPresenter.UpdateOrderAddressByOrderID(OrderID, returnAddress);
                    }

//                        } else {
//                            ToastUtils.showShort("请添加配件图片");
//                        }

                }

                break;
            case R.id.img_return:
                NewAddAccessoriesActivity.this.finish();
                break;
            case R.id.tv_choose:
                View under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_add_accessories, null);
                final EditText et_accessories_name = under_review.findViewById(R.id.et_accessories_name);
                Button btn_add1 = under_review.findViewById(R.id.btn_add);
                btn_add1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        tv_accessory_name.setText(et_accessories_name.getText());
                        num++;//背包内数量+1
                        Accessory accessory = new Accessory();
                        accessory.setFAccessoryID("0");
                        accessory.setAccessoryName(et_accessories_name.getText().toString());
                        accessory.setFCategoryID(list_accessory.get(0).getFCategoryID());
                        accessory.setCount(1);
                        list_add.add(accessory);
                        Intent intent = new Intent();
                        intent.putExtra("list_collect", (Serializable) list_add);
                        setResult(Config.APPLY_RESULT, intent);
                        NewAddAccessoriesActivity.this.finish();
                        underReviewDialog.dismiss();
                    }
                });
                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                        .create();
                underReviewDialog.show();
                Window window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
                // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.tv_modify:
            case R.id.tv_edit:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 100);
                break;

        }


    }

    public void getBallImageResource(ImageView ball) {
        int Num = new Random().nextInt(7);
        switch (Num) {
            case 0:
                ball.setImageResource(R.mipmap.ic_add_1);// 设置buyImg的图片
                break;
            case 1:
                ball.setImageResource(R.mipmap.ic_add_2);
                break;
            case 2:
                ball.setImageResource(R.mipmap.ic_add_3);
                break;
            case 3:
                ball.setImageResource(R.mipmap.ic_add_4);
                break;
            case 4:
                ball.setImageResource(R.mipmap.ic_add_5);
                break;
            case 5:
                ball.setImageResource(R.mipmap.ic_add_6);
                break;
            case 6:
                ball.setImageResource(R.mipmap.ic_add_7);
                break;

        }


    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis() + ".jpg";
                    String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
                    FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
                    File dirfile = new File(fileDir);
                    if (!dirfile.exists()) {
                        dirfile.mkdirs();
                    }
                    File file = new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity, "com.ying.administrator.masterappdemo.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
//                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                    i.setType("image/*");
//                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    Matisse.from(NewAddAccessoriesActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(1)
//                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new Glide4Engine())
                            .forResult(code2);
                    mPopupWindow.dismiss();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
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
//            popupWindow.showAsDropDown(tv, 0, 10);
            mPopupWindow.showAtLocation(addpic_dialog.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
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

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //====配件照片
            //拍照
            case 801:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_host);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }
                break;
            //相册
            case 809:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
                    Glide.with(mActivity).load(uri).into(iv_host);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }
                break;
            //=====整机照片
            //拍照
            case 901:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_accessories);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }
                break;
            //相册
            case 909:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
                    Glide.with(mActivity).load(uri).into(iv_accessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }
                break;
        }

        if (requestCode == 100) {
            if (data != null) {
                AddressList address = (AddressList) data.getSerializableExtra("address");
                if (address != null) {
                    AddressBack = address.getProvince() + address.getCity() + address.getArea() + address.getDistrict() + address.getAddress() + "(" + address.getUserName() + "收)" + address.getPhone();
                    mTvAddressReturn.setText(AddressBack);
                }
            }
        }
    }

    /**
     * 添加配件图片
     *
     * @param map
     */
    public void ApplyAccessoryphotoUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        MultipartBody requestBody = builder.build();
        mPresenter.ApplyAccessoryphotoUpload(requestBody);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
