package MMA;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Comparable<Person> {
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int person_id;
	
	@Column(name="name", length=30)
	private String name;
	
	@Column(name="last_name", length=30)
	private String lastName;
	
	@Column(name="age", nullable = false)
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ethnicity_id")
	private Ethnicity ethnicity;
	
	@Column(name="experience_career", nullable = true)
	private Integer experienceCareer;
	
	@Column(name="min_age", nullable = false)
	public final static int minAge = 18;
	
	@Column(name="max_age", nullable = false)
	public final static int maxAge = 47;
	
	private static TreeSet<Person> personExtent = new TreeSet<>(Comparator.reverseOrder());

	
	public Person(String name, String lastName, Integer experienceCareer, int age, Ethnicity ethnicity) {
		this.setName(name);
		this.setAge(age);
		this.setLastName(lastName);
		this.setExperienceCareer(experienceCareer);
		this.setEthnicity(ethnicity);
		this.personExtent.add(this);
	}
	

	
	public Person() {
		// TODO Auto-generated constructor stub
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

	//Custom constraint
	public void setName(String name) {
		  if(name == null)
	          throw new RuntimeException("Given parameter(name) is null");
//	      if(name.matches("[A-Z][a-z]*"))
//	          throw new RuntimeException("Given name is not valid");
	      else
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	//dedicated setter unique constraint
	public void setLastName(String lastName) {
		if(lastName==null)
			throw new RuntimeException("Given parameter(sponsor) is null");
		else {
			if(this.personExtent.stream().anyMatch(p->p.getLastName().equals(lastName)))
				throw new  RuntimeException("Last Name should be unique");
			else
		this.lastName = lastName;
		}
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
			return new TreeSet<>(personExtent);
		}
		
		public int getAge() {
			return age;
		}

		//attributes constraints
		public void setAge(int age) {
			if(age<minAge || age>maxAge )
				throw new IllegalArgumentException(String.format("Age can't be less than (%s) and more than %s", minAge,maxAge));
			else {
				if(age < this.age) 
					throw new IllegalArgumentException("Age never decreases.");
				else
			this.age = age;
			}
		}
		
		public int getId() {
			return person_id;
		}



		public void setId(int person_id) {
			this.person_id = person_id;
		}

	
}
