/**
 * 
 */
package net.rickcee.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.rickcee.model.DBConfiguration;
import net.rickcee.model.DBModel;
import net.rickcee.model.DBReport;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.Environment.*;

/**
 * @author RickCee
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
		@ComponentScan("net.rickcee.model"),
		@ComponentScan("net.rickcee.model.persistence"), 
		@ComponentScan("net.rickcee.model.facade"), 
		@ComponentScan("net.rickcee.model.validator") })
public class AppConfig {

	@Autowired
	private Environment env;

//	@Bean
//	public LocalSessionFactoryBean getSessionFactory() {
//		System.out.println("Creating Session factory....");
//		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//
//		Properties props = new Properties();
//		// // Setting JDBC properties
//		// props.put(DRIVER, env.getProperty("mysql.driver"));
//		// props.put(URL, env.getProperty("mysql.url"));
//		// props.put(USER, env.getProperty("mysql.user"));
//		// props.put(PASS, env.getProperty("mysql.password"));
//		//
//		// Setting Hibernate properties
//		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
//		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
//		props.put(DIALECT, env.getProperty("hibernate.dialect"));
//
//		// Setting C3P0 properties
//		// props.put(C3P0_MIN_SIZE,
//		// env.getProperty("hibernate.c3p0.min_size"));
//		// props.put(C3P0_MAX_SIZE,
//		// env.getProperty("hibernate.c3p0.max_size"));
//		// props.put(C3P0_ACQUIRE_INCREMENT,
//		// env.getProperty("hibernate.c3p0.acquire_increment"));
//		// props.put(C3P0_TIMEOUT,
//		// env.getProperty("hibernate.c3p0.timeout"));
//		// props.put(C3P0_MAX_STATEMENTS,
//		// env.getProperty("hibernate.c3p0.max_statements"));
//
//		factoryBean.setHibernateProperties(props);
//		factoryBean.setDataSource(getDataSource());
//		factoryBean.setAnnotatedClasses(DBModel.class, DBReport.class, DBConfiguration.class);
//		return factoryBean;
//	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getDataSource());
		Properties props = new Properties();
		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		props.put(DIALECT, env.getProperty("hibernate.dialect"));
		sessionBuilder.addProperties(props);
		sessionBuilder.scanPackages("net.rickcee.model");
	    return sessionBuilder.buildSessionFactory();
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		System.out.println("Creating DataSource....");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.user"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		System.out.println("Creating Transaction Manager....");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory());
		return transactionManager;
	}	
	
//	@Bean
//	public DAO getDAO() {
//		DAO dao = new DAO();
//		dao.setSessionFactory(getSessionFactory());
//	};
}