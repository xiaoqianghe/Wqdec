package com.yltx.modulewd.constant;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public interface UserInfo {
    String USER_ID = "USER_ID";
    String USER_ID_CARD = "USER_ID_CARD";
    String USER_NAME = "USER_NAME";
    String USER_PHONE = "USER_PHONE";
    String USER_BANK = "USER_BANK";     //银行卡名称
    String USER_BANK_CARD = "USER_BANK_CARD";   //银行卡号

    //// TODO: 2017/10/24  账号 页面字段

//    annualFeePayTime    会员年费缴费时间
//    annualFeeValidate   会员年费到期时间
//    levelName           等级
//    creditScore         信用分

    String ANNUAL_FEE_PAY_TIME = "ANNUAL_FEE_PAY_TIME";//会员年费缴费时间
    String ANNUAL_FEE_VALIDATE = "ANNUAL_FEE_VALIDATE";//会员年费到期时间
    String LEVEL_NAME = "LEVEL_NAME";//等级
    String CREDIT_SCORE = "CREDIT_SCORE";//信用分
    String IS_PAY_FEE = "IS_PAY_FEE";//开通会员

    ///zhimaStatus   芝麻认证zhimaCredit  芝麻信用 isHaveBank信用卡 isPayFee   开通会员
    String USER_ZHIMA_STATUS = "USER_ZHIMA_STATUS";//芝麻认证
    String USER_ZHIMA_CREDIT = "USER_ZHIMA_CREDIT";//芝麻信用
    String USER_ISHAVE_BANK = "USER_ISHAVE_BANKUS";//绑定信用卡







}
