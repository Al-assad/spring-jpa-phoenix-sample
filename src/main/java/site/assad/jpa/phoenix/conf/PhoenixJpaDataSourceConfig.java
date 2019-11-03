package site.assad.jpa.phoenix.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import site.assad.jpa.phoenix.repository.PhoenixJpaRepositoryImpl;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * phoenix 数据源和实体管理工厂注册
 *
 * @author Al-assad
 * @since 2019/10/26
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"site.assad.jpa.**.repository"},
        entityManagerFactoryRef = "phoenixEntityManagerFactory",
        repositoryBaseClass = PhoenixJpaRepositoryImpl.class,
        transactionManagerRef = "phoenixTransactionManager"
)
public class PhoenixJpaDataSourceConfig {
    
    /**
     * 注册 phoenix 数据源
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.phoenix")
    public DataSource phoenixDataSource() {
        return DataSourceBuilder.create()
                .type(PhoenixNoPoolingDataSource.class)
                .build();
    }
    
    /**
     * 注册实体管理工厂
     */
    @Bean(name = "phoenixEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean phoenixEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(phoenixDataSource())
                .packages("site.assad.**.entity")
                .persistenceUnit("phoenix")
                .build();
        Properties properties = new Properties();
        properties.setProperty("hbm2ddl.auto", "none");
        // 设置方言实现类
        properties.setProperty("hibernate.dialect", "com.ruesga.phoenix.dialect.PhoenixDialect");
        // 设置实体分段策略
        properties.setProperty("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        // 设置拦截器
        properties.setProperty("hibernate.session_factory.interceptor", "site.assad.jpa.phoenix.interceptor.PhoenixInterceptor");
        em.setJpaProperties(properties);
        return em;
    }
    
    /**
     * 注册事务管理器
     */
    @Bean(name = "phoenixTransactionManager")
    @Primary
    public PlatformTransactionManager phoenixTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(phoenixEntityManagerFactory(builder).getObject());
    }
    
    
}


