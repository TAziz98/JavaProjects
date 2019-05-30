package enumMany.copy;


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
				
				Address student_address = new Address();
				student_address.setAddress_detail("Bartycka 16A");
				
				Address student_address2 = new Address();
				student_address2.setAddress_detail("Walicow 20");
			
				student1.getStudentAddress().add(student_address);
				student1.getStudentAddress().add(student_address2);
				
				session.beginTransaction();
				
				session.save(student1);
				session.getTransaction().commit();
				System.out.println("row added");
		  }
		  finally {
			  session.close();
			factory.close();
		}
			
	
	}

}
