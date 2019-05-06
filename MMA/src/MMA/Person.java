package MMA;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public abstract class Person {

	private String name;
	private String lastName;
	private int age;
	private Ethnicity ethnicity;
	private Integer experienceCareer;
	
	private static Set<Person> personExtent = new HashSet<>();
	
	public Person(String name, String lastName, Integer experienceCareer, int age, Ethnicity ethnicity) {
		this.setName(name);
		this.setAge(age);
		this.setLastName(lastName);
		this.setExperienceCareer(experienceCareer);
		this.setEthnicity(ethnicity);
		this.personExtent.add(this);
	}
	
	public abstract Integer getSalary();
	
	
	public void setEthnicity(Ethnicity ethnicity) {
		if (ethnicity == null) 
		throw new RuntimeException("Given parameter(ethnicity) is null");
		else {
		if(personExtent.stream().anyMatch(p->p.getEthnicity()==ethnicity))
	    throw new RuntimeException("This ethnicity is alredy other person's");
		else
		this.ethnicity = ethnicity;
		 }
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null) 
			throw new RuntimeException("Given parameter(name) is null");
		else
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName==null)
			throw new RuntimeException("Given parameter(sponsor) is null");
		else
		this.lastName = lastName;
	}

	public int getExperienceCareer() {
		return experienceCareer;
	}

	public void setExperienceCareer(Integer experienceCareer) {
		if(experienceCareer==null)
		 throw new RuntimeException("Given parameter(sponsor) is null");
		else {
		if(experienceCareer<0)
			throw new IllegalArgumentException("can't be negative");
		else
		this.experienceCareer = experienceCareer;
		}
	}
	
	  public Ethnicity getEthnicity() {
			return ethnicity;
		}

		public static Set<Person> getPersonExtent() {
			return new HashSet<>(personExtent);
		}
		
		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			if(age<18)
				throw new IllegalArgumentException("Age can't be less  than 18");
			else
			this.age = age;
		}

	
}
