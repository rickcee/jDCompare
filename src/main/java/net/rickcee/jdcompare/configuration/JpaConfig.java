/**
 * 
 */
package net.rickcee.jdcompare.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author RickCee
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"net.rickcee.jdcompare"} )
@EntityScan(basePackages = {"net.rickcee.jdcompare.model"} )
@EnableJpaRepositories(basePackages = { "net.rickcee.jdcompare.repository" } )
public class JpaConfig {
	@Autowired
	private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("net.rickcee.jdcompare.model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        //dataSource.setUsername(env.getProperty("spring.datasource.username"));
        //dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        return hibernateProperties;
    }
	
//	@Autowired
//	@Bean(name = "sessionFactory")
//	public SessionFactory getSessionFactory() {
//	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
//		Properties props = new Properties();
//		// Setting Hibernate properties
//		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
//		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
//		props.put(DIALECT, env.getProperty("hibernate.dialect"));
//		sessionBuilder.addProperties(props);
//		sessionBuilder.scanPackages("net.rickcee.model");
//	    return sessionBuilder.buildSessionFactory();
//	}
//
//	@Bean(name = "dataSource")
//	public DataSource getDataSource() {
//		System.out.println("Creating DataSource....");
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("db.driver"));
//		dataSource.setUrl(env.getProperty("db.url"));
//		dataSource.setUsername(env.getProperty("db.user"));
//		dataSource.setPassword(env.getProperty("db.password"));
//		return dataSource;
//	}
//
//	@Bean(name = "transactionManager")
//	public HibernateTransactionManager getTransactionManager() {
//		System.out.println("Creating Transaction Manager....");
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(getSessionFactory());
//		return transactionManager;
//	}	
}
