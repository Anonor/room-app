package room.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import room.common.interceptor.LoginHandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行路径（只拦截web管理员的非登录界面，移动端均不拦截）
        List<String> patterns = new ArrayList();
        patterns.add("/admin/login");//管理员登录

        //只注册管理员拦截器，移动端功能未添加进放行路径默认放行
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(patterns);
    }
}

