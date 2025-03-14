/**
 * 
 */
package net.rickcee.jdcompare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rickcee
 *
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
//		JdbcClientAutoConfiguration.class, JdbcTemplateAutoConfiguration.class })
//@EnableAutoConfiguration
//@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
////@EntityScan(basePackages = {"net.rickcee.jdcompare.dbmodel.*"} )
//@EnableJpaRepositories(basePackages = { "net.rickcee.jdcompare.repository" } )
public class SBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SBApplication.class);
    }
}
