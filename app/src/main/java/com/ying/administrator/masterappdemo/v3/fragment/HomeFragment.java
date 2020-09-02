package com.ying.administrator.masterappdemo.v3.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.CodeMoney;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.v3.activity.MessageActivity;
import com.ying.administrator.masterappdemo.v3.activity.ServingDetailActivity;
import com.ying.administrator.masterappdemo.v3.adapter.HomeAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.HomePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.HomeContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.HomeModel;
import com.ying.administrator.masterappdemo.widget.SwitchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements View.OnClickListener, HomeContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;
    @BindView(R.id.ll_switch)
    LinearLayout mLlSwitch;
    @BindView(R.id.rv_new_order)
    RecyclerView mRvNewOrder;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_red)
    ImageView mIvRed;
    private String mContentText;
    private int i = 0;
    private List<Article.DataBean> datalist = new ArrayList<>();
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private HomeAdapter adapter;
    private String userId;
    private int page = 1;
    private WorkOrder workOrder;
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private CodeMoney codeList;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private EditText et_message;
    private Button negtive;
    private Button positive;
    private TextView title;
    private AlertDialog cancelDialog;
    private boolean yn=true; //true 接单 false 拒接
    private Spinner mSpinnerObject;
    private String orderid;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
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
    protected int setLayoutId() {
        return R.layout.v3_fragment_home;
    }

    @Override
    protected void initData() {

        adapter = new HomeAdapter(R.layout.v3_item_home, list);
        mRvNewOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvNewOrder.setAdapter(adapter);
        adapter.setEmptyView(getHomeEmptyView());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_orders://接单
                        yn=true;
                        orderid =list.get(position).getOrderID();
                        orders(position);
                        break;
                    case R.id.tv_cancel://拒接
                        yn=false;
                        cancelOrder(position);
                        break;
                    case R.id.iv_copy:
                        copy(position);
                        break;
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ServingDetailActivity.class);
                intent.putExtra("codeValue", codeList.getCodeValue());//保外单需收的费用
                intent.putExtra("id", list.get(position).getOrderID());
                startActivity(intent);
            }
        });
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.resetNoMoreData();
                list.clear();
                page = 1;
                getOrderList();
                mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");
                mPresenter.messgIsOrNo(userId, "1", "1");
                refreshlayout.resetNoMoreData();
            }
        });


        //没满屏时禁止上拉
//        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++; //页数加1
                // 通过Math的random()函数返回一个double类型随机数，范围[0.0, 1.0)
                getOrderList();
                refreshlayout.finishLoadmore();
            }
        });

    }
    //复制
    private void copy(int position) {
        myClip = ClipData.newPlainText("", "下单厂家："+list.get(position).getInvoiceName() + "\n"
                +"工单号："+list.get(position).getOrderID() + "\n"
                +"下单时间："+list.get(position).getCreateDate() + "\n"
                +"用户信息："+list.get(position).getUserName()+" "+list.get(position).getPhone() + "\n"
                +"用户地址："+list.get(position).getAddress() + "\n"
                +"产品信息："+list.get(position).getProductType() + "\n"
                +"售后类型："+list.get(position).getGuaranteeText() + "\n"
                +"服务类型："+list.get(position).getTypeName()
        );
        myClipboard.setPrimaryClip(myClip);
        ToastUtils.showShort("复制成功");
    }

    //接单
    private void orders(final int position) {
        if ("保外".equals(list.get(position).getGuaranteeText())) {
            customdialog_home_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_home, null);
            customdialog_home_dialog = new AlertDialog.Builder(mActivity)
                    .setView(customdialog_home_view)
                    .create();
            customdialog_home_dialog.show();
            TextView title = customdialog_home_view.findViewById(R.id.title);
            TextView message = customdialog_home_view.findViewById(R.id.message);
            Button negtive = customdialog_home_view.findViewById(R.id.negtive);
            Button positive = customdialog_home_view.findViewById(R.id.positive);
            title.setText("温馨提示");
            message.setText("敬爱的师傅：您接到的是保外服务单，费用由用户支付，平台仅收取" + codeList.getCodeValue() + "元派单费，工单完结后，请充值。谢谢！");
            negtive.setText("取消");
            positive.setText("接单");
            negtive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customdialog_home_dialog.dismiss();
                }
            });
            positive.setOnClickListener(new View.OnClickListener() {
                @SingleClick
                @Override
                public void onClick(View v) {
                    showProgress();
                    mPresenter.UpdateSendOrderState(list.get(position).getOrderID(), "1", "");
                    customdialog_home_dialog.dismiss();
                }
            });
        } else {
            // FIXME: 2020-07-15 重复同意接单问题
            showProgress();
            mPresenter.UpdateSendOrderState(list.get(position).getOrderID(), "1", "");
        }
    }

    //拒接
    private void cancelOrder(final int position) {
        View Cancelview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel, null);
        et_message = Cancelview.findViewById(R.id.et_message);
        negtive = Cancelview.findViewById(R.id.negtive);
        positive = Cancelview.findViewById(R.id.positive);
        mSpinnerObject = Cancelview.findViewById(R.id.spinner_object);

        mSpinnerObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        et_message.setText("");
                        break;
                    case 1:
                        et_message.setText("用户电话无法接通");
                        break;
                    case 2:
                        et_message.setText("用户不需要安装/维修");
                        break;
                    case 3:
                        et_message.setText("机器正常无需维修");
                        break;
                    case 4:
                        et_message.setText("用户退机");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        title = Cancelview.findViewById(R.id.title);
        title.setText("是否拒接工单");
        negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog.dismiss();
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = et_message.getText().toString();
                if (message == null || "".equals(message)) {
                    ToastUtils.showShort("请输入拒接工单理由");
                } else {
                    mPresenter.UpdateSendOrderState(list.get(position).getOrderID(), "-1", message);
                    cancelDialog.dismiss();
                }

            }
        });
        cancelDialog = new AlertDialog.Builder(mActivity).setView(Cancelview).create();
        cancelDialog.show();
        Window window1 = cancelDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window1.getAttributes();
        window1.setAttributes(layoutParams);
        window1.setBackgroundDrawable(new ColorDrawable());
    }

    private void setReason(final Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_message.setText(btn.getText());
            }
        });
    }

    private void getOrderList() {
        // 通过Math的random()函数返回一个double类型随机数，范围[0.0, 1.0)
        final double d = Math.random();
        // 通过d获取一个[0, 100)之间的整数
        final int i = (int) (d * 100);
        mPresenter.WorkerGetOrderList(userId, "0", page + "", "10",i);
    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        getOrderList();
        mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");
        mPresenter.messgIsOrNo(userId, "1", "1");
        mPresenter.GetCodeList("ProtectionCost", "1", "1");
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mLlMessage.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
        }
    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()) {
            case 200:
                if (page!=1&&baseResult.getData().getData().size()==0){
                    mRefreshLayout.finishLoadmoreWithNoMoreData();
                }
                if (page == 1) {
                    list.clear();
                }
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                adapter.setNewData(list);
                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                datalist = baseResult.getData().getData();
                mScrolltv.removeAllViews();
                mScrolltv.initView(R.layout.item_switchview, new SwitchView.ViewBuilder() {
                    @Override
                    public void initView(View view) {
                        final TextView tv_name = (TextView) view.findViewById(R.id.tv_content);
                        final TextView tv_url = (TextView) view.findViewById(R.id.tv_url);
                        tv_name.setText(datalist.get(i % datalist.size()).getTitle());
                        tv_url.setText(datalist.get(i % datalist.size()).getContent());
                        tv_name.setTag(i);

                        i++;
                        if (i == datalist.size()) {
                            i = 0;
                        }

                        tv_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = tv_name.getText().toString();
                                String content = tv_url.getText().toString();
                                Intent intent = new Intent(mActivity, WebActivity.class);
                                intent.putExtra("Url", content);
                                intent.putExtra("Title", title);
                                startActivity(intent);
                            }
                        });
                    }
                });
                break;
        }
    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        Data data = baseResult.getData();
        switch (baseResult.getStatusCode()) {
            case 200://200
                if (data.isItem1()) {//接单成功
                    if (yn){
                        Toast.makeText(getActivity(), "接单成功", LENGTH_SHORT).show();
                        EventBus.getDefault().post(1);
                        EventBus.getDefault().post(2);
                        EventBus.getDefault().post(10);
                        Intent intent = new Intent(mActivity, ServingDetailActivity.class);
                        intent.putExtra("codeValue", codeList.getCodeValue());//保外单需收的费用
                        intent.putExtra("id", orderid);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "已拒绝接单", LENGTH_SHORT).show();
                        EventBus.getDefault().post(0);
                    }
                } else {
                    Toast.makeText(getActivity(), (CharSequence) data.getItem2(), LENGTH_SHORT).show();
                }
                hideProgress();
                break;

            default:
                break;
        }
    }

    @Override
    public void messgIsOrNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    mIvRed.setVisibility(View.VISIBLE);
                } else {
                    mIvRed.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void GetCodeList(BaseResult<List<CodeMoney>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                codeList = baseResult.getData().get(0);
                break;
        }
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case 10:
            case 0:
                list.clear();
                page = 1;
                getOrderList();
                break;
            case Config.ORDER_READ:

//                mPresenter.WorkerGetOrderRed(userid);

            default:
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("transaction_num".equals(name)) {
            mPresenter.messgIsOrNo(userId, "1", "1");
        }
    }
}
