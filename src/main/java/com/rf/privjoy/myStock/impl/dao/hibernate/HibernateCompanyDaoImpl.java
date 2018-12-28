package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.CompanyDao;
import com.rf.privjoy.myStock.impl.persistent.Company;

@Component
public class HibernateCompanyDaoImpl extends HibernateGenericDao<Company, Long> implements CompanyDao {

	@Override
	public Company getCompanyByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(Company.class.getName()).append(" WHERE name = (:name)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("name", name);
		Company company = (Company) query.getSingleResult();
		endTransaction();
		return company;
	}

}
