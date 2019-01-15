package com.xxx.demo.jsonBean.remote.remotemodel;

import com.xxx.demo.models.remote.AirCondition;

/**
 * 空调
 * @author guo.zj
 */
public class AirConditionCtrl {

    //vin
    private String vin;
    //操作
    private int action;
    //空调
    private AirCondition acparam;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public AirCondition getAcparam() {
        return acparam;
    }

    public void setAcparam(AirCondition acparam) {
        this.acparam = acparam;
    }
}
