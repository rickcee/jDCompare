/**
 * 
 */
package net.rickcee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author catalrc
 * 
 */
@Entity
@Table(name = "dbreport")
public class DBReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private DBConfiguration db1;
	@OneToOne(fetch = FetchType.EAGER)
	private DBConfiguration db2;
	@Transient
	private String dbID1;
	@Transient
	private String dbID2;

	@Column(length = 1000)
	private String sqlQuery;
	@Column
	private String sqlQueryName;
	@Column
	private String sqlQueryDescription;

	/**
	 * @return the dbID1
	 */
	public String getDbID1() {
		return dbID1;
	}

	/**
	 * @param dbID1
	 *            the dbID1 to set
	 */
	public void setDbID1(String dbID1) {
		this.dbID1 = dbID1;
	}

	/**
	 * @return the dbID2
	 */
	public String getDbID2() {
		return dbID2;
	}

	/**
	 * @param dbID2
	 *            the dbID2 to set
	 */
	public void setDbID2(String dbID2) {
		this.dbID2 = dbID2;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the sqlQueryName
	 */
	public String getSqlQueryName() {
		return sqlQueryName;
	}

	/**
	 * @param sqlQueryName
	 *            the sqlQueryName to set
	 */
	public void setSqlQueryName(String sqlQueryName) {
		this.sqlQueryName = sqlQueryName;
	}

	/**
	 * @return the sqlQuery
	 */
	public String getSqlQuery() {
		return sqlQuery;
	}

	/**
	 * @param sqlQuery
	 *            the sqlQuery to set
	 */
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	/**
	 * @return the sqlQueryDescription
	 */
	public String getSqlQueryDescription() {
		return sqlQueryDescription;
	}

	/**
	 * @param sqlQueryDescription
	 *            the sqlQueryDescription to set
	 */
	public void setSqlQueryDescription(String sqlQueryDescription) {
		this.sqlQueryDescription = sqlQueryDescription;
	}

	/**
	 * @return the db1
	 */
	public DBConfiguration getDb1() {
		return db1;
	}

	/**
	 * @param db1
	 *            the db1 to set
	 */
	public void setDb1(DBConfiguration db1) {
		this.db1 = db1;
	}

	/**
	 * @return the db2
	 */
	public DBConfiguration getDb2() {
		return db2;
	}

	/**
	 * @param db2
	 *            the db2 to set
	 */
	public void setDb2(DBConfiguration db2) {
		this.db2 = db2;
	}

}
