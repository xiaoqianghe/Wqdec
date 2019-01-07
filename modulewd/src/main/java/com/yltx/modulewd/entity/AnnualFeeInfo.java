package com.yltx.modulewd.entity;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public class AnnualFeeInfo {


    /**
     * code : success
     * message : 调用成功
     * data : {"memberId":"3","accountName":null,"realName":"陈科昌","status":null,"isAccountEnabled":null,"isAccountLocked":null,"isAccountFreeze":null,"dateLocked":null,"clientType":null,"phone":"13510378755","identifyUrl":null,"identifyUrl2":null,"holdIdentifyUrl":null,"bankCardUrl":null,"bankCardUrl2":null,"faceUrl":null,"isDeleted":null,"nickName":null,"paymentPassword":null,"identityNumber":null,"sex":null,"photourl":null,"oneLevelAgentId":null,"oneLevelAgentName":null,"agentId":null,"agentName":null,"annualFeeStatus":"1","annualFeePayTime":"2017-10-13 17:36:43","annualFeeValidate":"2018-10-13 00:00:00","merchantNo":null,"merchantName":null,"merchantType":null,"registerTime":null,"businessAddr":null,"accountType":null,"bankId":"103","bankName":"民生银行","accountName2":"哈哈","correspondentNo":null,"openArea":null,"createdBy":null,"dateCreated":null,"updatedBy":null,"dateUpdated":null,"bankCardNo":"1234567893214888","startDate":null,"endDate":null,"memberFee":"200.0","feePeriod":"365","loanServiceCharge":"0.01","borrowTerms":[{"term":"1个月","repaymentTime":"2017-11-17"},{"term":"2个月","repaymentTime":"2017-11-17"},{"term":"3个月","repaymentTime":"2017-11-17"},{"term":"4个月","repaymentTime":"2017-11-17"},{"term":"5个月","repaymentTime":"2017-11-17"}],"limitUnused":"2000.0","repaymentDay":""}
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
         * memberId : 3
         * accountName : null
         * realName : 陈科昌
         * status : null
         * isAccountEnabled : null
         * isAccountLocked : null
         * isAccountFreeze : null
         * dateLocked : null
         * clientType : null
         * phone : 13510378755
         * identifyUrl : null
         * identifyUrl2 : null
         * holdIdentifyUrl : null
         * bankCardUrl : null
         * bankCardUrl2 : null
         * faceUrl : null
         * isDeleted : null
         * nickName : null
         * paymentPassword : null
         * identityNumber : null
         * sex : null
         * photourl : null
         * oneLevelAgentId : null
         * oneLevelAgentName : null
         * agentId : null
         * agentName : null
         * annualFeeStatus : 1
         * annualFeePayTime : 2017-10-13 17:36:43
         * annualFeeValidate : 2018-10-13 00:00:00
         * merchantNo : null
         * merchantName : null
         * merchantType : null
         * registerTime : null
         * businessAddr : null
         * accountType : null
         * bankId : 103
         * bankName : 民生银行
         * accountName2 : 哈哈
         * correspondentNo : null
         * openArea : null
         * createdBy : null
         * dateCreated : null
         * updatedBy : null
         * dateUpdated : null
         * bankCardNo : 1234567893214888
         * startDate : null
         * endDate : null
         * memberFee : 200.0
         * feePeriod : 365
         * loanServiceCharge : 0.01
         * borrowTerms : [{"term":"1个月","repaymentTime":"2017-11-17"},{"term":"2个月","repaymentTime":"2017-11-17"},{"term":"3个月","repaymentTime":"2017-11-17"},{"term":"4个月","repaymentTime":"2017-11-17"},{"term":"5个月","repaymentTime":"2017-11-17"}]
         * limitUnused : 2000.0
         * repaymentDay : 
         */

        private String memberId;
        private String accountName;
        private String realName;
        private String status;
        private String isAccountEnabled;
        private String isAccountLocked;
        private String isAccountFreeze;
        private String dateLocked;
        private String clientType;
        private String phone;
        private String identifyUrl;
        private String identifyUrl2;
        private String holdIdentifyUrl;
        private String bankCardUrl;
        private String bankCardUrl2;
        private String faceUrl;
        private String isDeleted;
        private String nickName;
        private String paymentPassword;
        private String identityNumber;
        private String sex;
        private String photourl;
        private String oneLevelAgentId;
        private String oneLevelAgentName;
        private String agentId;
        private String agentName;
        private String annualFeeStatus;
        private String annualFeePayTime;
        private String annualFeeValidate;
        private String merchantNo;
        private String merchantName;
        private String merchantType;
        private String registerTime;
        private String businessAddr;
        private String accountType;
        private String bankId;
        private String bankName;
        private String accountName2;
        private String correspondentNo;
        private String openArea;
        private String createdBy;
        private String dateCreated;
        private String updatedBy;
        private String dateUpdated;
        private String bankCardNo;
        private String startDate;
        private String endDate;
        private String memberFee;
        private String feePeriod;
        private String loanServiceCharge;
        private String limitUnused;
        private String repaymentDay;
        private List<BorrowTermsBean> borrowTerms;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsAccountEnabled() {
            return isAccountEnabled;
        }

        public void setIsAccountEnabled(String isAccountEnabled) {
            this.isAccountEnabled = isAccountEnabled;
        }

        public String getIsAccountLocked() {
            return isAccountLocked;
        }

        public void setIsAccountLocked(String isAccountLocked) {
            this.isAccountLocked = isAccountLocked;
        }

        public String getIsAccountFreeze() {
            return isAccountFreeze;
        }

        public void setIsAccountFreeze(String isAccountFreeze) {
            this.isAccountFreeze = isAccountFreeze;
        }

        public String getDateLocked() {
            return dateLocked;
        }

        public void setDateLocked(String dateLocked) {
            this.dateLocked = dateLocked;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdentifyUrl() {
            return identifyUrl;
        }

        public void setIdentifyUrl(String identifyUrl) {
            this.identifyUrl = identifyUrl;
        }

        public String getIdentifyUrl2() {
            return identifyUrl2;
        }

        public void setIdentifyUrl2(String identifyUrl2) {
            this.identifyUrl2 = identifyUrl2;
        }

        public String getHoldIdentifyUrl() {
            return holdIdentifyUrl;
        }

        public void setHoldIdentifyUrl(String holdIdentifyUrl) {
            this.holdIdentifyUrl = holdIdentifyUrl;
        }

        public String getBankCardUrl() {
            return bankCardUrl;
        }

        public void setBankCardUrl(String bankCardUrl) {
            this.bankCardUrl = bankCardUrl;
        }

        public String getBankCardUrl2() {
            return bankCardUrl2;
        }

        public void setBankCardUrl2(String bankCardUrl2) {
            this.bankCardUrl2 = bankCardUrl2;
        }

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPaymentPassword() {
            return paymentPassword;
        }

        public void setPaymentPassword(String paymentPassword) {
            this.paymentPassword = paymentPassword;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getOneLevelAgentId() {
            return oneLevelAgentId;
        }

        public void setOneLevelAgentId(String oneLevelAgentId) {
            this.oneLevelAgentId = oneLevelAgentId;
        }

        public String getOneLevelAgentName() {
            return oneLevelAgentName;
        }

        public void setOneLevelAgentName(String oneLevelAgentName) {
            this.oneLevelAgentName = oneLevelAgentName;
        }

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getAgentName() {
            return agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public String getAnnualFeeStatus() {
            return annualFeeStatus;
        }

        public void setAnnualFeeStatus(String annualFeeStatus) {
            this.annualFeeStatus = annualFeeStatus;
        }

        public String getAnnualFeePayTime() {
            return annualFeePayTime;
        }

        public void setAnnualFeePayTime(String annualFeePayTime) {
            this.annualFeePayTime = annualFeePayTime;
        }

        public String getAnnualFeeValidate() {
            return annualFeeValidate;
        }

        public void setAnnualFeeValidate(String annualFeeValidate) {
            this.annualFeeValidate = annualFeeValidate;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantType() {
            return merchantType;
        }

        public void setMerchantType(String merchantType) {
            this.merchantType = merchantType;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getBusinessAddr() {
            return businessAddr;
        }

        public void setBusinessAddr(String businessAddr) {
            this.businessAddr = businessAddr;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getAccountName2() {
            return accountName2;
        }

        public void setAccountName2(String accountName2) {
            this.accountName2 = accountName2;
        }

        public String getCorrespondentNo() {
            return correspondentNo;
        }

        public void setCorrespondentNo(String correspondentNo) {
            this.correspondentNo = correspondentNo;
        }

        public String getOpenArea() {
            return openArea;
        }

        public void setOpenArea(String openArea) {
            this.openArea = openArea;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getDateUpdated() {
            return dateUpdated;
        }

        public void setDateUpdated(String dateUpdated) {
            this.dateUpdated = dateUpdated;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getMemberFee() {
            return memberFee;
        }

        public void setMemberFee(String memberFee) {
            this.memberFee = memberFee;
        }

        public String getFeePeriod() {
            return feePeriod;
        }

        public void setFeePeriod(String feePeriod) {
            this.feePeriod = feePeriod;
        }

        public String getLoanServiceCharge() {
            return loanServiceCharge;
        }

        public void setLoanServiceCharge(String loanServiceCharge) {
            this.loanServiceCharge = loanServiceCharge;
        }

        public String getLimitUnused() {
            return limitUnused;
        }

        public void setLimitUnused(String limitUnused) {
            this.limitUnused = limitUnused;
        }

        public String getRepaymentDay() {
            return repaymentDay;
        }

        public void setRepaymentDay(String repaymentDay) {
            this.repaymentDay = repaymentDay;
        }

        public List<BorrowTermsBean> getBorrowTerms() {
            return borrowTerms;
        }

        public void setBorrowTerms(List<BorrowTermsBean> borrowTerms) {
            this.borrowTerms = borrowTerms;
        }

        public static class BorrowTermsBean {
            /**
             * term : 1个月
             * repaymentTime : 2017-11-17
             */

            private String term;
            private String repaymentTime;

            private String termNum;

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }

            public String getRepaymentTime() {
                return repaymentTime;
            }

            public void setRepaymentTime(String repaymentTime) {
                this.repaymentTime = repaymentTime;
            }

            public String getTermNum() {
                return termNum;
            }

            public void setTermNum(String termNum) {
                this.termNum = termNum;
            }
        }
    }
}
