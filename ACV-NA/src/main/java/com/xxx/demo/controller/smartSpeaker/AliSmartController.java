package com.xxx.demo.controller.smartSpeaker;


import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.frame.constants.DataFormat;
import com.xxx.demo.models.jsonBean.smartSpeaker.ali.AliTaskRequest;
import com.xxx.demo.services.smartSpeaker.AliSmartEvHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 天猫精灵控车demo
 * <p>
 * Created by liyang on 2018/01/25.
 */
@Controller
@RequestMapping({"/smartSpeaker"})
public class AliSmartController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private AliSmartEvHandle aliSmartEvHandle;

    @ResponseBody
    @RequestMapping(value = "/aliSmart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object aliSmart(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        logger.info("jsonObject:{}", jsonObject.toString());

        AliTaskRequest aliTaskRequest = DataFormat.parseToQuery(jsonObject);

        JSONObject json = aliSmartEvHandle.execute(aliTaskRequest);
        return json;
    }

}
