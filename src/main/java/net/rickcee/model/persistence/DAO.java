package net.rickcee.model.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public class DAO { //extends HibernateDaoSupport {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//	@Autowired
//	public void setHTemplate(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
//		super.setSessionFactory(sessionFactory);
//	}

	/**
	 * @param hql
	 * @return
	 */
	public final Collection<?> getQuery(String hql) {
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @param obj
	 */
	public final void remove(final Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("The Object cannot be null");
		}

		sessionFactory.getCurrentSession().delete(obj);
	}

	/**
	 * @param obj
	 */
	public final void save(final Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("The Object cannot be null");
		}

		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	public final Object get(Class<?> clazz, final long id) {
		return sessionFactory.getCurrentSession().get(clazz, new Long(id));
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	public final Object get(Class<?> clazz, final String id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}

	/**
	 * @param clazz
	 * @return
	 */
	public final Collection<?> getAll(Class<?> clazz) {
		System.out.println("Current Session Factory: " + sessionFactory);
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("Current Session: " + currentSession);
		return currentSession.createQuery("from " + clazz.getCanonicalName() + " order by id").getResultList();
	}

	/**
	 * @param clazz
	 */
	public final void removeAll(Class<?> clazz) {
		sessionFactory.getCurrentSession().createQuery("delete from " + clazz.getCanonicalName()).executeUpdate();
	}
}