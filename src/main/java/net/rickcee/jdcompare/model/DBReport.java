/**
 * 
 */
package net.rickcee.jdcompare.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * @author catalrc
 * 
 */
@Entity
@Table(name = "dbreport")
@Data
public class DBReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private DBConfiguration db1;
	@ManyToOne(fetch = FetchType.EAGER)
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

}
