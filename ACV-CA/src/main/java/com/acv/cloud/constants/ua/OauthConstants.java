package com.acv.cloud.constants.ua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="jwt")
public class OauthConstants {
    //错误码
    public static final String INVALID_RESPONSE_TYPE = "901";
    public static final String INVALID_SCOPE = "902";
    public static final String INVALID_CLIENT_ID = "904";
    public static final String INVALID_CLIENT_ID_SECRET = "905";
    public static final String INVALID_AUTH_CODE = "906";
    public static final String INVALID_REFRESH_TOKEN = "907";
    public static final String INVALID_ACCESS_TOKEN = "908";
    public static final String INVALID_PARAMETER = "400";
    public static final String SERVER_EXCEPTION = "500";
    public static final String REQUEST_OVERTIME = "201";
    //错误码描述
    public static final String INVALID_RESPONSE_TYPE_DESCRIBE = "授权服务器不支持客户端所指定的response_type";
    public static final String INVALID_SCOPE_DESCRIBE = "scope无效";
    public static final String INVALID_CLIENT_ID_DESCRIBE = "client_id不存在";
    public static final String INVALID_CLIENT_ID_SECRET_DESCRIBE = "client_id和client_secret不匹配";
    public static final String INVALID_AUTH_CODE_DESCRIBE = "授权码无效或已过期";
    public static final String INVALID_REFRESH_TOKEN_DESCRIBE = "refresh_token无效或已过期";
    public static final String INVALID_ACCESS_TOKEN_DESCRIBE = "accessToken无效或已过期";
    public static final String INVALID_PARAMETER_DESCRIBE = "请求参数不符合协议规范";
    public static final String SERVER_EXCEPTION_DESCRIBE = "服务器异常";
    public static final String REQUEST_OVERTIME_DESCRIBE = "请求超时";
    //路径分隔符
    public static String FILE_SEPARATOR = System.getProperty("file.separator");


    //CHRTSP01秘钥
//
//    public static String secret_baidu;
//    //CHRTSP02秘钥
//
//    public static String secret_hu;
//    //CHRTSP03秘钥
//
//    public static String secret_auto;
//    //CHRTSP04秘钥
//
//    public static String secret_jd;
//    //授权码过期时间
//
//    public static int code_expirein;
//    //refresh_token过期时间
//
//    public static Long refresh_token_expirein;
//    //access_token过期时间
//
//    public static Long access_token_expirein;
//    //权限_连接
//
//    //public static String AUTHORITY_LIST;
//
//    public static String getSecret_baidu() {
//        return secret_baidu;
//    }
//
//    public static void setSecret_baidu(String secret_baidu) {
//        OauthConstants.secret_baidu = secret_baidu;
//    }
//
//    public static String getSecret_hu() {
//        return secret_hu;
//    }
//
//    public static void setSecret_hu(String secret_hu) {
//        OauthConstants.secret_hu = secret_hu;
//    }
//
//    public static String getSecret_auto() {
//        return secret_auto;
//    }
//
//    public static void setSecret_auto(String secret_auto) {
//        OauthConstants.secret_auto = secret_auto;
//    }
//
//    public static String getSecret_jd() {
//        return secret_jd;
//    }
//
//    public static void setSecret_jd(String secret_jd) {
//        OauthConstants.secret_jd = secret_jd;
//    }
//
//    public static int getCode_expirein() {
//        return code_expirein;
//    }
//
//    public static void setCode_expirein(int code_expirein) {
//        OauthConstants.code_expirein = code_expirein;
//    }
//
//    public static Long getRefresh_token_expirein() {
//        return refresh_token_expirein;
//    }
//
//    public static void setRefresh_token_expirein(Long refresh_token_expirein) {
//        OauthConstants.refresh_token_expirein = refresh_token_expirein;
//    }
//
//    public static Long getAccess_token_expirein() {
//        return access_token_expirein;
//    }
//
//    public static void setAccess_token_expirein(Long access_token_expirein) {
//        OauthConstants.access_token_expirein = access_token_expirein;
//    }
}
