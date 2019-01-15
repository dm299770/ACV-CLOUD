package com.xxx.demo.jsonBean.remote.remotemodel;

/**
 * 车锁/天窗
 * @author guo.zj
 */
public class EVVehicleCtrl {
    private int action;
    private String vin;


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
}
