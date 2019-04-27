package net.rickcee.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.rickcee.model.facade.DBConfigurationFacade;
import net.rickcee.model.facade.DBReportFacade;
import net.rickcee.view.ResultView;

@Repository("model")
public class DBModel {
//	@Autowired
//	private ReportManager reportManager;
	@Autowired
	private DBConfigurationFacade facadeConfig;
	@Autowired
	private DBReportFacade facadeReport;

	private List<DBConfiguration> dbs = new ArrayList<DBConfiguration>();
	private List<DBReport> reports = new ArrayList<DBReport>();

	private Map<Long, DBReport> reportMap = new HashMap<Long, DBReport>();
	private Map<Long, DBConfiguration> configMap = new HashMap<Long, DBConfiguration>();

	private DBReport report = new DBReport();
	private DBConfiguration config = new DBConfiguration();

	// private QueryComparator queryComparator;
	private ResultView resultView;

	private Boolean isDeleteReport = Boolean.FALSE;
	private Boolean isDeleteConfig = Boolean.FALSE;

	private String reportID;
	private String configID;

	private String exceptionMessage;
	private String exceptionTrace;

	
	public DBModel() {
		System.out.println("=======================================");
		System.out.println("= Creating DBModel...");
		System.out.println("=======================================");
	}
 	/**
	 * @throws Exception
	 */
	public void reloadData() throws Exception {
		dbs = (List<DBConfiguration>) facadeConfig.getAll();
		configMap = new HashMap<Long, DBConfiguration>();
		for (DBConfiguration config : dbs) {
			configMap.put(config.getId(), config);
		}

		reports = (List<DBReport>) facadeReport.getAll();
		reportMap = new HashMap<Long, DBReport>();
		for (DBReport report : reports) {
			reportMap.put(report.getId(), report);
			// TODO: mmm dirty way to make form:select work!
			report.setDbID1(report.getDb1().getId().toString());
			report.setDbID2(report.getDb2().getId().toString());
		}
	}

	/**
	 * @return the resultView
	 */
	public ResultView getResultView() {
		return resultView;
	}

	/**
	 * @param resultView
	 *            the resultView to set
	 */
	public void setResultView(ResultView resultView) {
		this.resultView = resultView;
	}

	/**
	 * @return the isDeleteReport
	 */
	public Boolean getIsDeleteReport() {
		return isDeleteReport;
	}

	/**
	 * @param isDeleteReport
	 *            the isDeleteReport to set
	 */
	public void setIsDeleteReport(Boolean isDeleteReport) {
		this.isDeleteReport = isDeleteReport;
	}

	/**
	 * @return the isDeleteConfig
	 */
	public Boolean getIsDeleteConfig() {
		return isDeleteConfig;
	}

	/**
	 * @param isDeleteConfig
	 *            the isDeleteConfig to set
	 */
	public void setIsDeleteConfig(Boolean isDeleteConfig) {
		this.isDeleteConfig = isDeleteConfig;
	}

	/**
	 * @return the configMap
	 */
	public Map<Long, DBConfiguration> getConfigMap() {
		return configMap;
	}

	/**
	 * @param configMap
	 *            the configMap to set
	 */
	public void setConfigMap(Map<Long, DBConfiguration> configMap) {
		this.configMap = configMap;
	}

	/**
	 * @return the facadeConfig
	 */
	public DBConfigurationFacade getFacadeConfig() {
		return facadeConfig;
	}

	/**
	 * @param facadeConfig
	 *            the facadeConfig to set
	 */
	public void setFacadeConfig(DBConfigurationFacade facadeConfig) {
		this.facadeConfig = facadeConfig;
	}

	/**
	 * @return the facadeReport
	 */
	public DBReportFacade getFacadeReport() {
		return facadeReport;
	}

	/**
	 * @param facadeReport
	 *            the facadeReport to set
	 */
	public void setFacadeReport(DBReportFacade facadeReport) {
		this.facadeReport = facadeReport;
	}

	/**
	 * @return the configID
	 */
	public String getConfigID() {
		return configID;
	}

	/**
	 * @param configID
	 *            the configID to set
	 */
	public void setConfigID(String configID) {
		this.configID = configID;
	}

	/**
	 * @return the reportID
	 */
	public String getReportID() {
		return reportID;
	}

	/**
	 * @param reportID
	 *            the reportID to set
	 */
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	/**
	 * @return the reportMap
	 */
	public Map<Long, DBReport> getReportMap() {
		return reportMap;
	}

	/**
	 * @param reportMap
	 *            the reportMap to set
	 */
	public void setReportMap(Map<Long, DBReport> reportMap) {
		this.reportMap = reportMap;
	}

	/**
	 * @return the dbs
	 */
	public List<DBConfiguration> getDbs() {
		return dbs;
	}

	/**
	 * @param dbs
	 *            the dbs to set
	 */
	public void setDbs(List<DBConfiguration> dbs) {
		this.dbs = dbs;
	}

	/**
	 * @return the reports
	 */
	public List<DBReport> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(List<DBReport> reports) {
		this.reports = reports;
	}

	/**
	 * @return the report
	 */
	public DBReport getReport() {
		return report;
	}

	/**
	 * @param report
	 *            the report to set
	 */
	public void setReport(DBReport report) {
		this.report = report;
	}

	// /**
	// * @return the result
	// */
	// public QueryComparator getQueryComparator() {
	// return queryComparator;
	// }
	//
	// /**
	// * @param result
	// * the result to set
	// */
	// public void setQueryComparator(QueryComparator queryComparator) {
	// this.queryComparator = queryComparator;
	// }

//	/**
//	 * @return the reportManager
//	 */
//	public ReportManager getReportManager() {
//		return reportManager;
//	}
//
//	/**
//	 * @param reportManager
//	 *            the reportManager to set
//	 */
//	public void setReportManager(ReportManager reportManager) {
//		this.reportManager = reportManager;
//	}

	/**
	 * @return the config
	 */
	public DBConfiguration getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(DBConfiguration config) {
		this.config = config;
	}

	/**
	 * @return the exceptionTrace
	 */
	public String getExceptionTrace() {
		return exceptionTrace;
	}

	/**
	 * @param exceptionTrace
	 *            the exceptionTrace to set
	 */
	public void setExceptionTrace(String exceptionTrace) {
		this.exceptionTrace = exceptionTrace;
	}

	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param exceptionMessage
	 *            the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
