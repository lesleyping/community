package com.lxp.community.controller.interceptor;

import com.lxp.community.annotation.LoginRequired;
import com.lxp.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;

    // 在请求最初判断是否是登录状态
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断object这个拦截目标是不是一个方法
        //如果不是（比如静态资源）则不拦截
        if (handler instanceof HandlerMethod){
            // 进行类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 拦截到的Method的对象
            Method method = handlerMethod.getMethod();
            // 取到对象的注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            //需要登录 但 user是null
            if (loginRequired != null && hostHolder.getUser() == null){
                //response重定向
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
