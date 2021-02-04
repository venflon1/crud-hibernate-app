package com.roberto.hibernate.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.roberto.hibernate.model.HbStudent;
import com.roberto.hibernate.util.HibenateUtil;

public class HbStudentDao {

	private HibenateUtil hbUtil = HibenateUtil.getInstance();

	public HbStudent selectHbStudent(long id) {
		SessionFactory sessionFactory = hbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// RETRIEVE OPERATION
		HbStudent studentFetched = session.get(HbStudent.class, id);

		session.close();
		return studentFetched;
	}

	public List<HbStudent> searchStudentLikeName(String name) {
		SessionFactory sessionFactory = hbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hqlStatement = "SELECT st FROM HbStudent st WHERE st.name LIKE :nameParam ";
		Query<HbStudent> query = session.createQuery(hqlStatement, HbStudent.class);
		query.setParameter("nameParam", "%" + name + "%");
		
		List<HbStudent> resultList = query.getResultList();

		return resultList;
	}

	public HbStudent insertHbStudent(HbStudent hbStudentToInsert) throws CloneNotSupportedException {
		SessionFactory sessionFactory = hbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		HbStudent studentInserted = (HbStudent) hbStudentToInsert.clone();
		Long id = null;
		try {
			// INSERT OPERATION
			Transaction trx = session.getTransaction();
			trx.begin();
			id = (Long) session.save(hbStudentToInsert);
			studentInserted.setIdentifier(id);
			trx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return studentInserted;
	}

	public HbStudent updateHbStudent(HbStudent hbStudentToUpdate) {
		SessionFactory sessionFactory = hbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		HbStudent oldStudent = null;
		try {
			Transaction trx = session.beginTransaction();
			oldStudent = session.get(HbStudent.class, hbStudentToUpdate.getIdentifier());
			oldStudent.setName(hbStudentToUpdate.getName() != null? hbStudentToUpdate.getName(): oldStudent.getName());
			oldStudent.setSurname(hbStudentToUpdate.getSurname() != null? hbStudentToUpdate.getSurname(): oldStudent.getSurname());
			oldStudent.setEmailAddress(hbStudentToUpdate.getEmailAddress() != null? hbStudentToUpdate.getEmailAddress(): oldStudent.getEmailAddress());
			trx.commit();
		} catch (Exception e) {
			hbStudentToUpdate = null;
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return oldStudent;
	}

	public void deleteHbStudent(long id) {
		SessionFactory sessionFactory = hbUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		HbStudent studentToDel = session.get(HbStudent.class, id);
		// DELETE OPERATION
		session.delete(studentToDel);
		trx.commit();
	}

}
