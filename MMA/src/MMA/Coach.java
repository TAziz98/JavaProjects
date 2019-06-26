package MMA;

import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;


@Entity
@Table(name="COACH")
public class Coach extends Person implements ICoach {
	
	@Column(name="salary_as_coach", nullable = true)
	private Integer salaryAsCoach;
	
	@Column(name="experience_as_coach", nullable = false)
	private int experienceAsCoach;
	
	private String coachNick;
	
	
	public Coach(String nick, String name, String lastName, Integer coachExperience, int age, Ethnicity ethnicity,Integer salaryAsCoach, Integer experienceAsCoach ) {
		// TODO Auto-generated constructor stub
		super(name, lastName, coachExperience, age, ethnicity);
		this.setSalaryAsCoach(salaryAsCoach);
		this.setCoachNick(nick);
		this.setExperienceAsCoach(experienceAsCoach);
	}
	
	public Coach() {
		
	}

	 public static void removeCoach(int id) {
		  
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  try {
				session.beginTransaction();
				 Query query=session.createQuery("delete from Coach  where person_id=:id");  
			      query.setParameter("id",id); 
				 int deleted = query.executeUpdate();
			    System.out.println("deleted");
		  }
			   finally {
			   session.close();		
			}
	  }

	@Override
	public void setSalaryAsCoach(Integer salaryAsCoach) {
		// TODO Auto-generated method stub
		if(salaryAsCoach==null)
			throw new RuntimeException("Given parameter(salary) is null");
		else
			this.salaryAsCoach=salaryAsCoach;	
	}

	@Override
	public Integer getSalaryAsCoach() {
		// TODO Auto-generated method stub
		return salaryAsCoach;
	}
	
	@Override
	public int getexperienceAsCoach() {
		// TODO Auto-generated method stub
		return experienceAsCoach;
	}
	
	@Override
	public void setExperienceAsCoach(int experienceAsCoach) {
		if(experienceAsCoach<0)
			throw new IllegalArgumentException("can't be negative");
		else
			this.experienceAsCoach=experienceAsCoach;
	}


	@Override
	public Integer getSalary() {
		// TODO Auto-generated method stub
		return getSalaryAsCoach();
	}


	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.getAge()-o.getAge();
	}
	
	public String getCoachNick() {
		return coachNick;
	}

	public void setCoachNick(String coachNick) {
		if(coachNick==null)
			throw new RuntimeException("Given parameter(salary) is null");
		else
		this.coachNick = coachNick;
	}


	
		

}
