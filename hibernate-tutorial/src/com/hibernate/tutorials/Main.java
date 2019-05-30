package com.hibernate.tutorials;


import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.Configuration;

import java.util.Date;

import org.hibernate.*;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			SessionFactory factory = new Configuration()
		               .configure("hibernate.cfg.xml")
		               .addAnnotatedClass(Student_Info.class)
		               .buildSessionFactory();
  
		  Session session = factory.getCurrentSession();
		 
		  try {

			  Student_Info student = new Student_Info();
				student.setName("Aziz");
				student.setRollNo(1); 
				student.setBirth_date(new Date());
				session.beginTransaction();
				session.save(student);
				session.getTransaction().commit();
				System.out.println("row added");
		  }
		  finally {
			  session.close();
			factory.close();
		}
			
	
	}

}
