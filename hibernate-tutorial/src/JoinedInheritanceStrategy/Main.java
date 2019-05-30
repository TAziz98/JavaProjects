package JoinedInheritanceStrategy;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	
	public static void main(String[] args) {
	
		SessionFactory factory = new Configuration()
	               .configure("hibernate.cfg.xml")
	               .addAnnotatedClass(Student.class)
	               .buildSessionFactory();

		   Session session = factory.getCurrentSession();
			//factory.openSession();
			  try{
		Person person = new Person();
		person.setName("Aziz");
		person.setGender("Male");
		
		
		Employee employee = new Employee();
		employee.setBonus(new BigDecimal("277.389"));
		employee.setDeptName("IT");
		employee.setEmail("s16225@pjwstk.edu.pl"); 
		employee.setGender("Male");
		employee.setName("Ikrom");
		employee.setSalary(7000.8023);
		
		Student student = new Student();
		student.setName("Mumin");
		student.setGender("Male");
		student.setFee(20000.00f);
        student.setSchoolName("TTHS");
        student.setSectionName("12th Std");
        
        session.beginTransaction();
        session.save(person);
        session.save(student);
        session.save(employee);
    	session.getTransaction().commit();
			  
	  }
	  finally {
   	 session.close();
		factory.close();
	}
	}
	
	
}
