package MMA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//Ok
public class Fighter implements Serializable {

	private String nickName;
	private String officialName;
//complex Attribute
	private Statistics statistics;

	private Contract contract;
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

	public void setContract(Contract contract) {
		if (contract == null)
			throw new NullPointerException("No such contract");
		if (contract.getFighter() != this)
			throw new IllegalArgumentException("Contract with different fighter");
		else
			this.contract = contract;
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

	public void setNickName(String nickName) {
		if (nickName == null)
			throw new NullPointerException("NickName is not applied");
		else
			this.nickName = nickName;
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

	public String getNickName() {
		return nickName;
	}

}
