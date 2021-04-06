package com.building.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * druid数据源配置(过滤url、sql注入监控、数据源监控)
 * @author yinjiahui
 * @create 2021-04-05 10:49
 */
@Configuration
public class DruidConfig {

    /**
     *
     * properties配置文件中增加:
     *
     * # 配置监控统计拦截的 filters， # stat监控统计, log4j日志记录,wall防sql注入
     * spring.datasource.filters: stat,wall,log4j
     * spring.datasource.maxPoolPreparedStatementPerConnectionSize: 20
     * spring.datasource.useGlobalDataSourceStat: true
     * spring.datasource.connectionProperties:
     * druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
     *
     * 说明: 1.使用@ConfigurationProperties 获取配置文件中spring.datasource数据,创建DataSource对象
     * 2.使用@Bean标签,把DataSource对象,放入spring容器中管理(类似xml中配置dataSource数据源一样,让spring管理)
     *
     * @return dataSource 数据源
     **/
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     *
     * 配置一个管理后台的Servlet
     *
     * 配置Druid监控后台 说明: springboot中没有web.xml文件,所有需要通过以下方法进行注册servlet
     *
     * 实际本方法类似web.xml一样,配置了一个servlet initParams是servlet的参数
     * 利用@bean标签创造一个ServletRegistrationBean对象,注册到servlet容器中,此servlet即可生效
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> servlet = new ServletRegistrationBean<StatViewServlet>(
                new StatViewServlet(), "/druid/*");// 监控页面地址

        servlet.addInitParameter("loginUsername", "admin");// 用户名

        servlet.addInitParameter("loginPassword", "admin");// 密码

        servlet.addInitParameter("allow", "");// 默认就是允许所有访问

//		servlet.addInitParameter("huangp", "127.0.0.1");不允许谁访问

        servlet.addInitParameter("resetEnable", "false");// 是否可以重置数据源

        return servlet;
    }

    /**
     * 配置一个web监控的filter过滤器
     * 用于监控url请求
     * @return
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {

        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();

        bean.setFilter(new WebStatFilter());

        bean.setUrlPatterns(Arrays.asList("/*")); // 过滤所有请求

        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");  //不过滤的资源

        return bean;
    }

}
