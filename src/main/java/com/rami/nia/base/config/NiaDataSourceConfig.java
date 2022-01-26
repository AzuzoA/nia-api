package com.rami.nia.base.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(value = {"com.rami.nia.base.repository"},
        sqlSessionFactoryRef = "niaSqlSessionFactory")
public class NiaDataSourceConfig {
    
    @Bean
    @Qualifier("secondaryHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig secondaryHikariConfig() {
        return new HikariConfig();
    }
    
    @Bean
    @Qualifier("niaDataSource")
    public DataSource niaDataSource() throws Exception {
        return new HikariDataSource(secondaryHikariConfig());
    }
    
    @Bean(name = "niaSqlSessionFactory")
    public SqlSessionFactory niaSqlSessionFactory(@Autowired @Qualifier("niaDataSource") DataSource dataSource
            , ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/nia/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean(name = "niaSqlSessionTemplate")
    public SqlSessionTemplate niaSqlSessionTemplate(
            @Qualifier("niaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
}