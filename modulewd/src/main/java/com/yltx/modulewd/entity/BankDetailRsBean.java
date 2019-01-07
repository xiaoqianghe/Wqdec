package com.yltx.modulewd.entity;

/**
 * Author：Wq
 * Date：2017/10/18 14:35
 * Description：//todo
 */

public class BankDetailRsBean {
    /**
     * code : success
     * message : 查询成功
     * bankName : null
     * info : {"infoId":"50","memberId":"3","cardType":"1","realName":"*科昌","idcardNo":"45**************56","bankCardNo":"***************4011","cvn2Code":"980859","reservedMobile":"135****8755","cardValidate":"0622","createDate":"2017-10-18 11:03:13","createBy":"陈科昌","updateBy":"陈科昌","updateDate":"2017-10-18 11:03:13","bankName":"中国建设银行"}
     * infoList : null
     * mbMember : null
     */

    private String code;
    private String message;
    private Object bankName;
    private InfoBean info;
    private Object infoList;
    private Object mbMember;

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

    public Object getMbMember() {
        return mbMember;
    }

    public void setMbMember(Object mbMember) {
        this.mbMember = mbMember;
    }

    public static class InfoBean {
        /**
         * infoId : 50
         * memberId : 3
         * cardType : 1
         * realName : *科昌
         * idcardNo : 45**************56
         * bankCardNo : ***************4011
         * cvn2Code : 980859
         * reservedMobile : 135****8755
         * cardValidate : 0622
         * createDate : 2017-10-18 11:03:13
         * createBy : 陈科昌
         * updateBy : 陈科昌
         * updateDate : 2017-10-18 11:03:13
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
