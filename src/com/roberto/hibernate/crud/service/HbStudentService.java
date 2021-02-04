package com.roberto.hibernate.crud.service;

import java.util.List;
import java.util.logging.Logger;

import com.roberto.hibernate.crud.dao.HbStudentDao;
import com.roberto.hibernate.model.HbStudent;

public class HbStudentService {

	private static final Logger log = Logger.getLogger(HbStudent.class.getSimpleName());
	
	private HbStudentDao hbStudentDao = new HbStudentDao();
	
	public HbStudent getStudent(long studentId) {
		log.info("getStudent - START - studentId=" + studentId);
		HbStudent student = this.hbStudentDao.selectHbStudent(studentId);

		log.info("getStudent - END - result=" + student);
		return student;
	}
	
	public List<HbStudent> searchStudentLikeName(String name){
		log.info("searchStudentLikeName - START - name=" + name);
		List<HbStudent> result = this.hbStudentDao.searchStudentLikeName(name);

		log.info("searchStudentLikeName - END");
		return result;
	}
	
	public HbStudent insertStudent(HbStudent studentToInsert) {
		log.info("insertStudent - START - studentToInsert=" + studentToInsert);
		HbStudent studentInserted = null;
		try {
			studentInserted = this.hbStudentDao.insertHbStudent(studentToInsert);
		} catch (CloneNotSupportedException e) {
			System.out.println("clone not supported...");
			studentInserted = hbStudentDao.selectHbStudent(studentInserted.getIdentifier());
		}
		
		log.info("insertStudent - END - result=" + studentInserted);
		return studentInserted;
	}
	
	public HbStudent updateStudent(HbStudent studentToUpdate) {
		log.info("updateStudent - START - studentToUpdate=" + studentToUpdate);
		this.hbStudentDao.updateHbStudent(studentToUpdate);
		HbStudent studentUpdated = this.hbStudentDao.selectHbStudent(studentToUpdate.getIdentifier());
		
		log.info("updateStudent - END - result=" + studentUpdated);
		return studentUpdated;
	}
	
	public void deleteStudent(long idStudentToDelete) {
		log.info("deleteStudent - START - idStudentToDelete=" + idStudentToDelete);
		this.hbStudentDao.deleteHbStudent(idStudentToDelete);
		log.info("deleteStudent - END");
	}
	
}
