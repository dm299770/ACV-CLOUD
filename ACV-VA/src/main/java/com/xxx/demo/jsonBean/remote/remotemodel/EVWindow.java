package com.xxx.demo.jsonBean.remote.remotemodel;

/**
 * 车辆诊断
 * @author guo.zj
 */
public class EVWindow {
    private int action;
    private String vin;
    private String wsite;


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

    public String getWsite() {
        return wsite;
    }

    public void setWsite(String wsite) {
        this.wsite = wsite;
    }
}