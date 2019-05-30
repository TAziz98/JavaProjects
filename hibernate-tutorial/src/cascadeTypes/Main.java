package cascadeTypes;


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
  
	//	  Session session = factory.getCurrentSession();
	      Session session = factory.openSession();
		  try{
			
//			    Student student = new Student();
//				student.setName("Aziz");
//				
//				  Student student2 = new Student();
//					student2.setName("Ikrom");
//				
//				Student_Detail student_detail = new Student_Detail();
//				
//				student_detail.setPhone_number("907325367");
//				student2.setStudent_detail(student_detail);
//				student_detail.setStudent(student2);
//				
//				Student_Detail student_detail2 = new Student_Detail();
//				student_detail2.setStudent(student);
//				student_detail2.setPhone_number("907325369");
//				student.setStudent_detail(student_detail2);
//				
//				session.beginTransaction();
//				session.persist(student);
//				session.persist(student2);
//				session.getTransaction().commit();
//				System.out.println("row added");
				  Student student5 = session.get(Student.class,3);
				  if(student5!=null)
				  {
					  session.beginTransaction();
					  session.delete(student5);
					  session.getTransaction().commit();
					  
				  }
		  }
		  finally {
         	 session.close();
			factory.close();
		}
			
	
	}

}
