package MMA;

import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="COACH")
public class Coach extends Person implements ICoach {
	
	private Integer salaryAsCoach;
	private int experienceAsCoach;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int coach_id;
//	
	
	public Coach(String name, String lastName, Integer coachExperience, int age, Ethnicity ethnicity) {
		// TODO Auto-generated constructor stub
		super(name, lastName, coachExperience, age, ethnicity);
		this.setSalaryAsCoach(salaryAsCoach);
		this.setExperienceAsCoach(experienceAsCoach);
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

	
		

}
