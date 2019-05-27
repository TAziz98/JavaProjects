package MMA;

import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COACH_IN_CAREER")
public class CoachInCareer extends Fighter implements ICoach {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int coachInCareer_id;
//	
	
	private Integer salaryAsCoach;
	private int experienceAsCoach;
	
	public CoachInCareer(String nickName, String officialname, String lastName, int experienceCareer,int age,
			Statistics statistics, Integer salaryAsCoach, int experienceAsCoach, Ethnicity ethnicity) {
		super(nickName, officialname, lastName, experienceCareer, age, statistics, ethnicity);
		this.setSalaryAsCoach(salaryAsCoach);
		this.setExperienceAsCoach(experienceAsCoach);
		// TODO Auto-generated constructor stub
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
	
	
	public Integer getTotalSalary() {
		return super.getSalary()+ this.getSalaryAsCoach();
	}
	
	public String identifyCoachsRank() {
		// TODO Auto-generated method stub
		System.out.println(this.getStatistics().getListOfOponents().get(0).getName());
		String coachesRank ="";
		if(this.getStatistics().getListOfOponents()
		.stream()
		.filter(fighter->fighter.getStatistics().getDivisionRating().equals(DivisionRating.Champion))
		.collect(Collectors.toList()).size() >0) {
			coachesRank="TOP_LEVEL_COACH";
		}
		else
			coachesRank="LOW_LEVEL_COACH";
       
		return coachesRank;
	}
	

}
