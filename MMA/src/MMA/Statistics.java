package MMA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Ok
//don't have removeOponent method, because list of oponents should stay in history of a fighter.
public class Statistics implements Serializable {

	private int numberOfWins;
	private int numberofLosts;
	private int numberOfStandOffs;
	private Division division;
	private DivisionRating divisionRating;

	private List<Fighter> listOfOponents;

	public Statistics(int numberOfWins, int numberOfStandOffs, int numberofLosts, Division division,DivisionRating divisionRating) {
		this.setNumberOfWins(numberOfWins);
		this.setNumberOfStandOffs(numberOfStandOffs);
		this.setNumberofLosts(numberofLosts);
		this.setDivision(division);
		this.setDivisionRating(divisionRating);
		//corrected
		listOfOponents = new ArrayList<>();
//	this.setListOfOponents(listOfOponents);
	}

	public void addOponent(Fighter oponent) {
		if (oponent == null)
			throw new NullPointerException("No oponents applied");
		listOfOponents.add(oponent);
	}

	public void setNumberOfWins(int numberOfWins) {
		if (numberOfWins < 0)
			throw new RuntimeException("Win streak can't be negative");
		else
			this.numberOfWins = numberOfWins;
	}

//In proffesional carrier list of fights should be presented to sign a contract
	public void setListOfOponents(List<Fighter> listOfOponents) {
		if (listOfOponents == null)
			throw new NullPointerException("List is null");
		for (Fighter oponents : listOfOponents) {
			if (oponents == null)
				throw new IllegalArgumentException("List contains null value");
			else
				this.listOfOponents = listOfOponents;
		}
	}

	public void setNumberofLosts(int numberofLosts) {
		if (numberofLosts < 0)
			throw new RuntimeException("Lost streak can't be negative");
		else
			this.numberofLosts = numberofLosts;
	}

	public void setNumberOfStandOffs(int numberOfStandOffs) {
		if (numberOfStandOffs < 0)
			throw new RuntimeException("Stand Off strak can't be negative");
		else
			this.numberOfStandOffs = numberOfStandOffs;
	}

	public Integer totalFights() {
		return Arrays.asList(numberOfWins, numberOfStandOffs, numberofLosts).stream().mapToInt(number -> number).sum();
	}

	public void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("list.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listOfOponents);
			oos.close();
			fos.close();
			System.out.printf("Serialized ArrayList data is saved in list.ser");
		} catch (Exception exception) {
			exception.printStackTrace();
			return;
		}
	}

	public void deserialize() {
		try {
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream("list.ser"));
			listOfOponents = (List<Fighter>) oos.readObject();
			oos.close();
			System.out.printf("Serialized ArrayList data is saved in list.ser");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		if (division == null)
			throw new NullPointerException("List is null");
		else
			this.division = division;
	}

	
	public List<Fighter> getListOfOponents() {
		return new ArrayList<>(listOfOponents);
	}

	public DivisionRating getDivisionRating() {
		return divisionRating;
	}

	public void setDivisionRating(DivisionRating divisionRating) {
		if (divisionRating == null)
			throw new NullPointerException("List is null");
		else
			this.divisionRating = divisionRating;
	}

	public String toString() {
		return "Proffesional Record [" + numberOfWins + "-" + numberOfStandOffs + "-" + numberofLosts + "]";
	}

}
