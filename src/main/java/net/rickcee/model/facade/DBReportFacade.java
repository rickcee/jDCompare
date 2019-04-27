package net.rickcee.model.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.rickcee.model.DBReport;
import net.rickcee.model.persistence.DAO;

@Repository
@Transactional
public class DBReportFacade {
	@Autowired
	private DAO dao;

	/**
	 * @param report
	 * @throws Exception
	 */
	public void add(DBReport report) throws Exception {
		dao.save(report);
	}

	/**
	 * @param report
	 * @throws Exception
	 */
	public void modify(DBReport report) throws Exception {
		dao.save(report);
	}

	/**
	 * @param report
	 * @throws Exception
	 */
	public void delete(DBReport report) throws Exception {
		dao.remove(report);
	}

	/**
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public DBReport get(Long id) throws Exception {
		return (DBReport) dao.get(DBReport.class, id);
	}

	/**
	 * @param report
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DBReport> getAll() throws Exception {
		List<DBReport> result = new ArrayList<DBReport>();
		Collection<?> c = dao.getAll(DBReport.class);
		result.addAll((Collection<? extends DBReport>) c);
		return result;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

}
