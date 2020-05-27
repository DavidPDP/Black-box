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
@EnableJpaRepositories(basePackages = {"co.edu.icesi.metrocali.blackbox.repositories.policies",
		"co.edu.icesi.metrocali.blackbox.repositories.events"}, 
		entityManagerFactoryRef = "eventsEntityManager", 
		transactionManagerRef = "eventsTransactionManager")
public class PgAviomConf {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.events")
	public DataSourceProperties eventsDSProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	public DataSource eventsDataSource(@Qualifier("eventsDSProperties") DataSourceProperties eventsDSProperties) {
		return eventsDSProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean eventsEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("eventsDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("co.edu.icesi.metrocali.blackbox.entities.policies",
						"co.edu.icesi.metrocali.blackbox.entities.event_managment").persistenceUnit("events").build();
	}

	@Bean
	@Primary
	public PlatformTransactionManager eventsTransactionManager(
			@Qualifier("eventsEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
