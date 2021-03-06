package com.promotion.product.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Component
@MapperScan(basePackages ="com.promotion.product.dao.mysql", sqlSessionTemplateRef  = "ds1SqlSessionTemplate")
public class MybatisConfig {


    @Bean("ds1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("ds1SqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws IOException, SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //启用Mybatis的全部xml文件，就不需要一个个去打开
        String packageSerchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mapper/**.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSerchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }


    @Primary
    @Bean(name = "ds1SqlSessionTemplate")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
