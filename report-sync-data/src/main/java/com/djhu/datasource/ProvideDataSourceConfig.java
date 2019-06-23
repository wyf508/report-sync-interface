package com.djhu.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description provide数据源配置
 */
@Configuration
@MapperScan(basePackages = ProvideDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "provideSqlSessionFactory")
public class ProvideDataSourceConfig {

    static final String PACKAGE = "com.djhu.dao.provide";
    static final String MAPPER_LOCATION = "classpath:mapper/provide/*.xml";

    //数据源连接信息
    @Value("${spring.datasource.provide.url}")
    private String url;

    @Value("${spring.datasource.provide.username}")
    private String username;

    @Value("${spring.datasource.provide.password}")
    private String password;

    @Value("${spring.datasource.provide.driver}")
    private String driverClassName;

    //数据源其他配置
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;

    /**
     * provide数据源配置
     *
     * @return
     */
    @Bean(name = "provideDataSource")
    public DataSource provideDataSource() {
        DruidDataSource provideDataSource = new DruidDataSource();
        //连接配置
        provideDataSource.setUrl(url);
        provideDataSource.setUsername(username);
        provideDataSource.setPassword(password);
        provideDataSource.setDriverClassName(driverClassName);

        //具体配置
        provideDataSource.setInitialSize(initialSize);
        provideDataSource.setMinIdle(minIdle);
        provideDataSource.setMaxWait(maxWait);
        provideDataSource.setMaxActive(maxActive);
        provideDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        provideDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        provideDataSource.setValidationQuery(validationQuery);
        provideDataSource.setTestWhileIdle(testWhileIdle);
        provideDataSource.setTestOnBorrow(testOnBorrow);
        provideDataSource.setTestOnReturn(testOnReturn);
        provideDataSource.setPoolPreparedStatements(poolPreparedStatements);
        provideDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            provideDataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        provideDataSource.setConnectionProperties(connectionProperties);
        return provideDataSource;
    }

    @Bean(name = "provideTransactionManager")
    public DataSourceTransactionManager provideTransactionManager() {
        return new DataSourceTransactionManager(provideDataSource());
    }

    @Bean(name = "provideSqlSessionFactory")
    public SqlSessionFactory provideSqlSessionFactory(@Qualifier("provideDataSource")DataSource provideDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(provideDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ProvideDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }


}
