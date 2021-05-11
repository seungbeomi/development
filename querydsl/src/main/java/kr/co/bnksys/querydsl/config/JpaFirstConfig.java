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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories(basePackages = {
        "kr.co.bnksys.querydsl.data.repository.first" }, entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager")
@Configuration
public class JpaFirstConfig {

    private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";

    @Autowired
    private DataSource firstDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),
                new HibernateSettings());

        properties.put("hibernate.default_schema", "BNK_DB");

        Set<String> keys = properties.keySet();
        for (String key : keys) {
            log.debug("FIRST : {} = {}", key, properties.get(key));
        }

        return builder.dataSource(firstDataSource)
                .properties(properties)
                .packages("kr.co.bnksys.querydsl.model.first")
                .persistenceUnit("first")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(firstEntityManagerFactory(builder).getObject());
    }

}
