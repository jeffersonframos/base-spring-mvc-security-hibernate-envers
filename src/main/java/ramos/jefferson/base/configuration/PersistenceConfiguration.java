package ramos.jefferson.base.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "ramos.jefferson.base.repository", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class PersistenceConfiguration {
    
    @Bean
    public DataSource dataSource(Environment env){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        config.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        config.setUsername(env.getRequiredProperty("spring.datasource.username"));
        config.setPassword(env.getRequiredProperty("spring.datasource.password"));
        try{
            config.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.connection.pool_size")));
        }catch(IllegalStateException | NumberFormatException ex){
            config.setMaximumPoolSize(5);
        }
        config.setConnectionTestQuery("SELECT 1");
        return new HikariDataSource(config);
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ramos.jefferson.base.entity", "ramos.jefferson.base.audity");
        
        Properties jpaProperties = new Properties();
        jpaProperties.put("org.hibernate.envers.store_data_at_delete", "true");
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("spring.jpa.hibernate.format_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.hbm2ddl.auto"));
        
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
