package org.product.warranty.pro.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ProUserRepository")
public class ProUserRepositoryImpl implements ProUserRepository{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void createProUser(ProUser user) throws WPDataAccessException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(user);
		} catch (HibernateException e) {
			throw new WPDataAccessException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProUser> getAllUsers() throws WPDataAccessException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria= session.createCriteria(ProUser.class);
			return criteria.list();
		} catch (HibernateException e) {
			throw new WPDataAccessException(e);
		}
	}
}
