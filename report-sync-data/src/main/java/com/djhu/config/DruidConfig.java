package com.djhu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Creator yf.wu
 * @CreateDate 2019/5/25 0025 下午 4:50
 * @Description Druid 配置
 */
@Configuration
public class DruidConfig {

//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//        return new DruidDataSource();
//    }

    @Autowired
    private Environment environment;

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        String prefix = "spring.datasource.druid.statViewServlet.";
        initParams.put("loginUsername", environment.getProperty(prefix + "loginUsername"));
        initParams.put("loginPassword", environment.getProperty(prefix + "loginPassword"));
        initParams.put("allow", "");//默认就是允许所有访问
        bean.setInitParameters(initParams);
        return bean;
    }


    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        String prefix = "spring.datasource.druid.webStatFilter.";
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", environment.getProperty(prefix + "exclusions"));
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
