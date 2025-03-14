/**
 * 
 */
package net.rickcee.jdcompare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rickcee.jdcompare.model.DBReport;

/**
 * @author rickcee
 *
 */
public interface DBReportRepository extends JpaRepository<DBReport, Long> {

}
