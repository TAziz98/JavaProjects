package MMA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import util.HibernateUtil;

//Ok

@Entity
@Table(name="FIGHTER")
public class Fighter extends Person implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int fighter_id;
	
	
	
//complex Attribute
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="statistics_id")
	private Statistics statistics;

	//class extent initialization
		public Fighter(String nickName, String officialname, String lastName,int experienceCareer, int age, Statistics statistics, Ethnicity ethnicity) {
			super(officialname, lastName, experienceCareer, age, ethnicity);
			this.setStatistics(statistics);
			this.setNickName(nickName);
			fighterExtent.put(this.nickName, this);
		}
		
		public Fighter() {
			super();
			this.nickName="default";
		}

	//-------------------->Binary Association
			
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="FIGHTER_SPONSOR", joinColumns = @JoinColumn(name="fighter_id"), inverseJoinColumns = @JoinColumn(name="sponsorshipAssociation_id"))
	private Set<SponsorshipAssociation> sponsors = new HashSet<>();
	
	public void acceptSponsorship(SponsorshipAssociation sponsor) {
		if(sponsor == null)
			throw new RuntimeException("Given parameter(sponsor) is null");
		else {
			if(sponsors.contains(sponsor))
				throw new RuntimeException("Fighter is already sponsored by"+sponsor.getAssociationName());
			else {
				sponsors.add(sponsor);
				if(!sponsor.getSponsoredFighters().contains(this)) 
				    sponsor.sponsorAFighter(this);
			}
			
		   
	   
		}
	}
	
	public void refuseSponsorship(SponsorshipAssociation sponsor) {
		if(sponsor == null)
			throw new RuntimeException("Given parameter(sponsor) is null");
		else {
			if(!sponsors.contains(sponsor)) 
				throw new RuntimeException("Fighter is not sponsored by"+sponsor.getAssociationName());
			else
				sponsors.remove(sponsor);
			if(specialMakeSponsors.contains(sponsor))
				specialMakeSponsors.remove(sponsor);
			if(sponsor.getSponsoredFighters().contains(this))
				sponsor.unsponsorAFighter(this);
		}
	}
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="SPECIALFIGHTER_SPECIALSPONSOR", joinColumns = @JoinColumn(name="fighter_id"), inverseJoinColumns = @JoinColumn(name="sponsorshipAssociation_id"))
	private Set<SponsorshipAssociation> specialMakeSponsors = new HashSet<>(); 
	
	public void  acceptSpecialSponsorShip(SponsorshipAssociation sponsor) {
		if(sponsor==null) {
			throw new RuntimeException("null value ");
		}
		else {
			if(sponsors.contains(sponsor)) {
				if(!specialMakeSponsors.contains(sponsor)) {
					specialMakeSponsors.add(sponsor);
					if(!sponsor.getSpecialSponsoredFighters().contains(this)) {
					sponsor.sponsorSpecialFighter(this); 
				}
				
				}else {
					throw new RuntimeException("This sponsor does not sponsor this fighter");
				}
			}
		}
	}
	
	public void refuseSpecialSponsorShip(SponsorshipAssociation sponsor) {
		if(sponsor==null) {
			throw new RuntimeException("null value ");
		}
		else {
				if(!specialMakeSponsors.contains(sponsor)) {
			      specialMakeSponsors.remove(sponsor);
			      sponsor.unSponsorSpecialFighter(this);
				}
				else {
					throw new RuntimeException("This sponsor does not sponsor this fighter");
				}
		}
	}
	
	public Set<SponsorshipAssociation> getSponsors() {
		return new HashSet<>(sponsors);
	}
	
	
	public Set<SponsorshipAssociation> getSpecialMakeSponsors() {
		return new HashSet<>(specialMakeSponsors);
	}


  //------------------------>
	
	//------------------>Qualified Association
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Team team;
	
	@Column(name="nick_name", length=30)
	private String nickName;
	
	public void setTeam(Team team) {
		if(this.association==null) {
		if(team == null){
			throw new RuntimeException("Given parameter(team) is null");
		}else{		
			if(team.getFighters().containsKey(this.nickName)) 
				 this.team = team;   
			else 
				team.signFighter(this);
						
		}
		}else throw new RuntimeException("Fighter already in the Association");
		
	}
	
	
	public void refuceTeam(Team team) {
		if(this.association==null) {
		if(team == null)
			throw new RuntimeException("Given parameter(team) is null");
		else {
		this.team = null;
		if(team.getFighters().containsKey(this.nickName)) {
			team.unsignFighter(this);	
		}
		}
		}else throw new RuntimeException("Fighter already in the Association");
	}
	
	public Team getTeam() {
	if(this.association!=null) 
	throw new RuntimeException("Fighter already in the Association");
	else
	return team;
	}

	public void setNickName(String nickName) {
		if (nickName == null)
			throw new NullPointerException("NickName is not applied");
		else
			this.nickName = nickName;
	}	

	public String getNickName() {
		return nickName;
	}
	
	
	//------------------------->
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="association_id")
	private Association association;
	
	public void setAssociation(Association association) {
		if(this.team==null) {
		if(association == null){
			throw new RuntimeException("Given parameter(association) is null");
		}else{		
			if(association.getFighters().contains(this)) 
				 this.association = association;   
			else 
				association.signFighter(this);
						
		}
		}else throw new RuntimeException("Fighter is already in the Team");
		
	}
	
	public void refuceAssociation(Association associaton) {
		if(this.team==null) {
		if(this.association==null)
			throw new RuntimeException("Given parameter(association) is null");
		else {
		this.association = null;
		if(association.getFighters().contains(this)) {
			association.unsignFighter(this);	
		}
		}
		}else throw new RuntimeException("Fighter is already in the Team");
	}
	
	public Association getAssociation() {
		if(this.team!=null) 
			 throw new RuntimeException("Fighter is already in the Team");
			else
		return association;
	}

	
	
	//-------------------->|Association With An Attribute|
	
	@OneToOne(mappedBy = "fighter")
	private Contract contract;
	
	public void setContract(Contract contract) {
		if (contract == null)
			throw new NullPointerException("No such contract");
		if (contract.getFighter() != this)
			throw new IllegalArgumentException("Contract with different fighter");
		else
			this.contract = contract;
	}

	
	public void refuceContract(Contract contract) {
		if (contract == null)
			throw new NullPointerException("null parameter applied");
		if (contract.getFighter() != this)
			throw new IllegalArgumentException("Contract with different fighter");
		else
			 this.contract=null;		
	}
	

	
	public Contract getContract() {
		return contract;
	}
	//------------------------>
	
//class extent
	private static Map<String, Fighter> fighterExtent = new HashMap<String, Fighter>();

//returning copy of an array
	public static Map<String, Fighter> getFighterExtent() {
		return new HashMap<>(fighterExtent);
	}

//class Method
	public static String ViewRecordByNickName(String nickName) {
		if (nickName == null)
			throw new NullPointerException("NickName is not applied");
		if (!fighterExtent.containsKey(nickName) && fighterExtent.get(nickName) == null)
			throw new IllegalArgumentException("No fighters found");
		else
			return fighterExtent.get(nickName).getStatistics().toString(); // method overriding
	}

//derived attribute
	public Integer getAnnualSalary() {
		 Contract currentContract = null;
		 Integer bonus = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			session.beginTransaction();
			currentContract = session.get(Contract.class, this.getId());
			if(currentContract==null) 
			throw new RuntimeException("Fighter does not have such Contract.");
			if (bonus == null)
			bonus = 0;
		  }
		   finally {
	//	   session.close();
			
		}
		return currentContract.getHonorariumSettledByPromotion()*currentContract.getNumberOfFightsSettledByPromotion()+ bonus;
	
//		Integer bonus = contract.getBonus();
//		if (bonus == null)
//			bonus = 0;
//		return contract.getHonorariumSettledByPromotion() * contract.getNumberOfFightsSettledByPromotion() + bonus;
	}



	//method overloading
	public static List<String> ViewTopFighters() {
		return fighterExtent.values().stream()
				.filter(fighter -> fighter.getStatistics().getDivisionRating().equals(DivisionRating.Champion))
				.map(fighter -> fighter.getNickName()).collect(Collectors.toList());
	}

	public static List<String> ViewTopFighters(Division division) {
		List<String> fighters = new ArrayList<String>();
		DivisionRating rating[] = DivisionRating.values();
		for (DivisionRating divisionRating : rating) {
			fighterExtent.values().stream().filter(fighter -> fighter.getStatistics().getDivision().equals(division))
					.filter(fighter -> fighter.getStatistics().getDivisionRating().equals(divisionRating))
					.map(fighter -> fighter.getNickName()).forEach(fighter -> fighters.add(fighter));
		}
		return fighters;

	}

	public void setStatistics(Statistics statistics) {
		if (statistics == null)
			throw new NullPointerException("Statistics is not applied");
		else
			this.statistics = statistics;
	}


	public static void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("hashmap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fighterExtent);
			oos.close();
			fos.close();
			System.out.printf("Serialized HashMap data is saved in hashmap.ser");
		} catch (Exception exception) {
			exception.printStackTrace();
			return;
		}
	}

	public static void deserialize() {
		try {
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream("hashmap.ser"));
			fighterExtent = (Map<String, Fighter>) oos.readObject();
			oos.close();
			System.out.printf("Serialized HashMap data is saved in hashmap.ser");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
	}

/*	public static void  addFighterExtent(Fighter fighter) {
		if (fighter == null)
			throw new NullPointerException("null");
		else
		fighterExtent.put(fighter.nickName, fighter);
	}*/
	

	
public static void  removeFighterExtent(Fighter fighter) {
	if (fighter == null)
		throw new NullPointerException("null");
	if (!fighterExtent.containsKey(fighter.nickName))
		throw new IllegalArgumentException("doesnt contain");
	else
	fighterExtent.put(fighter.nickName, fighter);
}


	public Statistics getStatistics() {
		return statistics;
	}

	@Override
	public Integer getSalary() {
		// TODO Auto-generated method stub
	//	return getAnnualSalary();
		return 0;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.getAge()-o.getAge();
	}

	


}
