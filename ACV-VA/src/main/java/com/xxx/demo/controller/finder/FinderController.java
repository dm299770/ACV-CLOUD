package com.xxx.demo.controller.finder;

import com.alibaba.fastjson.JSONObject;
import com.xxx.demo.jsonBean.remote.RequestParameters;
import com.xxx.demo.services.finder.FinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description:远程寻车
 * @author:@guo.zj
 */
@RestController
@RequestMapping({"/find"})
public class FinderController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FinderService finderService;
    @ResponseBody
    @RequestMapping(value = "finder")
    public Object finder(@RequestBody RequestParameters data) {
        JSONObject result=null;
        try {
            result = finderService.finder(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

}


