package com.yltx.modulewd.entity;

/**
 * Author：Wq
 * Date：2017/10/18 14:33
 * Description：//todo
 */

public class DepositBankRsBean {
    /**
     * code : success
     * message : 查询成功
     * bankName : null
     * info : {"infoId":"0","memberId":"3","cardType":"0","realName":"*科昌","idcardNo":"45**************56","bankCardNo":"***************4888","cvn2Code":null,"reservedMobile":"135****8755","cardValidate":null,"createDate":null,"createBy":null,"updateBy":null,"updateDate":null,"bankName":"民生银行"}
     * infoList : null
     * mbMember : {"memberId":"3","accountName":"13510378755","realName":"陈科昌","status":"normal","isAccountEnabled":"1","isAccountLocked":"0","isAccountFreeze":"0","dateLocked":null,"clientType":"normal","phone":"13510378755","identifyUrl":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1516891756,2194615571=27=0.jpg","identifyUrl2":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3269061743,2028437678=27=0.jpg","holdIdentifyUrl":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1602552054,373587514=27=0.jpg","bankCardUrl":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2993321848,3135960404=27=0.jpg","bankCardUrl2":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1035436093,3470577695=27=0.jpg","faceUrl":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1035436093,3470577695=27=0.jpg","isDeleted":"0","nickName":"嘿嘿","paymentPassword":"147258","identityNumber":"","sex":"1","photourl":null,"oneLevelAgentId":"1234","oneLevelAgentName":"1234名称","agentId":"1112","agentName":"1112名称","annualFeeStatus":"1","annualFeePayTime":"2017-10-13 17:36:43","annualFeeValidate":"2018-10-13 00:00:00","merchantNo":"123","merchantName":"123123","merchantType":"123123","registerTime":"2017-09-26 11:11:11","businessAddr":"111111222","accountType":"222","bankId":"103","bankName":"民生银行","accountName2":"哈哈","correspondentNo":"431103199006225716","openArea":"南山支行","createdBy":"admin","dateCreated":"2017-09-26 11:11:11","updatedBy":null,"dateUpdated":null,"bankCardNo":"1234567893214888"}
     */

    private String code;
    private String message;
    private Object bankName;
    private InfoBean info;
    private Object infoList;
    private MbMemberBean mbMember;

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

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public Object getInfoList() {
        return infoList;
    }

    public void setInfoList(Object infoList) {
        this.infoList = infoList;
    }

    public MbMemberBean getMbMember() {
        return mbMember;
    }

    public void setMbMember(MbMemberBean mbMember) {
        this.mbMember = mbMember;
    }

    public static class InfoBean {
        /**
         * infoId : 0
         * memberId : 3
         * cardType : 0
         * realName : *科昌
         * idcardNo : 45**************56
         * bankCardNo : ***************4888
         * cvn2Code : null
         * reservedMobile : 135****8755
         * cardValidate : null
         * createDate : null
         * createBy : null
         * updateBy : null
         * updateDate : null
         * bankName : 民生银行
         */

        private String infoId;
        private String memberId;
        private String cardType;
        private String realName;
        private String idcardNo;
        private String bankCardNo;
        private Object cvn2Code;
        private String reservedMobile;
        private Object cardValidate;
        private Object createDate;
        private Object createBy;
        private Object updateBy;
        private Object updateDate;
        private String bankName;

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdcardNo() {
            return idcardNo;
        }

        public void setIdcardNo(String idcardNo) {
            this.idcardNo = idcardNo;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public Object getCvn2Code() {
            return cvn2Code;
        }

        public void setCvn2Code(Object cvn2Code) {
            this.cvn2Code = cvn2Code;
        }

        public String getReservedMobile() {
            return reservedMobile;
        }

        public void setReservedMobile(String reservedMobile) {
            this.reservedMobile = reservedMobile;
        }

        public Object getCardValidate() {
            return cardValidate;
        }

        public void setCardValidate(Object cardValidate) {
            this.cardValidate = cardValidate;
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

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }

    public static class MbMemberBean {
        /**
         * memberId : 3
         * accountName : 13510378755
         * realName : 陈科昌
         * status : normal
         * isAccountEnabled : 1
         * isAccountLocked : 0
         * isAccountFreeze : 0
         * dateLocked : null
         * clientType : normal
         * phone : 13510378755
         * identifyUrl : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1516891756,2194615571=27=0.jpg
         * identifyUrl2 : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3269061743,2028437678=27=0.jpg
         * holdIdentifyUrl : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1602552054,373587514=27=0.jpg
         * bankCardUrl : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2993321848,3135960404=27=0.jpg
         * bankCardUrl2 : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1035436093,3470577695=27=0.jpg
         * faceUrl : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1035436093,3470577695=27=0.jpg
         * isDeleted : 0
         * nickName : 嘿嘿
         * paymentPassword : 147258
         * identityNumber :
         * sex : 1
         * photourl : null
         * oneLevelAgentId : 1234
         * oneLevelAgentName : 1234名称
         * agentId : 1112
         * agentName : 1112名称
         * annualFeeStatus : 1
         * annualFeePayTime : 2017-10-13 17:36:43
         * annualFeeValidate : 2018-10-13 00:00:00
         * merchantNo : 123
         * merchantName : 123123
         * merchantType : 123123
         * registerTime : 2017-09-26 11:11:11
         * businessAddr : 111111222
         * accountType : 222
         * bankId : 103
         * bankName : 民生银行
         * accountName2 : 哈哈
         * correspondentNo : 431103199006225716
         * openArea : 南山支行
         * createdBy : admin
         * dateCreated : 2017-09-26 11:11:11
         * updatedBy : null
         * dateUpdated : null
         * bankCardNo : 1234567893214888
         */

        private String memberId;
        private String accountName;
        private String realName;
        private String status;
        private String isAccountEnabled;
        private String isAccountLocked;
        private String isAccountFreeze;
        private Object dateLocked;
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
        private Object photourl;
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
        private Object updatedBy;
        private Object dateUpdated;
        private String bankCardNo;

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

        public Object getDateLocked() {
            return dateLocked;
        }

        public void setDateLocked(Object dateLocked) {
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

        public Object getPhotourl() {
            return photourl;
        }

        public void setPhotourl(Object photourl) {
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

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Object getDateUpdated() {
            return dateUpdated;
        }

        public void setDateUpdated(Object dateUpdated) {
            this.dateUpdated = dateUpdated;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }
    }
}
