package com.roberto.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.roberto.hibernate.model.HbStudent;

public class HibenateUtil {

	private static HibenateUtil _instance = null;
	private SessionFactory sessionFactory = null; 
	
	private HibenateUtil() {
		// create session factory from 'hibernate.cfg.xml'
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(HbStudent.class)
										.buildSessionFactory();

		this.sessionFactory = factory;
	}
	
	public static HibenateUtil getInstance() {
		if(_instance == null) {
			_instance = new HibenateUtil();
		}
		
		return _instance;
	}
	
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
