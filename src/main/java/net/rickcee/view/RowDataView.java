package net.rickcee.view;

import java.util.ArrayList;
import java.util.List;

public class RowDataView {
	List<ColumnDataView> pks = new ArrayList<ColumnDataView>();
	List<ColumnDataView> result = new ArrayList<ColumnDataView>();

	/**
	 * @return the pks
	 */
	public List<ColumnDataView> getPks() {
		return pks;
	}

	/**
	 * @param pks
	 *            the pks to set
	 */
	public void setPks(List<ColumnDataView> pks) {
		this.pks = pks;
	}

	/**
	 * @return the result
	 */
	public List<ColumnDataView> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<ColumnDataView> result) {
		this.result = result;
	}

}
