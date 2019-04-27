package net.rickcee.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author catalrc
 * 
 */
public class QueryResult {
	private Map<String, QueryResultRow> rows = new HashMap<String, QueryResultRow>();

	/**
	 * @return the rows
	 */
	public Map<String, QueryResultRow> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(Map<String, QueryResultRow> rows) {
		this.rows = rows;
	}
	
}
