package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:/application.properties"})
@MapperScan(basePackages = {"org.scoula.board.mapper","org.scoula.member.mapper","org.scoula.travel.mapper" })
@ComponentScan(basePackages = {"org.scoula.board.service","org.scoula.member.service","org.scoula.travel.service"})
@Log4j2
@EnableTransactionManagement


//@MapperScan(basePackages={})
public class RootConfig {
    @Value("${jdbc.driver}") String driver;
    @Value("${jdbc.url}") String url;
    @Value("${jdbc.username}") String username;
    @Value("${jdbc.password}") String password;
    @Autowired ApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

// 커넥션 풀 추가 설정 (선택사항)
        config.setMaximumPoolSize(10);             // 최대 커넥션 수
        config.setMinimumIdle(5);                  // 최소 유지 커넥션 수
        config.setConnectionTimeout(30000);       // 연결 타임아웃 (30초)
        config.setIdleTimeout(600000);            // 유휴 타임아웃 (10분)


        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }



    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){

        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;

    }


}