package com.ying.administrator.masterappdemo.util;

import android.graphics.drawable.Drawable;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankUtil {

    public static List<Bank> getBankList(){
       List<Bank> list=new ArrayList<>();
        list.add(new Bank("光大银行",R.mipmap.gaungda));
        list.add(new Bank("广发银行",R.mipmap.gaungfa));
        list.add(new Bank("工商银行",R.mipmap.gongshang));
        list.add(new Bank("华夏银行",R.mipmap.huaxia));
        list.add(new Bank("建设银行",R.mipmap.jianshe));
        list.add(new Bank("交通银行",R.mipmap.jiaotong));
        list.add(new Bank("民生银行",R.mipmap.minsheng));
        list.add(new Bank("宁波银行",R.mipmap.ningbo));
        list.add(new Bank("农业银行",R.mipmap.nongye));
        list.add(new Bank("浦发银行",R.mipmap.pufa));
        list.add(new Bank("兴业银行",R.mipmap.xinye));
        list.add(new Bank("邮政储蓄",R.mipmap.youzheng));
        list.add(new Bank("招商银行",R.mipmap.zhaoshan));
        list.add(new Bank("浙商银行",R.mipmap.zheshang));
        list.add(new Bank("中国银行",R.mipmap.zhongguo));
        list.add(new Bank("中信银行",R.mipmap.zhongxin));
        return list;
    }
}
