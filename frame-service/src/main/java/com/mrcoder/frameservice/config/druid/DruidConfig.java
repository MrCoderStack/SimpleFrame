package com.mrcoder.frameservice.config.druid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Description: Druid监控配置
 * @author: mrcoder
 */
@Configuration
public class DruidConfig {

    /**
     * 配置Druid数据库连接池
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /***** 配置Druid的监控 *****/
    /**
     * 1、配置一个管理后台的Servlet
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("loginUsername", "mrcoder");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", ""); // 默认就是允许所有访问
        // 初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 2、配置一个web监控的filter
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        // 不拦截那些请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        // 初始化参数
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}

