package net.rickcee.jdcompare.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author catalrc
 * 
 */
public class QueryResultRow {
	private Map<String, String> columns = new HashMap<String, String>();

	public String getPK(String[] groupByColumns) {
		String pk = "";
		if(groupByColumns.length > 0) {
			for(String temp: groupByColumns) {
				pk = pk.concat("PK_").concat(temp).concat("_").concat(columns.get(temp));
			}
		}
		return pk;
	}
	
	/**
	 * @return the columns
	 */
	public Map<String, String> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

}
