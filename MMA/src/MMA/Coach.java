package MMA;

import java.util.stream.Collectors;

public class Coach extends Person implements ICoach {
	
	private Integer salaryAsCoach;
	private int experienceAsCoach;
	
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

	
		

}
