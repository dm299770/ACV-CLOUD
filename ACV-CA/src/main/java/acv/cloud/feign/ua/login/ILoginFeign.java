package acv.cloud.feign.ua.login;

import acv.cloud.model.ua.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@FeignClient(name = "ACV-UA")
public interface ILoginFeign {
    @ResponseBody
    @RequestMapping(value = "login/login/{phoneNum}/{password}/{deviceNo}")
    public Object login(@PathVariable("phoneNum") String phoneNum, @PathVariable("password") String password, @PathVariable("deviceNo") String deviceNo);

    @ResponseBody
    @RequestMapping(value = "user/getUserInfo")
    public Object getUserInfo(UserInfo user);

}
