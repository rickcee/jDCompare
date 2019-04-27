package net.rickcee.model.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.rickcee.model.DBReport;

@Repository
public class DBReportValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(DBReport.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		DBReport report = (DBReport) target;
		if (report.getDbID1() == null || report.getDbID1().equals("")) {
			errors.rejectValue("report.dbID1", null, "You must select a server!");
		}
		if (report.getDbID2() == null || report.getDbID2().equals("")) {
			errors.rejectValue("report.dbID2", null, "You must select a server!");
		}

		if (report.getSqlQuery() == null || report.getSqlQuery().equals("")) {
			errors.rejectValue("report.sqlQuery", null, "SQL Query Cannot be empty!");
		}
		if (report.getSqlQueryDescription() == null || report.getSqlQueryDescription().equals("")) {
			errors.rejectValue("report.sqlQueryDescription", null, "Query Description Cannot be empty!");
		}
		if (report.getSqlQueryName() == null || report.getSqlQueryName().equals("")) {
			errors.rejectValue("report.sqlQueryName", null, "Query Name Cannot be empty!");
		}

	}

}
