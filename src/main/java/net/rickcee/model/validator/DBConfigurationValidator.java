package net.rickcee.model.validator;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.rickcee.model.DBConfiguration;

@Repository
public class DBConfigurationValidator implements Validator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(DBConfiguration.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		DBConfiguration report = (DBConfiguration) target;
		if (report.getDbAlias() == null || report.getDbAlias().equals("")) {
			errors.rejectValue("config.dbAlias", null, "Alias Cannot be empty!");
		}
		if (report.getDbDriver() == null || report.getDbDriver().equals("")) {
			errors.rejectValue("config.dbDriver", null, "Driver Cannot be empty!");
		}
		if (report.getDbURL() == null || report.getDbURL().equals("")) {
			errors.rejectValue("config.dbURL", null, "URL Cannot be empty!");
		}
		if (report.getDbUserName() == null || report.getDbUserName().equals("")) {
			errors.rejectValue("config.dbUserName", null, "UserName Cannot be empty!");
		}
		if (report.getDbPassword() == null || report.getDbPassword().equals("")) {
			errors.rejectValue("config.dbPassword", null, "Password Cannot be empty!");
		}
	}

}
