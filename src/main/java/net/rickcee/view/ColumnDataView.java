package net.rickcee.view;

public class ColumnDataView {
	private String displayValue;
	private String fieldName;
	private Object fieldValue1;
	private Object fieldValue2;
	private Boolean isOK = Boolean.FALSE;
	private Object diff;

	/**
	 * @return the displayValue
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * @param displayValue the displayValue to set
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the fieldValue1
	 */
	public Object getFieldValue1() {
		return fieldValue1;
	}

	/**
	 * @param fieldValue1
	 *            the fieldValue1 to set
	 */
	public void setFieldValue1(Object fieldValue1) {
		this.fieldValue1 = fieldValue1;
	}

	/**
	 * @return the fieldValue2
	 */
	public Object getFieldValue2() {
		return fieldValue2;
	}

	/**
	 * @param fieldValue2
	 *            the fieldValue2 to set
	 */
	public void setFieldValue2(Object fieldValue2) {
		this.fieldValue2 = fieldValue2;
	}

	/**
	 * @return the diff
	 */
	public Object getDiff() {
		return diff;
	}

	/**
	 * @param diff
	 *            the diff to set
	 */
	public void setDiff(Object diff) {
		this.diff = diff;
	}

	/**
	 * @return the isOK
	 */
	public Boolean getIsOK() {
		return isOK;
	}

	/**
	 * @param isOK
	 *            the isOK to set
	 */
	public void setIsOK(Boolean isOK) {
		this.isOK = isOK;
	}

}
