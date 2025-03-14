package net.rickcee.jdcompare.core;

import java.util.List;
import java.util.Map;

import net.rickcee.jdcompare.model.DBConfiguration;
import net.rickcee.jdcompare.model.DBReport;


public class ReportManager {
	private List<DBReport> reports;
	private List<DBConfiguration> configs;
	private Map<String, DBReport> reportMap;
	private Map<String, DBConfiguration> configMap;

	/**
	 * @return the configs
	 */
	public List<DBConfiguration> getConfigs() {
		return configs;
	}

	/**
	 * @param configs
	 *            the configs to set
	 */
	public void setConfigs(List<DBConfiguration> configs) {
		this.configs = configs;
	}

	/**
	 * @return the configMap
	 */
	public Map<String, DBConfiguration> getConfigMap() {
		return configMap;
	}

	/**
	 * @param configMap
	 *            the configMap to set
	 */
	public void setConfigMap(Map<String, DBConfiguration> configMap) {
		this.configMap = configMap;
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
	 * @return the reportMap
	 */
	public Map<String, DBReport> getReportMap() {
		return reportMap;
	}

	/**
	 * @param reportMap
	 *            the reportMap to set
	 */
	public void setReportMap(Map<String, DBReport> reportMap) {
		this.reportMap = reportMap;
	}

}
