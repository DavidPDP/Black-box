package co.edu.icesi.metrocali.blackbox.confs;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"co.edu.icesi.metrocali.blackbox.repositories.evaluator"},
        entityManagerFactoryRef = "evaluatorEntityManager",
        transactionManagerRef = "evaluatorTransactionManager")
public class PgEvaluatorConf {

    @Bean
    @ConfigurationProperties("spring.datasource.evaluator")
    public DataSourceProperties evaluatorDSProperties() {

        return new DataSourceProperties();

    }

    @Bean
    public DataSource evaluatorDataSource(
            @Qualifier("evaluatorDSProperties") DataSourceProperties evaluatorDSProperties) {

        return evaluatorDSProperties.initializeDataSourceBuilder().build();

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean evaluatorEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("evaluatorDataSource") DataSource dataSource) {

        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return builder.dataSource(dataSource).properties(properties)
                .packages("co.edu.icesi.metrocali.blackbox.entities.evaluator")
                .persistenceUnit("evaluator").build();

    }

    @Bean
    public PlatformTransactionManager evaluatorTransactionManager(
            @Qualifier("evaluatorEntityManager") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);

    }
}
