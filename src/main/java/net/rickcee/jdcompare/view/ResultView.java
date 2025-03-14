package net.rickcee.jdcompare.view;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
	private List<RowDataView> rows;
	private List<String> metadata = new ArrayList<String>();
	private List<String> metadataPK = new ArrayList<String>();
	private String dbAlias1;
	private String dbAlias2;

	/**
	 * @return the dbAlias1
	 */
	public String getDbAlias1() {
		return dbAlias1;
	}

	/**
	 * @param dbAlias1
	 *            the dbAlias1 to set
	 */
	public void setDbAlias1(String dbAlias1) {
		this.dbAlias1 = dbAlias1;
	}

	/**
	 * @return the dbAlias2
	 */
	public String getDbAlias2() {
		return dbAlias2;
	}

	/**
	 * @param dbAlias2
	 *            the dbAlias2 to set
	 */
	public void setDbAlias2(String dbAlias2) {
		this.dbAlias2 = dbAlias2;
	}

	/**
	 * @return the rows
	 */
	public List<RowDataView> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<RowDataView> rows) {
		this.rows = rows;
	}

	/**
	 * @return the metadata
	 */
	public List<String> getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata
	 *            the metadata to set
	 */
	public void setMetadata(List<String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the metadataPK
	 */
	public List<String> getMetadataPK() {
		return metadataPK;
	}

	/**
	 * @param metadataPK
	 *            the metadataPK to set
	 */
	public void setMetadataPK(List<String> metadataPK) {
		this.metadataPK = metadataPK;
	}

}
