package com.liuyanzhao.sens.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 言曌
 * @date 2020-01-03 17:03
 */

@Configuration
@MapperScan(basePackages = "com.liuyanzhao.sens.mapper.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Db2DataSourceConfig {

    @Autowired
    private Db2DataSourceProperties db2DataSourceProperties;

    @Bean(name = "db2DataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(db2DataSourceProperties.getDriverClassName());
        dataSource.setUrl(db2DataSourceProperties.getUrl());
        dataSource.setUsername(db2DataSourceProperties.getUsername());
        dataSource.setPassword(db2DataSourceProperties.getPassword());
        dataSource.setInitialSize(db2DataSourceProperties.getInitialSize());
        dataSource.setMinIdle(db2DataSourceProperties.getMinIdle());
        dataSource.setMaxActive(db2DataSourceProperties.getMaxActive());
        return dataSource;
    }

    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource,
                                               @Qualifier("camelCaseConfiguration") org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(configuration);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:mapper/db2/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
