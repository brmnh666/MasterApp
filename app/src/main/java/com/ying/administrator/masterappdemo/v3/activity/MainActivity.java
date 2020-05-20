package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;

import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.Phone;
import com.ying.administrator.masterappdemo.entity.Phone2;
import com.ying.administrator.masterappdemo.entity.Phone3;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Login_New_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedActivity2;
import com.ying.administrator.masterappdemo.util.DesktopCornerUtil;
import com.ying.administrator.masterappdemo.v3.fragment.HomeFragment;
import com.ying.administrator.masterappdemo.v3.fragment.MineFragment;
import com.ying.administrator.masterappdemo.v3.fragment.OrderFragment;
import com.ying.administrator.masterappdemo.v3.fragment.ShopFragment;
import com.ying.administrator.masterappdemo.v3.fragment.StudyFragment;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.MainPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MainContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.MainModel;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;
import com.ying.administrator.masterappdemo.entity.Data;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements View.OnClickListener, MainContract.View {
    private static final String TAG = "MainActivity";
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_order)
    LinearLayout mLlOrder;
    @BindView(R.id.ll_study)
    LinearLayout mLlStudy;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.rootview)
    LinearLayout mRootview;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    private List<Fragment> fragmentList;
    private String userID;
    private UserInfo.UserInfoDean userInfo;
    private View under_review;
    private AlertDialog underReviewDialog;
    private TextView tv_content;
    private SPUtils spUtils;
    private GetMessagePag data;
    private String displayName;
    private List<Phone> list = new ArrayList<>();
    private String time = "1";

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID, "1");
        mPresenter.GetmessagePag(userID);
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance(""));
        fragmentList.add(OrderFragment.newInstance(""));
        fragmentList.add(StudyFragment.newInstance(""));
        fragmentList.add(ShopFragment.newInstance(""));
        fragmentList.add(MineFragment.newInstance(""));
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mViewPager.setScroll(false);
        mViewPager.setCurrentItem(0);
        tabSelected(mLlHome);
        //判断是否开启读取通讯录的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }
    }


    private void readContacts() {
        Cursor cursor = null;
        try {
            //查询联系人数据,使用了getContentResolver().query方法来查询系统的联系人的数据
            //CONTENT_URI就是一个封装好的Uri，是已经解析过得常量
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
            //对cursor进行遍历，取出姓名和电话号码
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));

//                    String address=cursor.getString(cursor.getColumnIndex(  ContactsContract.CommonDataKinds.Phone.DATA1));
                    //把取出的两类数据进行拼接，中间加换行符，然后添加到listview中
//                    contactsList.add(displayName+"\n"+number);

//                    ToastUtils.showShort(address);

//                    Log.d(TAG,"address---"+address);
                    list.add(new Phone(displayName, number));

                }

                Gson gson = new Gson();
//                Phone2 phone2=new Phone2();
//                phone2.setArray(list);
//                String s=gson.toJson(phone2);
                Phone3 phone3 = new Phone3();
                phone3.setUserId(userID);
                if ("1".equals(time)) {
                    list.clear();
                    phone3.setData(list);
                } else if (time == null || "".equals(time)) {
                    phone3.setData(list);
                } else {
                    String firstTime = getDateStr(time.replaceAll("/","-"), 90);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
//获取当前时间
                    Date date = new Date(System.currentTimeMillis());
                    String nowTime = simpleDateFormat.format(date);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//年-月-日 时-分
                    try {
                        Date date1 = dateFormat.parse(nowTime);//开始时间
                        Date date2 = dateFormat.parse(firstTime);//结束时间

                        if (date2.getTime() < date1.getTime()) {
                            phone3.setData(list);
//                            Toast.makeText(PostActivity.this, "结束时间小于开始时间", Toast.LENGTH_SHORT).show();
                        } else if (date2.getTime() == date1.getTime()) {
                            return;
//                            Toast.makeText(PostActivity.this, "开始时间与结束时间相同", Toast.LENGTH_SHORT).show();
                        } else if (date2.getTime() > date1.getTime()) {
                           return;
                            //正常情况下的逻辑操作.
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                String s1 = gson.toJson(phone3);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);
                mPresenter.AndroidTelephone(body);
//                Log.d(TAG,"--->"+s1);

                //刷新listview
//                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //记得关掉cursor
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //Day:日期字符串例如 2015-3-10  Num:需要减少的天数例如 7
    public static String getDateStr(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() + (long) Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    //时间戳转字符串

    public static String getStrTime(String timeStamp, String format) {

        String timeString = null;

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        long l = Long.valueOf(timeStamp);

        timeString = sdf.format(new Date(l));//单位秒

        return timeString;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
//                    Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(this).setTitle("通讯录权限")
                            .setMessage("是否打开通讯录权限？")
                            //  取消选项
                            .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Toast.makeText(MainActivity.this, "close", Toast.LENGTH_SHORT).show();
                                    // 关闭dialog
                                    dialogInterface.dismiss();
                                }
                            })
                            //  确认选项
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //跳转到手机原生设置页面
//                                    Intent intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
//                                    startActivityForResult(intent,1);
                                    toSelfSetting(mActivity);
                                }
                            })
                            .setCancelable(false)
                            .show();
                }
                break;
            default:
                break;
        }
    }

    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().getData().size() > 0) {
                    userInfo = baseResult.getData().getData().get(0);
                    if (userInfo.getIfAuth() == null) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_real, null);
                        TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
                        TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
                        tv_content = under_review.findViewById(R.id.tv_content);
                        tv_reservation.setText("去实名");
                        tv_content.setText("请还未实名认证，请先实名认证");
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });

                        tv_reservation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mActivity, VerifiedActivity2.class));
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if ("-1".equals(userInfo.getIfAuth())) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_real, null);
                        TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
                        TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
                        tv_content = under_review.findViewById(R.id.tv_content);
                        tv_reservation.setText("去实名");
                        tv_content.setText(userInfo.getAuthMessage() + ",有疑问请咨询客服电话。");
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });

                        tv_reservation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mActivity, VerifiedActivity2.class));
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else {
                        return;
                    }
                } else {
                    spUtils.put("isLogin", false);
                    startActivity(new Intent(mActivity, Login_New_Activity.class));
                    finish();
                }

                break;
        }
    }

    @Override
    public void GetmessagePag(BaseResult<Data<GetMessagePag>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData().getItem2();
                int num = data.getCount5() + data.getCount2() + data.getCount3() + data.getCount4() + data.getCount6();
                DesktopCornerUtil.setBadgeNumber(num);
                break;
        }
    }

    @Override
    public void AndroidTelephone(BaseResult<Data<String>> baseResult) {
        try {
            if ("操作成功".equals(baseResult.getData().getItem2())) {
                return;
            } else {
                time = baseResult.getData().getItem2();
                //判断是否开启读取通讯录的权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager
                        .PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                } else {
                    readContacts();
                }
            }
        }catch (Exception e){

        }

    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void setListener() {
        mLlHome.setOnClickListener(this);
        mLlOrder.setOnClickListener(this);
        mLlStudy.setOnClickListener(this);
        mLlMine.setOnClickListener(this);
        mLlShop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_order:
                mViewPager.setCurrentItem(1);
                tabSelected(mLlOrder);
                break;
            case R.id.ll_study:
                mViewPager.setCurrentItem(2);
                tabSelected(mLlStudy);
                break;
            case R.id.ll_mine:
                mViewPager.setCurrentItem(4);
                tabSelected(mLlMine);
                break;
            case R.id.ll_shop:
                mViewPager.setCurrentItem(3);
                tabSelected(mLlShop);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlOrder.setSelected(false);
        mLlStudy.setSelected(false);
        mLlMine.setSelected(false);
        mLlShop.setSelected(false);
        linearLayout.setSelected(true);
    }


    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case 10:
                mViewPager.setCurrentItem(1); //服务中 state 2
                tabSelected(mLlOrder);
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
            mPresenter.GetmessagePag(userID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                readContacts();
            }
        }

    }
}
