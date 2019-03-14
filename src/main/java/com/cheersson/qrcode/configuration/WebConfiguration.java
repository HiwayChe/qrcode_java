package com.cheersson.qrcode.configuration;

//import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean springmvcServletRegistration(){
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("classpath*:dispatcherServlet-servlet.xml");
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.addUrlMappings("/");
        return registrationBean;
    }
}
