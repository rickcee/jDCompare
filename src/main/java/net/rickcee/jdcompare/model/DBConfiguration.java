/**
 * 
 */
package net.rickcee.jdcompare.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author catalrc
 * 
 */
@Entity
@Table(name = "dbconfig")
@Data
@Slf4j
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
	@Transient
	private String displayValue;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return dbUserName + "@" + dbAlias + " - " + dbURL;
	}
	
	public String getDisplayValue() {
		return dbUserName + "@" + dbAlias + " - " + dbURL;
	}
	
	/**
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		log.info("Creating connection for " + dbURL);
		Class.forName(dbDriver);
		conn = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		return conn;
	}

}
