package net.rickcee.model.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.rickcee.model.DBConfiguration;
import net.rickcee.model.persistence.DAO;

@Repository
@Transactional
public class DBConfigurationFacade {
	@Autowired
	private DAO dao;

	/**
	 * @param config
	 * @throws Exception
	 */
	public void add(DBConfiguration config) throws Exception {
		dao.save(config);
	}

	/**
	 * @param config
	 * @throws Exception
	 */
	public void modify(DBConfiguration config) throws Exception {
		dao.save(config);
	}

	/**
	 * @param config
	 * @throws Exception
	 */
	public void delete(DBConfiguration config) throws Exception {
		dao.remove(config);
	}

	/**
	 * @param config
	 * @throws Exception
	 */
	public DBConfiguration get(Long id) throws Exception {
		return (DBConfiguration) dao.get(DBConfiguration.class, id);
	}

	/**
	 * @param config
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<DBConfiguration> getAll() throws Exception {
		List<DBConfiguration> result = new ArrayList<DBConfiguration>();
		Collection<?> c = dao.getAll(DBConfiguration.class);
		result.addAll((Collection<? extends DBConfiguration>) c);
		return result;
	}

//	/**
//	 * @param dao
//	 *            the dao to set
//	 */
//	public void setDao(DAO dao) {
//		this.dao = dao;
//	}

}
