package com.xxx.demo.services.remote;


import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.jsonBean.finder.request.RequestParameterForCtrl;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.AirConditionRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.EVWindowRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.EVvehicleCtrlRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.VinRequestParameter;


/**
 * @description:登录相关的方法
 * @author:@leo.
 */
public interface RemoteService {
    /**
     * 远程车控
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject remote(RequestParameterForCtrl request);

    /**
     * 车锁
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject remotedoor(EVvehicleCtrlRequestParameter request);


    /**
     * 天窗
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject sunroof(EVvehicleCtrlRequestParameter request);


    /**
     * 车窗
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject window(EVWindowRequestParameter request);

    /**
     * 空调开关
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject airconditionertrun(AirConditionRequestParameter request);


    /**
     * 闪灯鸣笛
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject horn(VinRequestParameter request);

    /**
     * 后备箱锁
     *
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject runk(EVvehicleCtrlRequestParameter request);

    /**
     * 启车
     * @param vin车架号 操作类型
     * @return 成功失败
     */
    JSONObject carpower(EVvehicleCtrlRequestParameter request);

}

