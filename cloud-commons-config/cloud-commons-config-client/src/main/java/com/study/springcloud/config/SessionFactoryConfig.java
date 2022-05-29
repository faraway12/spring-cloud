package com.study.springcloud.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p> Title: SessionFactoryConfig </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-22
 */
@Configuration
public class SessionFactoryConfig {

    @Value("${config.mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${config.mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Resource(name="dataSource")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(mapperLocations));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return sqlSessionFactoryBean;
    }
}
