package com.ying.administrator.masterappdemo.mvp.service;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Address;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.CategoryData;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.Logistics;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;
import com.ying.administrator.masterappdemo.entity.RedPointData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.Skill;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * 判断用户名是否可用
     */
    @FormUrlEncoded
    @POST("Account/ValidateUserName")
    Observable<BaseResult<String>> ValidateUserName(@Field("UserID") String userName);

    /**
     * 获取短信
     */
    @FormUrlEncoded
    @POST("Message/Send")
    Observable<BaseResult<Data<String>>> GetCode(@Field("mobile") String mobile,
                                           @Field("type") String type,
                                           @Field("roleType") String roleType);



    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("Account/Reg")
    Observable<BaseResult<Data<String>>> Reg(@Field("mobile") String mobile,
                                             @Field("type") String type,
                                             @Field("code") String code,
                                             @Field("roleType") String roleType,
                                             @Field("password") String password
                                            );


    /*短信登陆*/
    @FormUrlEncoded
    @POST("Account/LoginOnMessage")
    Observable<BaseResult<Data<String>>> LoginOnMessage(@Field("mobile") String mobile,
                                                        @Field("code") String code,
                                                        @Field("roleType") String roleType);



    /**
     * app用户登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOn")
    Observable<BaseResult<Data<String>>> LoginOn(@Field("userName") String userName,
                                                 @Field("passWord") String passWord,
                                                 @Field("RoleType") String RoleType);

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOut")
    Observable<BaseResult<Data<String>>> LoginOut(@Field("UserID") String UserID,@Field("Type") String type);
    /**
     * app获取用户信息
     */
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Body RequestBody json);




    /**
     * app获取用户信息
     */
    @FormUrlEncoded
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Field("userName") String userName);


    /*获取用户信息*/
    @FormUrlEncoded
    @POST("Account/GetUserInfoList")
    Observable<BaseResult<UserInfo>> GetUserInfoList(@Field("UserID") String UserID,
                                                     @Field("limit") String limit);


    /*
     *新增获取更新推送账户的token以及tags， 工厂的type是6 师傅的type是7 ， createtime可以不传 UserID为登录用户名
     * */
    @FormUrlEncoded
    @POST("Message/AddAndUpdatePushAccount")
    Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(@Field("token") String token,
                                                           @Field("type") String type,
                                                           @Field("UserID") String UserID);
/*

    */
/**
     * 获取工单列表
     * 废除-1，待审核0，派单中1，服务中2，已完成3
     * 获取首页 的单子
     *//*

    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoList(@Field("SendUser") String SendUser,
                                                       @Field("state") String state,
                                                       @Field("page") String page,
                                                       @Field("limit") String limit);
*/


    /*
    * 师傅端获取工单列表新接口
    * 师傅端state

0、待接单
1、已接待预约
2、服务中
3、返件单
4、质保单
5、完成待取机
6、已完成
7、预约不成功
    * */
   @FormUrlEncoded
   @POST("Order/WorkerGetOrderList")
   Observable<BaseResult<WorkOrder>> WorkerGetOrderList(@Field("UserID") String UserID,
                                                        @Field("State") String State,
                                                        @Field("page") String page,
                                                        @Field("limit") String limit);


/*

    @FormUrlEncoded
    @POST("Order/WorkerGetOrderList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(@Field("UserID") String UserID,
                                                            @Field("State") String State,
                                                            @Field("page") String page,
                                                            @Field("limit") String limit);
*/


    /*
     *
     * 获取工单详情
     * 通过OrderID获取工单详情
     * */
    @FormUrlEncoded
    @POST("Order/GetOrderInfo")
    Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(@Field("OrderID") String OrderID);



    /**
     * 提交抢单申请
     */
    @FormUrlEncoded
    @POST("Order/GrabOrder")
    Observable<BaseResult<Data>> AddGrabsheetapply (@Field("OrderID") String OrderID,
                                                    @Field("UserID") String UserID);


    /*主账号转派订单操作*/

    @FormUrlEncoded
    @POST("Order/ChangeSendOrder")
    Observable<BaseResult<Data>> ChangeSendOrder(@Field("OrderID") String OrderID,
                                                @Field("UserID") String UserID);



    /*
     *
    接单接口
    *
    * */

    @FormUrlEncoded
    @POST("Order/UpdateSendOrderState")
    Observable<BaseResult<Data>> UpdateSendOrderState(@Field("OrderID") String OrderID,
                                                      @Field("State") String State);


/*
* 快递信息
* */
    @FormUrlEncoded
    @POST("Order/GetExpressInfo")
    Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(@Field("ExpressNo") String ExpressNo);


  /*  *//*获取派单列表*//*
    @FormUrlEncoded
    @POST("Order/GetSendOrderList")
    Observable<BaseResult<SendOrder>> GetSendOrderList(@Field())


*/
    /**
     * 获取其他状态的 获取已接的订单
     * **/
  /*  @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(@Field("state") String state,
                                                            @Field("page") String page,
                                                          @Field("limit") String limit,
                                                            @Field("SendUser") String SendUser);*/

    /**
     * 订单维修图片
     * @param body
     * @return
     */
    @POST("Upload/ReuturnAccessoryPicUpload")
    Observable<BaseResult<Data<String>>> ReuturnAccessoryPicUpload(@Body RequestBody body);



    /**
     * 订单完成维修图片
     * @param body
     * @return
     */
  @POST("Upload/FinishOrderPicUpload")
  Observable<BaseResult<Data<String>>> FinishOrderPicUpload(@Body RequestBody body);

    /**
     * 订单服务图片
     * @param body
     * @return
     */
  @POST("Upload/ServiceOrderPicUpload")
  Observable<BaseResult<Data<String>>> ServiceOrderPicUpload(@Body RequestBody body);




    /**
     * 远程费图片
     * @param body
     * @return
     */
  @POST("Upload/OrderByondImgPicUpload")
  Observable<BaseResult<Data<String>>> OrderByondImgPicUpload(@Body RequestBody body);

    /**
     * 配件图片
     * @param body
     * @return
     */
    @POST("Upload/ApplyAccessoryphotoUpload")
    Observable<BaseResult<Data<String>>> ApplyAccessoryphotoUpload(@Body RequestBody body);



    /*申请远程费距离*/
    @FormUrlEncoded
    @POST("Order/ApplyBeyondMoney")
    Observable<BaseResult<Data<String>>> ApplyBeyondMoney(@Field("OrderID") String OrderID,
                                                          @Field("BeyondMoney") String BeyondMoney,
                                                          @Field("BeyondDistance") String BeyondDistance);





    /*获取返件图片*/
    @FormUrlEncoded
    @POST("Order/GetReturnAccessoryByOrderID")
    Observable<BaseResult<String>> GetReturnAccessoryByOrderID(@Field("OrderID") String OrderID);

    /**
     * 提交师傅预约失败的原因
     * **/
    @FormUrlEncoded
    @POST("Order/UpdateSendOrderAppointmentState")
    Observable<BaseResult<Data>> AddOrderfailureReason(@Field("OrderID") String OrderID,
                                                       @Field("AppointmentState") String AppointmentState,
                                                       @Field("AppointmentMessage") String AppointmentMessage);



    /**
     * 提交师傅预约预约成功
     * **/
    @FormUrlEncoded
    @POST("Order/UpdateSendOrderAppointmentState")
    Observable<BaseResult<Data>> AddOrderSuccess(@Field("OrderID") String OrderID,
                                                 @Field("AppointmentState") String AppointmentState,
                                                 @Field("AppointmentMessage") String AppointmentMessage);




    /*提交返件信息快递单号加快递公司*/

    @FormUrlEncoded
    @POST("Order/AddReturnAccessory")
    Observable<BaseResult<Data<String>>> AddReturnAccessory(@Field("OrderID") String OrderID,
                                                            @Field("ReturnAccessoryMsg") String ReturnAccessoryMsg,
                                                            @Field("PostMoney") String PostMoney
    );

     @FormUrlEncoded
    @POST("Order/UpdateOrderState")
    Observable<BaseResult<Data<String>>> UpdateOrderState (@Field("OrderID") String OrderID,
                                                      @Field("State") String State);
    /*
     *
     * 获取工厂配件信息
     * */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryAccessory")
    Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory(@Field("FCategoryID") String FProductTypeID);

    /*获取工厂服务信息*/
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryService")
    Observable<BaseResult<GetFactoryData<Service>>> GetFactoryService(@Field("FBrandID") String FBrandID,@Field("FCategoryID") String FCategoryID);

/*

    */
/*上传远程费*//*

    @POST("Upload/OrderByondImgPicUpload")
    Observable<BaseResult<Data<String>>> OrderByondImgPicUpload(@Body RequestBody body);

*/






     /*根据工单号获取配件列表*/
    @FormUrlEncoded
    @POST("Order/GetOrderAccessoryByOrderID")
    Observable<BaseResult<List<GAccessory>>> GetOrderAccessoryByOrderID(@Field("OrderID") String OrderID);



    /**
     * 上传身份证以及清晰头像 UserID用户名Sort（1：正面，2：反面，3：清晰自拍照）
     * */
    @POST("Upload/IDCardUpload")
    Observable<BaseResult<Data<String>>> IDCardUpload(@Body RequestBody json);

    /**
    * 预接单提交配件信息  json
    *
    *
    * {
    "OrderID":"123",
    "AccessorySequency":"0",
    "OrderAccessoryStr":{
    "OrderAccessory":[
    {
    "FAccessoryID":"1",
    "FAccessoryName":"PC管",
    "Quantity":"2",
    "Price":"1",
    "DiscountPrice":"1"
    }
    ]
    }
    }
    *
    * */
    @POST("Order/AddOrderAccessory")
    Observable<BaseResult<Data>> AddOrderAccessory(@Body RequestBody json);
     /*添加服务*/
    @POST("Order/AddOrderService")
    Observable<BaseResult<Data>> AddOrderService(@Body RequestBody json);



    /*预接单页面提交上门时间 */
    @POST("Order/AddOrUpdateAccessoryServiceReturn")
    Observable<BaseResult<Data<String>>> AddOrUpdateAccessoryServiceReturn(@Body RequestBody json);


    @FormUrlEncoded
    @POST("Order/UpdateSendOrderUpdateTime")
    Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(@Field("OrderID") String OrderID,
                                                           @Field("UpdateDate") String UpdateDate,
                                                           @Field("UpdateDate2") String EndDate);

    /**
     * 获取分类
     */
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<CategoryData>> GetFactoryCategory();
//    /**
//     * 获取子分类
//     */
//    @FormUrlEncoded
//    @POST("FactoryConfig/GetFactoryCategory")
//    Observable<BaseResult<CategoryData>> GetChildFactoryCategory(@Field("ParentID") String ParentID);

   /*获取账户的技能*/
    @FormUrlEncoded
    @POST("Account/GetAccountSkill")
    Observable<BaseResult<List<Skill>>> GetAccountSkill(@Field("UserID") String UserID);

    /**
     * 添加技能
     * @param UserID
     * @return
     */
    @FormUrlEncoded
    @POST("Account/UpdateAccountSkillData")
    Observable<BaseResult<String>> UpdateAccountSkillData(@Field("UserID") String UserID,@Field("NodeIds") String NodeIds);

    /**
     * 获取省
     */
    @POST("Config/GetProvince")
    Observable<BaseResult<List<Province>>> GetProvince();
    /**
     * 获取市
     */
    @FormUrlEncoded
    @POST("Config/GetCity")
    Observable<BaseResult<Data<List<City>>>> GetCity(@Field("parentcode") String parentcode);
    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetArea")
    Observable<BaseResult<Data<List<Area>>>> GetArea(@Field("parentcode") String parentcode);

    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetDistrict")
    Observable<BaseResult<Data<List<District>>>> GetDistrict(@Field("parentcode") String parentcode);
    /**
     * 提交认证申请
     */
    @FormUrlEncoded
    @POST("Account/ApplyAuthInfo")
    Observable<BaseResult<Data<String>>> ApplyAuthInfo(
            @Field("UserID") String UserID,
            @Field("TrueName") String TrueName,
            @Field("Sex") String Sex,
            @Field("IDCard") String IDCard,
            @Field("Address") String Address,
            @Field("NodeIds") String NodeIds,
            @Field("Province") String Province,
            @Field("City") String City,
            @Field("Area") String Area,
            @Field("District") String District,
            @Field("Longitude") String Longitude,
            @Field("Dimension") String Dimension,
            @Field("ServiceAreaJsonStr") String ServiceAreaJsonStr,
            @Field("ISwoker") String Iswoker
    );

    @FormUrlEncoded
    @POST("Account/ApplyAuthInfo")
    Observable<BaseResult<Data<String>>> ApplyAuthInfoBysub(@Field("UserID") String UserID,
                                                            @Field("TrueName") String TrueName,
                                                            @Field("Sex") String Sex,
                                                            @Field("IDCard") String IDCard,
                                                            @Field("Address") String Address,
                                                            @Field("Province") String Province,
                                                            @Field("City") String City,
                                                            @Field("Area") String Area,
                                                            @Field("District") String District,
                                                            @Field("Longitude") String Longitude,
                                                            @Field("Dimension") String Dimension,
                                                            @Field("ISwoker") String Iswoker
                                                            );




      /*更新服务区域*/
    @FormUrlEncoded
    @POST("Account/AddorUpdateServiceArea")
    Observable<BaseResult<Data<String>>> AddorUpdateServiceArea(@Field("UserID") String UserID,
                                                                @Field("ServiceAreaJsonStr") String ServiceAreaJsonStr);



    /*
    获取子账号*/
    @FormUrlEncoded
    @POST("Account/GetChildAccountByParentUserID")
    Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>>
    GetChildAccountByParentUserID(@Field("ParentUserID") String ParentUserID);



    /*
    修改个人信息页面
    */

    /*个人信息页面修改头像*/
    @POST("Upload/UploadAvator")
    Observable<BaseResult<Data<String>>> UploadAvator(@Body RequestBody json);

    /*获取身份证的图片*/
    @FormUrlEncoded
    @POST("Account/GetIDCardImg")
    Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(@Field("UserID") String UserID);

    /*修改昵称*/
    @FormUrlEncoded
    @POST("Account/UpdateAccountNickName")
    Observable<BaseResult<Data>> UpdateAccountNickName(@Field("UserID") String UserID,
                                                       @Field("NickName") String NickName);
    /*修改密码*/

    @FormUrlEncoded
    @POST("Account/UpdatePassword")
    Observable<BaseResult<Data>> UpdatePassword(@Field("UserID") String UserID,
                                                @Field("Password") String Password);
    /*修改性别*/
    @FormUrlEncoded
    @POST("Account/UpdateSex")
    Observable<BaseResult<Data>> UpdateSex(@Field("UserID") String UserID,
                                           @Field("Sex") String Sex);

    /**
     * 充值信息
     * @param UserID 账号
     * @param TotalAmount 金额
     * @param Type  1余额 2 诚意金 3订单支付
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetOrderStr")
    Observable<BaseResult<Data<String>>> GetOrderStr(@Field("UserID") String UserID,
                                                     @Field("BisId") String BisId,
                                                     @Field("OrderId") String OrderId,
                                                     @Field("TotalAmount") String TotalAmount,
                                                     @Field("Type") String Type,
                                                     @Field("JsonStr") JSONArray JsonStr
    );
    /**
     * 充值信息
     * @param UserID 账号
     * @param TotalAmount 金额
     * @param Type  1余额 2 诚意金 3订单支付
     * @param Style  工厂传factory 商城mall
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetWXOrderStr")
    Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(@Field("UserID") String UserID,
                                                          @Field("BisId") String BisId,
                                                          @Field("OrderId") String OrderId,
                                                          @Field("TotalAmount") String TotalAmount,
                                                          @Field("Type") String Type,
                                                          @Field("Style") String Style,
                                                          @Field("JsonStr")JSONArray JsonStr);
    /**
     * 微信人工回调OutTradeNo
     * @param OutTradeNo
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/WXNotifyManual")
    Observable<BaseResult<Data<String>>> WXNotifyManual(@Field("OutTradeNo") String OutTradeNo);

    /**
     * 完成工单（返件列表）
     * @param OrderID 订单id
     * @return
     */
    @FormUrlEncoded
    @POST("Order/UpdateContinueServiceState")
    Observable<BaseResult<Data<String>>> UpdateContinueServiceState(@Field("OrderID") String OrderID);

    /**
     * 催件
     * @param OrderID 订单id
     * @param Content 内容
     * @return
     */
    @FormUrlEncoded
    @POST("Order/PressFactoryAccount")
    Observable<BaseResult<Data<String>>> PressFactoryAccount(@Field("OrderID") String OrderID,
                                                     @Field("Content") String Content);

    /**
     * 获取文章列表
     * 师傅端
     * 7系统消息 8平台政策 9平台新闻 10接单必读
     * @param CategoryID 栏目id
     * @return
     */
    @FormUrlEncoded
    @POST("Cms/GetListCategoryContentByCategoryID")
    Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(
            @Field("CategoryID") String CategoryID
    );

    /*
    * 注销子账号
    * */
    @FormUrlEncoded
    @POST("Account/CancelChildAccount")
    Observable<BaseResult<Data<String>>> CancelChildAccount(@Field("UserID") String UserID,
                                                            @Field("ParentUserID") String ParentUserID);

    /*获取用户账单*/
    @FormUrlEncoded
    @POST("Account/AccountBill")
    Observable<BaseResult<Data<Bill>>> AccountBill(@Field("UserID") String UserID,
                                                   @Field("state") String state);

    /*添加银行卡*/
    @FormUrlEncoded
    @POST("Account/AddorUpdateAccountPayInfo")
    Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(@Field("UserID") String UserID,
                                                                   @Field("PayInfoCode") String PayInfoCode,
                                                                   @Field("PayInfoName") String PayInfoName,
                                                                   @Field("PayNo") String PayNo);
    /*获取银行卡*/
    @FormUrlEncoded
    @POST("Account/GetAccountPayInfoList")
    Observable <BaseResult<List<BankCard>>> GetAccountPayInfoList(@Field("UserID") String UserID);


    /*获取服务区域*/
    @FormUrlEncoded
    @POST("Account/GetServiceRangeByUserID")
    Observable<BaseResult<List<Address>>> GetServiceRangeByUserID(@Field("UserID") String UserID);


    /*根据银行卡号获取银行名 判断后台是否支持该银行的提现*/

    @FormUrlEncoded
    @POST("Account/GetBankNameByCardNo")
    Observable<BaseResult<Data<String>>> GetBankNameByCardNo(@Field("CardNo") String CardNo);

    /*获取消息列表*/

    /*获取个人消息  1.交易消息类型  2.订单消息类型*/
    @FormUrlEncoded
    @POST("Cms/GetListmessageByType")
    Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(@Field("UserID") String UserID,
                                                                      @Field("Type") String Type,
                                                                      @Field("SubType") String SubType,
                                                                      @Field("limit") String limit,
                                                                      @Field("page") String page,
                                                                      @Field("IsLook") String IsLook);



    /*提现页面获取提现页面的数据*/
    @FormUrlEncoded
    @POST("Account/GetDepositMoneyDisplay")
    Observable<BaseResult<WithDrawMoney>> GetDepositMoneyDisplay(@Field("UserID") String UserID);


    /*提现*/
    @FormUrlEncoded
    @POST("Account/WithDraw")
    Observable<BaseResult<Data<String>>> WithDraw(@Field("DrawMoney") String DrawMoney,
                                                  @Field("CardNo") String CardNo,
                                                  @Field("UserID") String UserID);

    /**
     * 意见反馈
     * @param UserID 用户id
     * @param BackType  1.账号问题 2.支付问题 3.其他问题
     * @param Content 描述
     * @return
     */
    @FormUrlEncoded
    @POST("Account/AddOpinion")
    Observable<BaseResult<Data<String>>> AddOpinion(@Field("UserID") String UserID,
                                                  @Field("BackType") String BackType,
                                                  @Field("Content") String Content);



    /*更新消息状态点击后*/
    @FormUrlEncoded
    @POST("Cms/AddOrUpdatemessage")
    Observable<BaseResult<Data<String>>> AddOrUpdatemessage(@Field("MessageID") String MessageID,
                                                            @Field("IsLook") String IsLook);


    /*获取状态改变小红点*/
    @FormUrlEncoded
    @POST("Order/WorkerGetOrderRed")
    Observable<BaseResult<RedPointData>> WorkerGetOrderRed(@Field("UserID") String UserID);

    /*更新工单消息为已读*/
    @FormUrlEncoded
    @POST("Order/UpdateOrderIsLook")
    Observable<BaseResult<Data<String>>> UpdateOrderIsLook(@Field("OrderID") String OrderID,
                                                           @Field("IsLook") String IsLook);

    /**
     * 根据大分类id获取题目
     * @param QuesCategory
     * @return
     */
    @FormUrlEncoded
    @POST("cms/GetQuestionBycategory")
    Observable<BaseResult<Data<List<QuestBean>>>> GetQuestionBycategory(@Field("QuesCategory") String QuesCategory);

    /**
     * 交卷
     * @param Answer
     * @return
     */
    @FormUrlEncoded
    @POST("cms/Calculate")
    Observable<BaseResult<QuestResult>> Calculate(@Field("Answer") String Answer);



    /*
    * 忘记密码
    * */
    @FormUrlEncoded
    @POST("Account/ForgetPassword")
    Observable<BaseResult<Data<String>>> ForgetPassword(@Field("mobile") String mobile,
                                                        @Field("type") String type,
                                                        @Field("code") String code,
                                                        @Field("roleType") String roleType,
                                                        @Field("password") String password);
    /*
    * 是否冻结
    * */
    @FormUrlEncoded
    @POST("Order/ConfirmtoFreezeByOrderID")
    Observable<BaseResult<Data<String>>> ConfirmtoFreezeByOrderID(@Field("OrderID") String OrderID,
                                                        @Field("Type") String type);



    /*
     * 申请延期
     * */
    @FormUrlEncoded
    @POST("Order/ApplyAccessoryLater")
    Observable<BaseResult<Data<String>>> ApplyAccessoryLate(@Field("OrderID") String OrderID);
}
