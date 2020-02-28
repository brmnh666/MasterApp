package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.AppointmentDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AppointmentDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.AppointmentDetailsModel;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

import static android.widget.Toast.LENGTH_SHORT;

public class AppointmentDetailsActivity extends BaseActivity<AppointmentDetailsPresenter, AppointmentDetailsModel> implements View.OnClickListener, AppointmentDetailsContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_numbering)
    TextView mTvNumbering;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_billing_time)
    TextView mTvBillingTime;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.ll_change_address)
    LinearLayout mLlChangeAddress;
    @BindView(R.id.tv_platform_price)
    TextView mTvPlatformPrice;
    @BindView(R.id.ll_platform_price)
    LinearLayout mLlPlatformPrice;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_specifications)
    TextView mTvSpecifications;
    @BindView(R.id.tv_maintenance_information)
    TextView mTvMaintenanceInformation;
    @BindView(R.id.ll_maintenance_information)
    LinearLayout mLlMaintenanceInformation;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_success)
    TextView mTvSuccess;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.ll_reservation)
    LinearLayout mLlReservation;
    @BindView(R.id.tv_reservation)
    TextView mTvReservation;
    @BindView(R.id.ll_telephone)
    LinearLayout mLlTelephone;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    private View under_review;
    private AlertDialog underReviewDialog;
    private long recommendedtime;
    private String orderId;
    private WorkOrder.DataBean data;
    private EditText et_message;
    private Button negtive;
    private Button positive;
    private TextView title;
    private AlertDialog cancelDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_appointment_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("预约详情");
        orderId = getIntent().getStringExtra("id");
        mPresenter.GetOrderInfo(orderId);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvReservation.setOnClickListener(this);
        mLlTelephone.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_reservation:
                if (data.getIsCall() == null) {
                    under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_reservation, null);
                    TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
                    TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
                    tv_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            underReviewDialog.dismiss();
                        }
                    });

                    tv_reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            call("tel:" + data.getPhone());
                            mPresenter.OrderIsCall(orderId,"Y");
                            underReviewDialog.dismiss();
                        }
                    });
                    underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                    underReviewDialog.show();
                }else {
                    RxPermissions rxPermissions = new RxPermissions(this);
                    rxPermissions.request(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {
                                    if (aBoolean) {
                                        // 获取全部权限成功

                                        chooseTime("请选择上门时间");
                                    } else {
                                        // 获取全部权限失败
//                                                Log.d("=====>", "权限获取失败");
                                        ToastUtils.showShort("权限获取失败");
                                    }
                                }
                            });
                }
//

                break;
            case R.id.ll_telephone:
                call("tel:" + data.getPhone());
                mPresenter.OrderIsCall(orderId,"Y");
                break;
            case R.id.tv_cancel:

                View Cancelview=LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel,null);
                et_message = Cancelview.findViewById(R.id.et_message);
                negtive = Cancelview.findViewById(R.id.negtive);
                positive = Cancelview.findViewById(R.id.positive);
                title = Cancelview.findViewById(R.id.title);
                title.setText("是否取消工单");
                negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelDialog.dismiss();
                    }
                });

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message= et_message.getText().toString();
                        if (message==null||"".equals(message)){
                            ToastUtils.showShort("请输入取消工单理由");
                        }else {
//                                    mPresenter.UpdateOrderState(OrderId, "-1",message);
                            mPresenter.UpdateSendOrderState(orderId,"-1",message);
                            cancelDialog.dismiss();
                        }

                    }
                });

                cancelDialog = new AlertDialog.Builder(mActivity).setView(Cancelview).create();
                cancelDialog.show();
                Window window1= cancelDialog.getWindow();
                WindowManager.LayoutParams layoutParams=window1.getAttributes();
                window1.setAttributes(layoutParams);
                window1.setBackgroundDrawable(new ColorDrawable());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 选择上门时间
     */
    public void chooseTime(final String title) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format1 = format.format(date);

        TimeSelector timeSelector = new TimeSelector(mActivity, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    recommendedtime = format.parse(time).getTime();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mPresenter.UpdateSendOrderUpdateTime(orderId, time, time);


//               Intent intent=new Intent(mActivity, WorkOrderDetailsActivity2.class);
//                intent.putExtra("OrderID",OrderId);
//                intent.putExtra("time",time);
//                startActivity(intent);
//                successposition=position;


            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.setNextBtTip("确定");
        timeSelector.show();
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    data = baseResult.getData();
                    if ("Y".equals(data.getExtra()) && !"0".equals(data.getExtraTime())) {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName() + "/加急");
                    } else {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName());
                    }
                    mTvType.setText(data.getTypeName());
                    mTvStatus.setText(data.getStateStr());
                    mTvNumbering.setText(data.getOrderID());
                    if (("Y").equals(data.getGuarantee())) {
                        mTvPayment.setText("平台代付");

                    } else {
                        mTvPayment.setText("客户付款");

                    }

                    mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                    mTvDescription.setText("描述：" + data.getMemo());
                    mTvName.setText(data.getUserName() + "    " + data.getPhone());
                    mTvAddress.setText(data.getAddress());
                    mTvDistance.setText("线路里程 " + data.getDistance() + "公里");

                }
                break;
        }
    }

    @Override
    public void OrderIsCall(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mPresenter.GetOrderInfo(orderId);
                }
                break;
        }
    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    //mRefreshLayout.autoRefresh(0,0,1);
                    finish();
                } else {
                    Toast.makeText(mActivity, "取消失败", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().isItem1()) {
                    mPresenter.AddOrderSuccess(orderId,"1","预约成功");
                    if (data.getAddress() == null) {
                        Log.d("=====>", "地址为空");
                    } else {
                        Log.d("=====>", data.getAddress());
                    }
                    CalendarEvent calendarEvent = new CalendarEvent(
                            data.getTypeName() + "工单号：" + data.getOrderID(),
                            "客户名:" + data.getUserName() + " 客户手机号:" + data.getPhone() + "故障原因" + data.getMemo(),
                            data.getAddress(),
                            recommendedtime,
                            recommendedtime,
                            60, null    //提前一个小时提醒  单位分钟
                    );
                    // 添加事件
                    int result = CalendarProviderManager.addCalendarEvent(mActivity, calendarEvent);
                    if (result == 0) {
                        Toast.makeText(mActivity, "已为您添加行程至日历,将提前一小时提醒您！！", Toast.LENGTH_SHORT).show();
                    } else if (result == -1) {
                        Toast.makeText(mActivity, "插入失败", LENGTH_SHORT).show();
                    } else if (result == -2) {
                        Toast.makeText(mActivity, "没有权限", LENGTH_SHORT).show();
                    }









                }
        }
    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("预约成功");
                    //Toast.makeText(getActivity(),"预约成功请到服务中",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(2);//预约成功跳转到服务中
                    EventBus.getDefault().post("待服务");
                    finish();
                } else {


                }
                break;
            default:
                break;

        }

    }
}
