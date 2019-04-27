/**
 * 
 */
package net.rickcee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author catalrc
 * 
 */
@Entity
@Table(name = "dbconfig")
public class DBConfiguration {
	public final static String CLAZZ = DBConfiguration.class.getName();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String dbDriver;
	@Column
	private String dbAlias;
	@Column
	private String dbURL;
	@Column
	private String dbUserName;
	@Column
	private String dbPassword;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return dbUserName + "@" + dbAlias + " - " + dbURL;
	}
	
	/**
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		System.out.println("Creating connection for " + dbURL);
		Class.forName(dbDriver);
		conn = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		return conn;
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
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @param dbDriver
	 *            the dbDriver to set
	 */
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	/**
	 * @return the dbAlias
	 */
	public String getDbAlias() {
		return dbAlias;
	}

	/**
	 * @param dbAlias
	 *            the dbAlias to set
	 */
	public void setDbAlias(String dbAlias) {
		this.dbAlias = dbAlias;
	}

	/**
	 * @return the dbURL
	 */
	public String getDbURL() {
		return dbURL;
	}

	/**
	 * @param dbURL
	 *            the dbURL to set
	 */
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	/**
	 * @return the dbUserName
	 */
	public String getDbUserName() {
		return dbUserName;
	}

	/**
	 * @param dbUserName
	 *            the dbUserName to set
	 */
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword
	 *            the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

}
