package kr.co.bnksys.querydsl.config;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories(basePackages = {
        "kr.co.bnksys.querydsl.data.repository.second" }, entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager")
@Configuration
public class JpaSecondConfig {

    private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";

    @Autowired
    private DataSource secondDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),
                new HibernateSettings());

        properties.put("hibernate.default_schema", "EBC_DB");

        Set<String> keys = properties.keySet();
        for (String key : keys) {
            log.debug("SECOND : {} = {}", key, properties.get(key));
        }

        return builder.dataSource(secondDataSource)
                .properties(properties)
                .packages("kr.co.bnksys.querydsl.model.second")
                .persistenceUnit("second")
                .build();
    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondEntityManagerFactory(builder).getObject());
    }

}
