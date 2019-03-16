package com.cheersson.qrcode.configuration;

//import org.h2.server.web.WebServlet;
import com.cheersson.qrcode.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration(){
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("classpath*:dispatcherServlet-servlet.xml");
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.addUrlMappings("/");
        return registrationBean;
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        return loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }
}
