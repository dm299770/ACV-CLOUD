package com.acv.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PreLogFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String accessToken = "accessToken";
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
        String header = request.getHeader(accessToken);


        logger.info("--->> route get all params :"+request.getParameterMap().toString());

        return null;
    }
}
