package com.ying.administrator.masterappdemo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IsCardNo {

    @SerializedName("messages")
    private List<MessagesBean> messages;

    /**
     * bank : ICBC
     * validated : true
     * cardType : DC
     * key : 6212264010013631038
     * messages : []
     * stat : ok
     */
    private String bank;
    private boolean validated;
    private String cardType;
    private String key;
    private String stat;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * errorCodes : CARD_BIN_NOT_MATCH
         * name : cardNo
         */

        private String errorCodes;
        private String name;

        public String getErrorCodes() {
            return errorCodes;
        }

        public void setErrorCodes(String errorCodes) {
            this.errorCodes = errorCodes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
