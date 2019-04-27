package net.rickcee.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.rickcee.view.ColumnDataView;
import net.rickcee.view.ResultView;
import net.rickcee.view.RowDataView;

/**
 * @author catalrc
 * 
 */
public class QueryComparator {
	private QueryResult result1 = new QueryResult();
	private QueryResult result2 = new QueryResult();

	public static final String NA = "N/A";
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";

	public enum colValueType {
		RESULT1, RESULT2
	};
	
	private Map<String, String> columnTypes = new HashMap<String, String>();
	private String pkColumn;

	// 1st Level: Map< Row PK, ... >
	// 2st Level: Map< Col PK, ... >
	// 3rd Level: Map< RESULT1, ... >, Map< RESULT2, ... >
	private Map<String, Map<String, Map<String, Object>>> rows = new TreeMap<String, Map<String, Map<String, Object>>>();

	private List<String> metadataPks = new ArrayList<String>();
	private List<String> metadata = new ArrayList<String>();

	/**
	 * @param selectedColumns
	 * @param diffColumns
	 */
	public void consolidateResults() {

		// Iterate over rows in source table 1
		for (String rowPK : result1.getRows().keySet()) {
			// Avoid processing NULL entries
			if (rowPK != null) {
				Map<String, Map<String, Object>> row = rows.get(rowPK);
				if (row == null) {
					row = new TreeMap<String, Map<String, Object>>();
					rows.put(rowPK, row);
				}

				// Iterate over columns in source table 1
				for (String colPK : result1.getRows().get(rowPK).getColumns().keySet()) {
					Object value = result1.getRows().get(rowPK).getColumns().get(colPK);
					// System.out.println(rowPK + " // " + colPK + "=" + value);
					addResultSource1(row, colPK, value);
				}
			}
		}

		// Iterate over rows in source table 2
		for (String rowPK : result2.getRows().keySet()) {
			// Avoid processing NULL entries
			if (rowPK != null) {
				Map<String, Map<String, Object>> row = rows.get(rowPK);
				if (row == null) {
					row = new TreeMap<String, Map<String, Object>>();
					rows.put(rowPK, row);
				}

				// Iterate over columns in source table 2
				for (String colPK : result2.getRows().get(rowPK).getColumns().keySet()) {
					Object value = result2.getRows().get(rowPK).getColumns().get(colPK);
					// System.out.println(rowPK + " // " + colPK + "=" + value);
					addResultSource2(row, colPK, value);
				}
			}
		}
	}

	/**
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void addResultSource1(Map<String, Map<String, Object>> row, String colName, Object value) {
		Map<String, Object> values = row.get(colName);
		if (values == null) {
			values = new TreeMap<String, Object>();
			row.put(colName, values);
		}
		values.put(colValueType.RESULT1.toString(), value);
	}

	/**
	 * @param row
	 * @param colName
	 * @param value
	 */
	public void addResultSource2(Map<String, Map<String, Object>> row, String colName, Object value) {
		Map<String, Object> values = row.get(colName);
		if (values == null) {
			values = new TreeMap<String, Object>();
			row.put(colName, values);
		}
		values.put(colValueType.RESULT2.toString(), value);
	}

	// Map<String, Map<String, Map<String, Object>>>
	/**
	 * @return
	 */
	public ResultView createViewObject() {
		ResultView finalResultView = new ResultView();
		List<RowDataView> result = new ArrayList<RowDataView>();
		List<String> cols = new ArrayList<String>();
		cols.addAll(metadataPks);
		cols.addAll(metadata);

		for (String row : rows.keySet()) {
			RowDataView viewRow = new RowDataView();
			Map<String, Map<String, Object>> columns = rows.get(row);

			NumberFormat formatter = new DecimalFormat("###,###,###,###.##");

			for (String column : cols) {
				ColumnDataView viewColumn = new ColumnDataView();
				viewColumn.setFieldName(column);
				String columnType = columnTypes.get(viewColumn.getFieldName());
				Double d1 = new Double(0);
				Double d2 = new Double(0);

				String result1 = (String) columns.get(column).get(colValueType.RESULT1.toString());
				if (result1 != null && !result1.toString().trim().equals("")) {
					if (isNumeric(columnType)) {
						d1 = new Double(result1);
						viewColumn.setFieldValue1(formatter.format(d1));
					} else {
						viewColumn.setFieldValue1(result1);
					}
				} else {
					viewColumn.setFieldValue1(NA);
				}
				String result2 = (String) columns.get(column).get(colValueType.RESULT2.toString());
				if (result2 != null && !result2.toString().trim().equals("")) {
					if (isNumeric(columnType)) {
						d2 = new Double(result2);
						viewColumn.setFieldValue2(formatter.format(d2));
					} else {
						viewColumn.setFieldValue2(result2);
					}
				} else {
					viewColumn.setFieldValue2(NA);
				}

				if (!viewColumn.getFieldValue1().equals(NA) && !viewColumn.getFieldValue2().equals(NA)) {
					if (isNumeric(columnType)) {
						Double _result = d2 - d1;
						viewColumn.setDiff(_result);
						if (_result.equals(new Double(0))) {
							viewColumn.setIsOK(Boolean.TRUE);
						}
					} else {
						Boolean _result = result1.equals(result2);
						if (_result) {
							viewColumn.setDiff(OK);
							viewColumn.setIsOK(Boolean.TRUE);
						} else {
							viewColumn.setDiff(ERROR);
						}
					}
				} else {
					viewColumn.setDiff(NA);
				}

				if (pkColumn.equals(column)) {
					// To fix the issue when row is present in DS2 but not in DS1
					if (viewColumn.getFieldValue1() != null && viewColumn.getFieldValue1() != NA) {
						viewColumn.setDisplayValue(viewColumn.getFieldValue1().toString());
					} else if (viewColumn.getFieldValue2() != null && viewColumn.getFieldValue2() != NA) {
						viewColumn.setDisplayValue(viewColumn.getFieldValue2().toString());
					} else {
						viewColumn.setDisplayValue(NA);
					}
					viewRow.getPks().add(viewColumn);
				} else {
					viewRow.getResult().add(viewColumn);
				}

			}
			result.add(viewRow);
		}

		finalResultView.setRows(result);
		finalResultView.setMetadata(metadata);
		finalResultView.setMetadataPK(metadataPks);
		return finalResultView;
	}

	/**
	 * @param columnType
	 * @return
	 */
	private boolean isNumeric(String columnType) {
		return columnType != null
				&& (columnType.contains("float") || columnType.contains("int") || columnType.contains("long") || columnType
						.contains("double"));
	}

	/**
	 * @param model
	 * @param mc
	 * @param finalResultView
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void executeReport(DBModel model)
			throws ClassNotFoundException, SQLException {
		Connection c, c2;
		ResultSet rs, rs2;
		Statement st, st2;
		ResultSetMetaData m, m2;

		c = model.getReport().getDb1().createConnection();
		st = c.createStatement();
		rs = st.executeQuery(model.getReport().getSqlQuery());

		boolean firstTimePK = true;
		boolean firstTime = true;
		m = rs.getMetaData();
		while (rs.next()) {
			QueryResultRow row = new QueryResultRow();
			String pk = null;
			for (int i = 1; i <= m.getColumnCount(); i++) {
				String name = m.getColumnName(i);
				String value = rs.getString(name);
				// First column should be the PK
				if (i == 1) {
					pk = value;
					// Add the PK only once to the list
					if (firstTimePK) {
						metadataPks.add(name);
						pkColumn = name;
						firstTimePK = false;
					}
				}
				// Add the column names / types only once to the list / type map.
				if (firstTime && i != 1) {
					columnTypes.put(name, rs.getMetaData().getColumnTypeName(i));
					metadata.add(name);
				}
				// Add column data to the row map
				row.getColumns().put(name, value);
			}
			firstTime = false;
			// Add row data to the result map
			getResult1().getRows().put(pk, row);
		}

		c2 = model.getReport().getDb2().createConnection();
		st2 = c2.createStatement();
		rs2 = st2.executeQuery(model.getReport().getSqlQuery());

		m2 = rs2.getMetaData();
		while (rs2.next()) {
			QueryResultRow row = new QueryResultRow();
			String pk = null;
			for (int i = 1; i <= m2.getColumnCount(); i++) {
				String name = m2.getColumnName(i);
				String value = rs2.getString(name);
				if (i == 1) {
					pk = value;
				}
				// Add column data to the row map
				row.getColumns().put(name, value);
			}
			// Add row data to the result map
			getResult2().getRows().put(pk, row);
		}
	}
	
	/**
	 * @return the result1
	 */
	public QueryResult getResult1() {
		return result1;
	}

	/**
	 * @param result1
	 *            the result1 to set
	 */
	public void setResult1(QueryResult result1) {
		this.result1 = result1;
	}

	/**
	 * @return the result2
	 */
	public QueryResult getResult2() {
		return result2;
	}

	/**
	 * @param result2
	 *            the result2 to set
	 */
	public void setResult2(QueryResult result2) {
		this.result2 = result2;
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
	 * @return the metadataPks
	 */
	public List<String> getMetadataPks() {
		return metadataPks;
	}

	/**
	 * @param metadataPks
	 *            the metadataPks to set
	 */
	public void setMetadataPks(List<String> metadataPks) {
		this.metadataPks = metadataPks;
	}

}
