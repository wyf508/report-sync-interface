package com.djhu.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description receive数据源配置, 默认的数据源
 */
@Configuration
@MapperScan(basePackages = ReceiveDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "receiveSqlSessionFactory")
public class ReceiveDataSourceConfig {

    static final String PACKAGE = "com.djhu.dao.receive";
    static final String MAPPER_LOCATION = "classpath:mapper/receive/*.xml";

    //数据源连接信息
    @Value("${spring.datasource.receive.url}")
    private String url;

    @Value("${spring.datasource.receive.username}")
    private String username;

    @Value("${spring.datasource.receive.password}")
    private String password;

    @Value("${spring.datasource.receive.driver}")
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
     * receive数据源配置
     *
     * @return
     */
    @Bean(name = "receiveDataSource")
    @Primary
    public DataSource receiveDataSource() {
        DruidDataSource sourceDataSource = new DruidDataSource();
        //连接配置
        sourceDataSource.setUrl(url);
        sourceDataSource.setUsername(username);
        sourceDataSource.setPassword(password);
        sourceDataSource.setDriverClassName(driverClassName);

        //具体配置
        sourceDataSource.setInitialSize(initialSize);
        sourceDataSource.setMinIdle(minIdle);
        sourceDataSource.setMaxActive(maxActive);
        sourceDataSource.setMaxWait(maxWait);
        sourceDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        sourceDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        sourceDataSource.setValidationQuery(validationQuery);
        sourceDataSource.setTestWhileIdle(testWhileIdle);
        sourceDataSource.setTestOnBorrow(testOnBorrow);
        sourceDataSource.setTestOnReturn(testOnReturn);
        sourceDataSource.setPoolPreparedStatements(poolPreparedStatements);
        sourceDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            sourceDataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sourceDataSource.setConnectionProperties(connectionProperties);
        return sourceDataSource;
    }

    @Bean(name = "receiveTransactionManager")
    @Primary
    public DataSourceTransactionManager sourceTransactionManager() {
        return new DataSourceTransactionManager(receiveDataSource());
    }

    @Bean(name = "receiveSqlSessionFactory")
    @Primary
    public SqlSessionFactory receiveSqlSessionFactory(@Qualifier("receiveDataSource") DataSource receiveDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(receiveDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ReceiveDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }


}
