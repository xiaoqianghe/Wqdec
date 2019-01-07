package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public class ZhimaUrl {

    /**
     * code : success
     * message : 调用成功
     * data : {"creditId":null,"memberId":null,"levelId":null,"identiType":null,"creditScore":null,"creditLimit":null,"limitUsed":null,"limitUnused":null,"status":null,"frozenWay":null,"remark":null,"zhimaStatus":null,"zhimaTime":null,"scoreUpdateDate":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"zhimaCredit":null,"zhimaNo":null,"startDate":null,"endDate":null,"phone":null,"realName":null,"identityNumber":null,"clientType":null,"levelName":null,"isInBlack":null,"isHaveBank":null,"isPayFee":null,"zhimaUrl":"https://openapi.alipay.com/gateway.do?alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22biz_no%22%3A%22ZM201710123000000393900157916570%22%7D&charset=utf-8&format=json&method=zhima.customer.certification.certify&return_url=xmbk%3A%2F%2F&sign=gBKNeI0U8V0H80za6mL4rc6s1eiQnM%2BadfbmHkzsX17oJkl0n3EM27JIn7zuV6u6wqfCZoAsNxLyDTwljmY%2BRhvBW0gxZfy6KUmPwR1lctDXjPDdY3qriBvvD3HaBfhOiq5%2Ffldzwa9CtxGGEzOUZkKmHE7SyYU%2Bj%2BkglWgGbZqr09qC%2FMqpq%2B%2BuCvo3IGtg1zraa9YvcWT8Pdk7G4o4SHqj%2FYPPO%2BwcZDz4PBbZtpL6gZPTVE0MN6d9nl5QPT4DhXjGx9IA%2BDkzy%2F6HFWeYbovseTIOAc6qRhsLjesDwHTpb9HXyBo1SIF8g8QouLt66uJO2EJOTRhc2ogQhvXVxA%3D%3D&sign_type=RSA2&timestamp=2017-10-12+10%3A07%3A42&version=1.0&sign=gBKNeI0U8V0H80za6mL4rc6s1eiQnM%2BadfbmHkzsX17oJkl0n3EM27JIn7zuV6u6wqfCZoAsNxLyDTwljmY%2BRhvBW0gxZfy6KUmPwR1lctDXjPDdY3qriBvvD3HaBfhOiq5%2Ffldzwa9CtxGGEzOUZkKmHE7SyYU%2Bj%2BkglWgGbZqr09qC%2FMqpq%2B%2BuCvo3IGtg1zraa9YvcWT8Pdk7G4o4SHqj%2FYPPO%2BwcZDz4PBbZtpL6gZPTVE0MN6d9nl5QPT4DhXjGx9IA%2BDkzy%2F6HFWeYbovseTIOAc6qRhsLjesDwHTpb9HXyBo1SIF8g8QouLt66uJO2EJOTRhc2ogQhvXVxA%3D%3D","isSetPwd":null}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * creditId : null
         * memberId : null
         * levelId : null
         * identiType : null
         * creditScore : null
         * creditLimit : null
         * limitUsed : null
         * limitUnused : null
         * status : null
         * frozenWay : null
         * remark : null
         * zhimaStatus : null
         * zhimaTime : null
         * scoreUpdateDate : null
         * createDate : null
         * createBy : null
         * updateDate : null
         * updateBy : null
         * zhimaCredit : null
         * zhimaNo : null
         * startDate : null
         * endDate : null
         * phone : null
         * realName : null
         * identityNumber : null
         * clientType : null
         * levelName : null
         * isInBlack : null
         * isHaveBank : null
         * isPayFee : null
         * zhimaUrl : https://openapi.alipay.com/gateway.do?alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22biz_no%22%3A%22ZM201710123000000393900157916570%22%7D&charset=utf-8&format=json&method=zhima.customer.certification.certify&return_url=xmbk%3A%2F%2F&sign=gBKNeI0U8V0H80za6mL4rc6s1eiQnM%2BadfbmHkzsX17oJkl0n3EM27JIn7zuV6u6wqfCZoAsNxLyDTwljmY%2BRhvBW0gxZfy6KUmPwR1lctDXjPDdY3qriBvvD3HaBfhOiq5%2Ffldzwa9CtxGGEzOUZkKmHE7SyYU%2Bj%2BkglWgGbZqr09qC%2FMqpq%2B%2BuCvo3IGtg1zraa9YvcWT8Pdk7G4o4SHqj%2FYPPO%2BwcZDz4PBbZtpL6gZPTVE0MN6d9nl5QPT4DhXjGx9IA%2BDkzy%2F6HFWeYbovseTIOAc6qRhsLjesDwHTpb9HXyBo1SIF8g8QouLt66uJO2EJOTRhc2ogQhvXVxA%3D%3D&sign_type=RSA2&timestamp=2017-10-12+10%3A07%3A42&version=1.0&sign=gBKNeI0U8V0H80za6mL4rc6s1eiQnM%2BadfbmHkzsX17oJkl0n3EM27JIn7zuV6u6wqfCZoAsNxLyDTwljmY%2BRhvBW0gxZfy6KUmPwR1lctDXjPDdY3qriBvvD3HaBfhOiq5%2Ffldzwa9CtxGGEzOUZkKmHE7SyYU%2Bj%2BkglWgGbZqr09qC%2FMqpq%2B%2BuCvo3IGtg1zraa9YvcWT8Pdk7G4o4SHqj%2FYPPO%2BwcZDz4PBbZtpL6gZPTVE0MN6d9nl5QPT4DhXjGx9IA%2BDkzy%2F6HFWeYbovseTIOAc6qRhsLjesDwHTpb9HXyBo1SIF8g8QouLt66uJO2EJOTRhc2ogQhvXVxA%3D%3D
         * isSetPwd : null
         */

        private Object creditId;
        private Object memberId;
        private Object levelId;
        private Object identiType;
        private Object creditScore;
        private Object creditLimit;
        private Object limitUsed;
        private Object limitUnused;
        private Object status;
        private Object frozenWay;
        private Object remark;
        private Object zhimaStatus;
        private Object zhimaTime;
        private Object scoreUpdateDate;
        private Object createDate;
        private Object createBy;
        private Object updateDate;
        private Object updateBy;
        private Object zhimaCredit;
        private Object zhimaNo;
        private Object startDate;
        private Object endDate;
        private Object phone;
        private Object realName;
        private Object identityNumber;
        private Object clientType;
        private Object levelName;
        private Object isInBlack;
        private Object isHaveBank;
        private Object isPayFee;
        private String zhimaUrl;
        private Object isSetPwd;

        public Object getCreditId() {
            return creditId;
        }

        public void setCreditId(Object creditId) {
            this.creditId = creditId;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public Object getLevelId() {
            return levelId;
        }

        public void setLevelId(Object levelId) {
            this.levelId = levelId;
        }

        public Object getIdentiType() {
            return identiType;
        }

        public void setIdentiType(Object identiType) {
            this.identiType = identiType;
        }

        public Object getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(Object creditScore) {
            this.creditScore = creditScore;
        }

        public Object getCreditLimit() {
            return creditLimit;
        }

        public void setCreditLimit(Object creditLimit) {
            this.creditLimit = creditLimit;
        }

        public Object getLimitUsed() {
            return limitUsed;
        }

        public void setLimitUsed(Object limitUsed) {
            this.limitUsed = limitUsed;
        }

        public Object getLimitUnused() {
            return limitUnused;
        }

        public void setLimitUnused(Object limitUnused) {
            this.limitUnused = limitUnused;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getFrozenWay() {
            return frozenWay;
        }

        public void setFrozenWay(Object frozenWay) {
            this.frozenWay = frozenWay;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getZhimaStatus() {
            return zhimaStatus;
        }

        public void setZhimaStatus(Object zhimaStatus) {
            this.zhimaStatus = zhimaStatus;
        }

        public Object getZhimaTime() {
            return zhimaTime;
        }

        public void setZhimaTime(Object zhimaTime) {
            this.zhimaTime = zhimaTime;
        }

        public Object getScoreUpdateDate() {
            return scoreUpdateDate;
        }

        public void setScoreUpdateDate(Object scoreUpdateDate) {
            this.scoreUpdateDate = scoreUpdateDate;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getZhimaCredit() {
            return zhimaCredit;
        }

        public void setZhimaCredit(Object zhimaCredit) {
            this.zhimaCredit = zhimaCredit;
        }

        public Object getZhimaNo() {
            return zhimaNo;
        }

        public void setZhimaNo(Object zhimaNo) {
            this.zhimaNo = zhimaNo;
        }

        public Object getStartDate() {
            return startDate;
        }

        public void setStartDate(Object startDate) {
            this.startDate = startDate;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public Object getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(Object identityNumber) {
            this.identityNumber = identityNumber;
        }

        public Object getClientType() {
            return clientType;
        }

        public void setClientType(Object clientType) {
            this.clientType = clientType;
        }

        public Object getLevelName() {
            return levelName;
        }

        public void setLevelName(Object levelName) {
            this.levelName = levelName;
        }

        public Object getIsInBlack() {
            return isInBlack;
        }

        public void setIsInBlack(Object isInBlack) {
            this.isInBlack = isInBlack;
        }

        public Object getIsHaveBank() {
            return isHaveBank;
        }

        public void setIsHaveBank(Object isHaveBank) {
            this.isHaveBank = isHaveBank;
        }

        public Object getIsPayFee() {
            return isPayFee;
        }

        public void setIsPayFee(Object isPayFee) {
            this.isPayFee = isPayFee;
        }

        public String getZhimaUrl() {
            return zhimaUrl;
        }

        public void setZhimaUrl(String zhimaUrl) {
            this.zhimaUrl = zhimaUrl;
        }

        public Object getIsSetPwd() {
            return isSetPwd;
        }

        public void setIsSetPwd(Object isSetPwd) {
            this.isSetPwd = isSetPwd;
        }
    }
}
