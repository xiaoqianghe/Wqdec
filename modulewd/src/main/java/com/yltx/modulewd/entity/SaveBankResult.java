package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/13.
 */

public class SaveBankResult {

    /**
     * code : success
     * message : 银行卡信息添加成功
     * bankName : null
     * info : {"infoId":"50","memberId":"3","cardType":"1","realName":"陈科昌","idcardNo":"","bankCardNo":"6217007200038404011","cvn2Code":"980859","reservedMobile":"13510378755","cardValidate":"0622","createDate":"2017-10-13 16:57:19","createBy":"陈科昌","updateBy":"陈科昌","updateDate":"2017-10-13 16:57:19","bankName":"中国建设银行"}
     * infoList : null
     */

    private String code;
    private String message;
    private String bankName;
    private InfoBean info;
    private String infoList;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getInfoList() {
        return infoList;
    }

    public void setInfoList(String infoList) {
        this.infoList = infoList;
    }

    public static class InfoBean {
        /**
         * infoId : 50
         * memberId : 3
         * cardType : 1
         * realName : 陈科昌
         * idcardNo :
         * bankCardNo : 6217007200038404011
         * cvn2Code : 980859
         * reservedMobile : 13510378755
         * cardValidate : 0622
         * createDate : 2017-10-13 16:57:19
         * createBy : 陈科昌
         * updateBy : 陈科昌
         * updateDate : 2017-10-13 16:57:19
         * bankName : 中国建设银行
         */

        private String infoId;
        private String memberId;
        private String cardType;
        private String realName;
        private String idcardNo;
        private String bankCardNo;
        private String cvn2Code;
        private String reservedMobile;
        private String cardValidate;
        private String createDate;
        private String createBy;
        private String updateBy;
        private String updateDate;
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

        public String getCvn2Code() {
            return cvn2Code;
        }

        public void setCvn2Code(String cvn2Code) {
            this.cvn2Code = cvn2Code;
        }

        public String getReservedMobile() {
            return reservedMobile;
        }

        public void setReservedMobile(String reservedMobile) {
            this.reservedMobile = reservedMobile;
        }

        public String getCardValidate() {
            return cardValidate;
        }

        public void setCardValidate(String cardValidate) {
            this.cardValidate = cardValidate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
