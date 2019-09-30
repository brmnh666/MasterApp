package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class CustomerBean implements Serializable {


    /**
     * Id : 1
     * ShopId : 1
     * Tool : 1
     * Type : 1
     * Name : 123
     * AccountCode : 232514435
     * TerminalType : 1
     * ServerStatus : 1
     */

    private int Id;
    private int ShopId;
    private int Tool;
    private int Type;
    private String Name;
    private String AccountCode;
    private int TerminalType;
    private int ServerStatus;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
        this.ShopId = ShopId;
    }

    public int getTool() {
        return Tool;
    }

    public void setTool(int Tool) {
        this.Tool = Tool;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAccountCode() {
        return AccountCode;
    }

    public void setAccountCode(String AccountCode) {
        this.AccountCode = AccountCode;
    }

    public int getTerminalType() {
        return TerminalType;
    }

    public void setTerminalType(int TerminalType) {
        this.TerminalType = TerminalType;
    }

    public int getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(int ServerStatus) {
        this.ServerStatus = ServerStatus;
    }
}
