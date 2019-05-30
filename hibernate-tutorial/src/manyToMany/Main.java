package manyToMany;


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
		               .addAnnotatedClass(Student.class)
		               .buildSessionFactory();
  
		  Session session = factory.getCurrentSession();
		 
		  try {

			    Student student1 = new Student();
				student1.setName("Aziz");

			    Student student2 = new Student();
				student2.setName("Ikrom");
				
				Address student_address = new Address();
				student_address.setAddress_detail("Bartycka 16A");
				
				Address student_address2 = new Address();
				student_address2.setAddress_detail("Walicow 20");

				Address student_address3 = new Address();
				student_address3.setAddress_detail("Florianska 3A");
				
				student1.getStudentAddress().add(student_address);
				student1.getStudentAddress().add(student_address2);
				student1.getStudentAddress().add(student_address3);
				
				student2.getStudentAddress().add(student_address);
				student2.getStudentAddress().add(student_address2);
				student2.getStudentAddress().add(student_address3);
				
				student_address.getStudentList().add(student1);
				student_address.getStudentList().add(student2);
				
				student_address2.getStudentList().add(student1);
				student_address2.getStudentList().add(student2);
				
				
				student_address3.getStudentList().add(student1);
				student_address3.getStudentList().add(student2);
				
				session.beginTransaction();
				
				session.save(student1);
				session.save(student2);
				
				session.getTransaction().commit();
				System.out.println("row added");
		  }
		  finally {
			  session.close();
			factory.close();
		}
			
	
	}

}
