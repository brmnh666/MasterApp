package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class WithDrawMoney implements Serializable {

    /**
     * bzj : 0.00
     * ktx : 6595.00
     * txz : 0
     * dqr : 23126.00
     */

    private String bzj;
    private String ktx;
    private String txz;
    private String dqr;

    public String getBzj() {
        return bzj;
    }

    public void setBzj(String bzj) {
        this.bzj = bzj;
    }

    public String getKtx() {
        return ktx;
    }

    public void setKtx(String ktx) {
        this.ktx = ktx;
    }

    public String getTxz() {
        return txz;
    }

    public void setTxz(String txz) {
        this.txz = txz;
    }

    public String getDqr() {
        return dqr;
    }

    public void setDqr(String dqr) {
        this.dqr = dqr;
    }
}
