package MMA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//Ok
public class Fighter implements Serializable {

	
	private String officialName;
//complex Attribute
	private Statistics statistics;

	

	//-------------------->Binary Association
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
			if(sponsor.getSponsoredFighters().contains(this))
				sponsor.unsponsorAFighter(this);
		}
	}
	
	public Set<SponsorshipAssociation> getSponsors() {
		return new HashSet<>(sponsors);
	}

  //------------------------>
	
	//------------------>Qualified Association 
	private Team team;
	
	private String nickName;
	
	public void setTeam(Team team) {
		if(team == null){
			throw new RuntimeException("Given parameter(team) is null");
		}else{		
			if(team.getFighters().containsKey(this.nickName)) 
				 this.team = team;   
			else 
				team.signFighter(this);
						
		}
		
	}
	
	public void refuceTeam(Team team) {
		this.team = null;
		if(team.getFighters().containsKey(this.nickName)) {
			team.unsignFighter(this);	
		}
	}
	
	public Team getTeam() {
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
	
	
	//-------------------->|Association With An Attribute|
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

//class extent initialization
	public Fighter(String nickName, String officialname, Statistics statistics) {
		this.setStatistics(statistics);
		this.setOfficialName(officialname);
		this.setNickName(nickName);
		fighterExtent.put(this.nickName, this);
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
		Integer bonus = contract.getBonus();
		if (bonus == null)
			bonus = 0;
		return contract.getHonorariumSettledByPromotion() * contract.getNumberOfFightsSettledByPromotion() + bonus;
	}

	public void setOfficialName(String officialName) {
		if (officialName == null)
			throw new NullPointerException("Official Name is not applied");
		else
			this.officialName = officialName;
	}

	//method overloading
	public static List<String> ViewTopFighters() {
		return fighterExtent.values().stream()
				.filter(fighter -> fighter.getStatistics().getDivisionRating().equals(DivisionRating.Champion))
				.map(fighter -> fighter.officialName).collect(Collectors.toList());
	}

	public static List<String> ViewTopFighters(Division division) {
		List<String> fighters = new ArrayList<String>();
		DivisionRating rating[] = DivisionRating.values();
		for (DivisionRating divisionRating : rating) {
			fighterExtent.values().stream().filter(fighter -> fighter.getStatistics().getDivision().equals(division))
					.filter(fighter -> fighter.getStatistics().getDivisionRating().equals(divisionRating))
					.map(fighter -> fighter.officialName).forEach(fighter -> fighters.add(fighter));
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
	public String getOfficialName() {
		return officialName;
	}

	public Statistics getStatistics() {
		return statistics;
	}


}
