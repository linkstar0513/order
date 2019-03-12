package com.order.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.order.web.annotation.AuthToken;
import com.order.web.pojo.User;
import com.order.web.service.TokenService;
import com.order.web.util.ApiTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 2;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;

    @Autowired
    private TokenService tokenService;


    //存放鉴权信息的header名称，默认是Authorization
    private String httpHeaderName="access_token";

    //鉴权失败后返回的错误信息，错误码
    private String authErrorMessage = "Auth error";
    private int authErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放用户登录模型key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY="uuid2";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        //根据cookie获取登录用户
        Cookie[] cookies = request.getCookies();
        String token1;
        if(cookies!=null){
            for (Cookie cookie:cookies) {
                if("access_token".equals(cookie.getName())){
                    token1 = cookie.getValue();
                    User user = tokenService.getUserInfo(token1);
                    request.setAttribute("username",user.getUsername());
                    request.setAttribute("uuid", user.getUuid());
                }
            }
        }


        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.error("开始验证是否有AuthToken");
        // 如果打上了AuthToken注解则需要验证token
        Boolean isLogined = true;//是否已登录
        if(method.getAnnotation(AuthToken.class)!=null || handlerMethod.getBeanType().getAnnotation(AuthToken.class)!=null){
            String token = request.getHeader(httpHeaderName);
            String username="";
            String uuid="";
            if (token!=null && token.length()!=0){
                logger.debug("Token's username ='"+username+"'");
                User user = tokenService.getUserInfo(token);
                username = user.getUsername();
                uuid = user.getUuid();
            } else { //token为空
                isLogined = false;
            }

            if(isLogined){
                request.setAttribute(REQUEST_CURRENT_KEY,uuid);
                return true;
            } else {//未登录
                String jsonObject = JSONObject.toJSONString(ApiTools.result(10001,"用户未登录",null));
                returnJson(response,jsonObject);
                return false;
            }

        } else {
//            PrintWriter out;
//            response.setStatus(authErrorCode);
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            out = response.getWriter();
//            out.println("{code:400}");
//            //out.println(ApiTools.result(authErrorCode,"mess","eooro"));
            request.setAttribute(REQUEST_CURRENT_KEY, null);
            return true;
        }
    }
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
