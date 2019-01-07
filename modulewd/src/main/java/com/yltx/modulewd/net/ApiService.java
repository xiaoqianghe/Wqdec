package com.yltx.modulewd.net;

import com.yltx.modulewd.entity.AddBorrowRsBean;
import com.yltx.modulewd.entity.AnnualFeeInfo;
import com.yltx.modulewd.entity.BankCard;
import com.yltx.modulewd.entity.BankDetailRsBean;
import com.yltx.modulewd.entity.BankListRs;
import com.yltx.modulewd.entity.Borrow;
import com.yltx.modulewd.entity.BorrowList;
import com.yltx.modulewd.entity.BorrowRepayment;
import com.yltx.modulewd.entity.InstallmentDetail;
import com.yltx.modulewd.entity.Instalment;
import com.yltx.modulewd.entity.LoanDetailRsBean;
import com.yltx.modulewd.entity.MemberCreditInfo;
import com.yltx.modulewd.entity.MsgByPhone;
import com.yltx.modulewd.entity.Order;
import com.yltx.modulewd.entity.OrderResult;
import com.yltx.modulewd.entity.PaymentRecords;
import com.yltx.modulewd.entity.PostPwd;
import com.yltx.modulewd.entity.Result;
import com.yltx.modulewd.entity.SaveBankResult;
import com.yltx.modulewd.entity.ZhimaCreditStatus;
import com.yltx.modulewd.entity.ZhimaStatus;
import com.yltx.modulewd.entity.ZhimaUrl;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public interface ApiService {

    /**
     * 认证接口 A
     */

    /**
     * A1. 获取会员信息
     *
     * @param merchantNo
     * @return
     */
    @GET("loan-rs/member/getMemberCreditInfo")
    Observable<MemberCreditInfo> getMemberCreditInfo(@Query("merchantNo") String merchantNo, @Query("memberId") String memberId);

    /**
     * A2. 获取芝麻认证url
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/member/getZhimaUrl")
    Observable<ZhimaUrl> getZhimaUrl(@Query("memberId") String memberId, @Query("resources") String resources);

    /**
     * A3. 根据身份证号查询该会员芝麻认证状态，zhimaStatus 为1是成功，为0是失败
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/member/getZhimaStatus")
    Observable<ZhimaStatus> getZhimaStatus(@Query("memberId") String memberId);

    /**
     * A4. 判断芝麻信用分是否达标 返回code为success达标，error显示message
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/member/getZhimaCreditStatus")
    Observable<ZhimaCreditStatus> getZhimaCreditStatus(@Query("memberId") String memberId, @Query("validateCode") String validateCode);

    /**
     * A5. 发送验证码
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/member/sendMsgByPhone")
    Observable<MsgByPhone> sendMsgByPhone(@Query("memberId") String memberId);

    /**
     * A6. 根据身份证号查询年费信息
     * <p>
     * <p>
     * http://192.168.4.60:15555/loan-rs/member/getAnnualFeeInfo?memberId=4
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/member/getAnnualFeeInfo")
    Observable<AnnualFeeInfo> getAnnualFeeInfo(@Query("memberId") String memberId);


    /**
     * A7. 绑定信用卡 http://192.168.1.98:7788/loan-rs/bankCard/saveBankCard
     *
     * @param bankCard
     * @return
     */
    @POST("loan-rs/bankCard/saveBankCard")
    Observable<SaveBankResult> saveBankCard(@Body BankCard bankCard);

    /**
     * A8. 根据卡号获取银行名称
     *
     * @param beanCard
     * @return
     */
    @GET("loan-rs/bankCard/getBankName")
    Observable<Result> getBankName(@Query("beanCard") String beanCard);

    /**
     * A9. 设置交易密码
     *
     * @param memberId
     * @param oldPassword
     * @param newPassword
     * @param new2Password
     * @return
     */
    @GET("loan-rs/bankCard/setPassWord")
    Observable<Result> setPassWord(@Query("memberId") String memberId,
                                   @Query("oldPassword") String oldPassword,
                                   @Query("newPassword") String newPassword,
                                   @Query("new2Password") String new2Password);


    /**
     * A10. 开通会员
     *
     * @param order
     * @return
     */
    @POST("loan-rs/order/addOrder")
    Observable<OrderResult> addOrder(@Body Order order);

    /**
     * A11. 查所属银行
     *
     * @param beanCard
     * @return
     */
    @GET("loan-rs/bankCard/getBankName")
    Observable<SaveBankResult> getBankCard(@Query("beanCard") String beanCard);


//    /**
//     * 借款
//     */
//    @POST("loan-rs/borrow/addBorrow")
//    Observable<Result> addBorrow(@Body Borrow borrow);

    /**
     * 借款
     */
    @POST("loan-rs/borrow/addBorrow")
    Observable<AddBorrowRsBean> addBorrow(@Body Borrow borrow);

    /**
     * 借款详情接口--
     *
     * @param borrowId
     * @return
     */
    @GET("loan-rs/borrow/getBorrowDetail")
    Observable<Result> getBorrowDetail(@Query("borrowId") String borrowId);

    /**
     * 借款详情接口
     *
     * @param borrowId
     * @return
     */
    @GET("loan-rs/borrow/loanDetail")
    Observable<LoanDetailRsBean> loanDetail(@Query("borrowId") String borrowId);

    /**
     * 借还记录首页接口
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/borrow/getBorrowRepayment")
    Observable<BorrowRepayment> getBorrowRepayment(@Query("memberId") String memberId);

    /**
     * 借款记录
     *
     * @param memberId
     * @return
     */
    @GET("loan-rs/borrow/getBorrowList")
    Observable<BorrowList> getBorrowList(@Query("memberId") String memberId,
                                         @Query("totalStatus") String totalStatus,
                                         @Query("pageSize") int pageSize,
                                         @Query("pageNumber") int pageNumber);

    /**
     * 还款记录
     *
     * @param mbId
     * @param status
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("loan-rs/payment/getPaymentRecords")
    Observable<PaymentRecords> getPaymentRecords(@Query("mbId") String mbId,
                                                 @Query("status") String status,
                                                 @Query("pageNo") int pageNo,
                                                 @Query("pageSize") int pageSize);

    /**
     * 分期列表
     *
     * @param memberId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GET("loan-rs/borrow/getInstalmentList")
    Observable<Instalment> getInstalmentList(@Query("memberId") String memberId,
                                             @Query("pageSize") int pageSize,
                                             @Query("pageNumber") int pageNumber);


    /**
     * 分期还款more
     *
     * @param mbId
     * @param repayDate
     * @return
     */
    @GET("loan-rs/borrow/installmentDetail")
    Observable<InstallmentDetail> installmentDetail(@Query("mbId") String mbId,
                                                    @Query("repayDate") String repayDate);

    /**
     * 分期还款 single
     *
     * @param mbId
     * @param borrowId
     * @return
     */
    @GET("loan-rs/borrow/installmentLoanDetail")
    Observable<InstallmentDetail> installmentLoanDetail(@Query("mbId") String mbId,
                                                        @Query("borrowId") String borrowId,
                                                        @Query("repayId") String repayId);


    //设置、修改交易密码 http://192.168.1.98:7788/loan-rs/bankCard/setPassWord  POST

    //PostPwd
    @POST("loan-rs/bankCard/setPassWord")
    Observable<Result> setPassWord(@Body PostPwd mPostPwd);


    //重置密码
    //http://192.168.1.98:7788/loan-rs/bankCard/backPassWord
    //PostPwd
    @POST("loan-rs/bankCard/backPassWord")
    Observable<Result> backPassWord(@Body PostPwd mPostPwd);


    ///loan-rs/bankCard/getBankList?memberId=   //银行卡列表

    ///loan-rs/bankCard/getDepositBank?memberId=  储蓄卡详情

//    /loan-rs/bankCard/getCreditBank?memberId=  信用卡详情


    @GET("loan-rs/bankCard/getBankList")
    Observable<BankListRs> getBankList(@Query("memberId") String memberId);//银行卡 列表


    @GET("loan-rs/bankCard/getCreditBank")
    Observable<BankDetailRsBean> getCreditBank(@Query("memberId") String memberId);//信用卡详情


    @GET("loan-rs/bankCard/getDepositBank")
    Observable<BankDetailRsBean> getDepositBank(@Query("memberId") String memberId);//储值卡卡详情


}
