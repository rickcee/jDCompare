/**
 * 
 */
package net.rickcee.jdcompare.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.rickcee.jdcompare.core.DBModel;
import net.rickcee.jdcompare.core.LogUtils;
import net.rickcee.jdcompare.core.QueryComparator;
import net.rickcee.jdcompare.model.DBConfiguration;
import net.rickcee.jdcompare.model.DBReport;
import net.rickcee.jdcompare.repository.DBConfigurationRepository;
import net.rickcee.jdcompare.repository.DBReportRepository;
import net.rickcee.jdcompare.validator.DBConfigurationValidator;
import net.rickcee.jdcompare.validator.DBReportValidator;
import net.rickcee.jdcompare.view.ResultView;

/**
 * @author catalrc
 * 
 */
@Controller
@Slf4j
public class InitController {
	@Autowired
	private DBModel model;

	@Autowired
	DBConfigurationValidator configValidator;

	@Autowired
	DBReportValidator reportValidator;
	
	@Autowired
	private DBConfigurationRepository configRepo;
	@Autowired
	private DBReportRepository reportRepo;
	
	/**
	 * @return A new instance of the form
	 * @throws Exception
	 */
	@ModelAttribute("model")
	public DBModel formObject() throws Exception {
		model.reloadData();
		log.info("Model: " + model);
		return model;
	}

	/**
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/Init", method = RequestMethod.GET)
	public ModelAndView show(@ModelAttribute("model") DBModel model) {
		return new ModelAndView("DBInit", "model", model);
	}

	@RequestMapping("/SQLQuery")
	public ModelAndView handleRequest(@ModelAttribute("model") DBModel model) {
		log.info("Model: " + model);
		ModelAndView mav;

		try {
			// Load the corresponding report
			String reportID = model.getReportID();
			if (reportID != null) {
				model.setReport(model.getReportMap().get(Long.parseLong(reportID)));
			}

			QueryComparator mc = new QueryComparator();
			mc.executeReport(model);
			mc.consolidateResults();

			// Create view object
			ResultView finalResultView = mc.createViewObject();
			finalResultView.setDbAlias1(model.getReport().getDb1().getDbAlias());
			finalResultView.setDbAlias2(model.getReport().getDb2().getDbAlias());
			model.setResultView(finalResultView);

			mav = new ModelAndView("DBSQLGenerator");
		} catch (NumberFormatException e) {
			mav = LogUtils.setExceptionResult(model, e);
		} catch (ClassNotFoundException e) {
			mav = LogUtils.setExceptionResult(model, e);
		} catch (SQLException e) {
			mav = LogUtils.setExceptionResult(model, e);
		}

		mav.addObject("MODEL", model);
		return mav;
	}

	@RequestMapping("/Export")
	public ModelAndView handleRequest(@ModelAttribute("model") DBModel model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav;
		try {
			// Load the corresponding report
			String reportID = model.getReportID();
			if (reportID != null) {
				model.setReport(model.getReportMap().get(Long.parseLong(reportID)));
			}

			QueryComparator mc = new QueryComparator();
			mc.executeReport(model);
			mc.consolidateResults();

			// Create view object
			ResultView finalResultView = mc.createViewObject();
			finalResultView.setDbAlias1(model.getReport().getDb1().getDbAlias());
			finalResultView.setDbAlias2(model.getReport().getDb2().getDbAlias());
			model.setResultView(finalResultView);

			response.setHeader("Content-Disposition", "filename=exportReport.xls");
			response.getOutputStream().write(ExcelGenerator.generate(finalResultView));
			response.setContentType("application/vnd.ms-excel");

			return null; // Everything's OK...
		} catch (Exception e) {
			mav = LogUtils.setExceptionResult(model, e);
		}

		// If we reach this code, it means that there was an error.
		mav.addObject("MODEL", model);
		return mav;
	}

	/**
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/AddNewConnection", method = RequestMethod.GET)
	public ModelAndView AddNewConnection(@ModelAttribute("model") DBModel model) {
		String configID = model.getConfigID();
		if (configID != null && !configID.equals("")) {
			model.setConfig(model.getConfigMap().get(Long.parseLong(configID)));
		} else {
			model.setConfig(new DBConfiguration());
		}
		return new ModelAndView("AddNewConnection", "model", model);
	}

	/**
	 * Validates and submit the form
	 * 
	 * @param report
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AddNewConnection", method = RequestMethod.POST)
	public ModelAndView AddNewConnection(@ModelAttribute("model") DBModel model, BindingResult bindingResult)
			throws Exception {
		ModelAndView mav;

		try {
			if (model.getIsDeleteConfig()) {
				// facade.delete(model.getConfig());
				log.info("Delete config: " + model.getConfig());
			} else {
				configValidator.validate(model.getConfig(), bindingResult);
				if (bindingResult.hasErrors()) {
					return new ModelAndView("AddNewConnection");
				}
				//configFacade.add(model.getConfig());
				configRepo.save(model.getConfig());
			}
			model.reloadData();
			mav = new ModelAndView("DBInit");
		} catch (Exception e) {
			mav = LogUtils.setExceptionResult(model, e);
		}
		mav.addObject("MODEL", model);
		return mav;
	}

	/**
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/AddNewReport", method = RequestMethod.GET)
	public ModelAndView AddNewReport(@ModelAttribute("model") DBModel model) {
		String reportID = model.getReportID();
		if (reportID != null && !reportID.equals("")) {
			model.setReport(model.getReportMap().get(Long.parseLong(reportID)));
		} else {
			model.setReport(new DBReport());
		}
		return new ModelAndView("AddNewReport", "model", model);
	}

	/**
	 * Validates and submit the form
	 * 
	 * @param report
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AddNewReport", method = RequestMethod.POST)
	public ModelAndView AddNewReport(@ModelAttribute("model") DBModel model, BindingResult bindingResult)
			throws Exception {
		ModelAndView mav;
		try {
			if (model.getIsDeleteReport()) {
				//reportFacade.delete(model.getReport());
				reportRepo.delete(model.getReport());
				log.info("Delete report: " + model.getReportID());
			} else {
				reportValidator.validate(model.getReport(), bindingResult);
				if (bindingResult.hasErrors()) {
					return new ModelAndView("AddNewReport");
				}

				// Set the datasources
				model.getReport().setDb1(configRepo.findById(Long.parseLong(model.getReport().getDbID1())).get());
				model.getReport().setDb2(configRepo.findById(Long.parseLong(model.getReport().getDbID2())).get());

				//reportFacade.add(model.getReport());
				reportRepo.save(model.getReport());
			}
			model.reloadData();
			mav = new ModelAndView("DBInit");
		} catch (Exception e) {
			mav = LogUtils.setExceptionResult(model, e);
		}
		mav.addObject("MODEL", model);
		return mav;
	}
}
