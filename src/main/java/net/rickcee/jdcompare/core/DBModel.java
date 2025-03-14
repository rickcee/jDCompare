package net.rickcee.jdcompare.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.rickcee.jdcompare.model.DBConfiguration;
import net.rickcee.jdcompare.model.DBReport;
import net.rickcee.jdcompare.repository.DBConfigurationRepository;
import net.rickcee.jdcompare.repository.DBReportRepository;
import net.rickcee.jdcompare.view.ResultView;

@Repository("model")
@Data
@Slf4j
public class DBModel {
//	@Autowired
//	private ReportManager reportManager;
//	@Autowired
//	private DBConfigurationFacade facadeConfig;
//	@Autowired
//	private DBReportFacade facadeReport;

	@Autowired
	private DBConfigurationRepository configRepo;
	@Autowired
	private DBReportRepository reportRepo;
	
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
		log.info("=======================================");
		log.info("= Creating DBModel...");
		log.info("=======================================");
	}
 	/**
	 * @throws Exception
	 */
	public void reloadData() throws Exception {
		dbs = configRepo.findAll();
		configMap = new HashMap<Long, DBConfiguration>();
		for (DBConfiguration config : dbs) {
			configMap.put(config.getId(), config);
		}

		reports = reportRepo.findAll();
		reportMap = new HashMap<Long, DBReport>();
		for (DBReport report : reports) {
			reportMap.put(report.getId(), report);
			// TODO: mmm dirty way to make form:select work!
			report.setDbID1(report.getDb1().getId().toString());
			report.setDbID2(report.getDb2().getId().toString());
		}
	}


}
