package com.promotion.product.config;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class MybatisConfig {
//    @Autowired
//    private YmlConfig ymlConfig;
    @Autowired
    private DataSource dataSource;
//    @Bean("dataSource")
//    public DataSource dateSource(){
//        ComboPooledDataSource dataSource = DataSourceBuilder.create().driverClassName(ymlConfig.mysql_driver)
//                .type(com.mchange.v2.c3p0.ComboPooledDataSource.class).url(ymlConfig.mysql_url)
//                .username(ymlConfig.mysql_username).password(ymlConfig.mysql_password).build();
//        return dataSource;
//    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory(DataSource dataSource) throws IOException, SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //启用Mybatis的全部xml文件，就不需要一个个去打开
        String packageSerchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mapper/**.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSerchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dateSource());
//    }
}
