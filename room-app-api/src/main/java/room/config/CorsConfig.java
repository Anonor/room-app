package room.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        //1.添加Cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");      //添加允许的跨域信息内容，前端是哪里，进行一个添加
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("http://api.anonor.natapp1.cc");


        //设置是否发送cookie信息
        config.setAllowCredentials(true);       //凭证，是否可以让请求携带一些相应的内容，如是否允许发送cookie信息

        //设置允许请求的方式
        config.addAllowedMethod("*");      //设置是否放行哪些请求的方式，如GET、POST

        //设置允许的Header
        config.addAllowedHeader("*");      //Header参数，前端与后端进行交互时，有一部分的信息是可以放在Header里面的

        //2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);       //path:"/**"代表请求进来适用于所有的路由

        //3.返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
