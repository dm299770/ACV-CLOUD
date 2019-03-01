package com.acv.cloud.fegin.va;

import com.acv.cloud.models.jsonBean.vehicleState.VehicleStateRequestParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "ACV-VA")
public interface VehicleStateFeign {

    @ResponseBody
    @RequestMapping(value = "EVVehicleState")
    Object EVvehiclestate(@RequestBody VehicleStateRequestParameter data);

}