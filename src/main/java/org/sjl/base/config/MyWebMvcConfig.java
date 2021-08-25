package org.sjl.base.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
//    @Bean
//    public MyWebMvcConfig getMyWebMvcConfig(){
//        MyWebMvcConfig myWebMvcConfig = new MyWebMvcConfig() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("alogin");
//                registry.addViewController("/login").setViewName("alogin");
//                registry.addViewController("/main.html").setViewName("dashboard");
//            }
//            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/login","/","/user/login");
//            }
//        };
//        return myWebMvcConfig;
//    }
//
}
