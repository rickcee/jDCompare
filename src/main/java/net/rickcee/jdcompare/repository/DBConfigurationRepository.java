/**
 * 
 */
package net.rickcee.jdcompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rickcee.jdcompare.model.DBConfiguration;

/**
 * @author rickcee
 *
 */
public interface DBConfigurationRepository extends JpaRepository<DBConfiguration, Long> {

}
