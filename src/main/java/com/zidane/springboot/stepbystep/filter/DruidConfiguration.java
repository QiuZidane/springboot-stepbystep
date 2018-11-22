package com.zidane.springboot.stepbystep.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Druid的监控页面配置
 * 配置
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {

        // ServletRegistrationBean 提供类进行注册
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数: initParams
        // 白名单：
        bean.addInitParameter("allow", "127.0.0.1");

        // IP黑名单(存在同时时，deny优先于allow)
        bean.addInitParameter("deny", "192.168.1.1"); // 不起作用？

        // 登录查看信息的账号和密码
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "123456");

        // 是否能够重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;

    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());

        // 添加过滤规则
        bean.addUrlPatterns("/*");

        // 添加需要忽略的格式
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;


    }

}
