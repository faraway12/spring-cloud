package com.study.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <p> Title: DataSourceConfig </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-22
 */
@Configuration
public class DataSourceConfig {

    @Value("${config.datasource.driver-class-name}")
    private String driver;
    @Value("${config.datasource.url}")
    private String url;
    @Value("${config.datasource.username}")
    private String username;
    @Value("${config.datasource.password}")
    private String password;

    @Bean("dataSource")
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driver);
        return dataSource;
    }
}
