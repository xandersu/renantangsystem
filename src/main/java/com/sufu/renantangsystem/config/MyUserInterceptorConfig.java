package com.sufu.renantangsystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2018/6/19.
 */
@Component
public class MyUserInterceptorConfig  implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器");
//        if(request.getRequestURI().equals("/login")){
//            return true;
//        }else
        if(request.getSession().getAttribute("loginUser")  != null){
//            logger.info(request.getSession().getAttribute("loginUser").toString());
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
