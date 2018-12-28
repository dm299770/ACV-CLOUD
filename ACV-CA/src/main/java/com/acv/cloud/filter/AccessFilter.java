package com.acv.cloud.filter;

import com.acv.cloud.constants.ua.CurrentUserConstants;
import com.acv.cloud.feign.ua.ITsUserServiceFeign;
import com.acv.cloud.model.ua.UserInfo;
//import com.acv.cloud.util.JwtTokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Component
public class AccessFilter extends ZuulFilter
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String accessToken = "accessToken";

    //@Autowired
    //private ITsUserServiceFeign iTsUserServiceFeign;


    /**
     * pre请求之前
     * route用于将请求路由转到微服务
     * post路由到微服务以后执行
     * error在其他阶段发生错误的时候执行
     * @return
     */

    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序
     * @return
     */

    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * true返回一个boolean判断该过滤器是否要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /**
     * 过滤器执行具体内容
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //logger.info("--->> route get all params :"+request.getParameterMap().toString());
        logger.info("--->>send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        //Object header = request.getHeader(accessToken);
        //UserInfo userInfo = iTsUserServiceFeign.getUser("3431b8b5-85df-4024-a3d4-a83af1b1aa27");
        //System.out.println(userInfo.getPhoneNum());
        //        if(header==null){
//            logger.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//
//        if(header!=null){
//            if (!JwtTokenUtil.checkAccessToken(accessToken)) {
//                //Claims claims = TokenUtils.parseJWT(accessToken);
//                Map<String, Object> map = JwtTokenUtil.analysisToken(accessToken, 1);
//                String uuid = map.get("id").toString();
//                UserInfo userInfo = null ;
//                if (!StringUtils.isEmpty(uuid)) {
//                    //userInfo = iTsUserServiceFeign.getUser(uuid);
//                }
//                // 当前登录用户@CurrentUser
//                request.setAttribute(CurrentUserConstants.CURRENT_USER, userInfo);
//            }
//        }

        logger.info("access token ok");
        return null;
    }
}
