package com.xxx.demo.services.remote.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.jsonBean.finder.request.RequestParameterForCtrl;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.AirConditionRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.EVWindowRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.EVvehicleCtrlRequestParameter;
import com.xxx.demo.jsonBean.remote.remoteRequestParmmeter.VinRequestParameter;
import com.xxx.demo.jsonBean.remote.remotemodel.Vehiclemarks;
import com.xxx.demo.models.remote.AirCondition;
import com.xxx.demo.services.remote.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:车辆操控接口
 * @author:@guo.zj
 */
@Service
public class RemoteServiceImpl implements RemoteService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public JSONObject remote(RequestParameterForCtrl data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            //Finder finder = JSON.parseObject(request, FinderRequestParameter.class).getData();
            String vin = data.getData().getVin();
            String serviceType = data.getData().getType();
            String controltype=data.getData().getControltype();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "closelock".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "闭锁成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "openlock".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "开锁成功");
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "air_conditioner".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "空调操作成功");
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "flash_horns".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "车辆正在鸣笛闪烁");
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "vehicle_diagnose".equals(controltype)) {
                Vehiclemarks vehiclemarks=new Vehiclemarks();
                Map<String, String>[] maps=new Map[10];
                Map<String, String> map0=new HashMap<>();
                Map<String, String> map1=new HashMap<>();
                Map<String, String> map2=new HashMap<>();
                Map<String, String> map3=new HashMap<>();
                Map<String, String> map4=new HashMap<>();
                Map<String, String> map5=new HashMap<>();
                Map<String, String> map6=new HashMap<>();
                Map<String, String> map7=new HashMap<>();
                Map<String, String> map8=new HashMap<>();
                Map<String, String> map9=new HashMap<>();
                maps[0]=map0;
                maps[1]=map1;
                maps[2]=map2;
                maps[3]=map3;
                maps[4]=map4;
                maps[5]=map5;
                maps[6]=map6;
                maps[7]=map7;
                maps[8]=map8;
                maps[9]=map9;
                maps[0].put("good","传动系统");
                maps[1].put("good","悬挂系统");
                maps[2].put("good","转向系统");
                maps[3].put("good","制动系统");
                maps[4].put("good","动力系统");
                maps[5].put("good","车身系统");
                maps[6].put("good","底盘系统");
                maps[7].put("good","操作系统");
                maps[8].put("bad","自动驾驶系统");
                maps[9].put("bad","传动系统");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());
                String diagnosedate=date.substring(5, 16);
                vehiclemarks.setDiagonsedate(diagnosedate);
                vehiclemarks.setSystem(maps);
                vehiclemarks.setScore("90");
                jsonObject.put("status", 200);
                jsonObject.put("msg", "车辆诊断成功");
                jsonObject.put("data",vehiclemarks);
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "user_behavior_analysis".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "用户行为分析成功");
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "start".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "启动汽车成功");
                logger.info("返回的json:{}", jsonObject);
            }else if ("APP".equals(serviceType) && "NISSAN0000000000".equals(vin)&& "fireoff".equals(controltype)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "熄火成功");
                logger.info("返回的json:{}", jsonObject);
            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject remotedoor(EVvehicleCtrlRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)&& action==0) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("NISSAN0000000000".equals(vin)&& action==1) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);

            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject sunroof(EVvehicleCtrlRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)&& action==0) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("NISSAN0000000000".equals(vin)&& action==1) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);

            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject window(EVWindowRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            String wsite = data.getData().getWsite();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)) {
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data", "");
            }else if (!wsite.equals("FL")) {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }else if ( "NISSAN0000000000".equals(vin)&& action==0) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("NISSAN0000000000".equals(vin)&& action==1) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);

            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject airconditionertrun(AirConditionRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            AirCondition acparam=new AirCondition();
            acparam= data.getData().getAcparam();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }


    @Override
    public JSONObject horn(VinRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject runk(EVvehicleCtrlRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)&& action==0) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("NISSAN0000000000".equals(vin)&& action==1) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);

            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject carpower(EVvehicleCtrlRequestParameter data) {
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("请求体:" + data);
            String vin = data.getData().getVin();
            int action = data.getData().getAction();
            Thread.sleep(2000);
            JSONArray jsonArray = new JSONArray();
            if(vin==null||"".equals(vin)){
                jsonObject.put("status", 201);
                jsonObject.put("msg", "传入参数有误");
                jsonObject.put("data","");
            }else if ( "NISSAN0000000000".equals(vin)&& action==0) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);
            } else if("NISSAN0000000000".equals(vin)&& action==1) {
                jsonObject.put("status", 200);
                jsonObject.put("msg", "指令下发成功");
                logger.info("返回的json:{}", jsonObject);

            }else {
                jsonObject.put("status", 400);
                jsonObject.put("msg", "failed");
                logger.info("返回的json:{}",jsonObject);
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("系统内部错误!");
            jsonObject.put("status",500);
            jsonObject.put("msg", "服务器处理失败，系统内部错误");
        }
        return jsonObject;
    }
}