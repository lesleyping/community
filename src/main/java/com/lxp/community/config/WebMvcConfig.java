package com.lxp.community.config;

import com.lxp.community.controller.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

//    @Autowired
//    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    private MessageInterceptor messageInterceptor;

    @Autowired
    private DateInterceptor dateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //// .excludePathPatterns 表示哪些路径不被拦截比方说一些静态资源，
        //    // .addPathPatterns 表示确定的拦截的路径
        // /**即为/static目录下所有的文件夹
        registry.addInterceptor(alphaInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg")
                .addPathPatterns("/register", "/login");

        //所有页面都要做如下处理，除了静态资源
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

//        //所有页面都要做如下处理，除了静态资源
//        registry.addInterceptor(loginRequiredInterceptor)
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        //所有页面都要做如下处理，除了静态资源
        registry.addInterceptor(messageInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

        registry.addInterceptor(dateInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
    }
}
