package com.acv.cloud.models.jsonBean.vehicleState;

/**
 * EV车辆状态请求bean
 *
 */
public class VehicleStateRequestParameter {

    private RequestVehicleState data;


    public RequestVehicleState getData() {
        return data;
    }

    public void setData(RequestVehicleState data) {
        this.data = data;
    }
}
