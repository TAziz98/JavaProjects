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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Ok
//don't have removeOponent method, because list of oponents should stay in history of a fighter.

@Entity
@Table(name="STATISTICS")
public class Statistics implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statistics_id;
	
	@Column(name="number_of_wins", nullable = false)
	private int numberOfWins;
	
	@Column(name="number_of_losts", nullable = false)
	private int numberofLosts;
	
	@Column(name="number_of_stand_offs", nullable = false)
	private int numberOfStandOffs;
	
	@Column(name="division")
	@Enumerated(EnumType.STRING)
	private Division division;
	
	@Column(name="division_rating")
	@Enumerated(EnumType.STRING)
	private DivisionRating divisionRating;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name="STATISTICS_LIST_OF_OPONENTS", joinColumns = @JoinColumn(name="statistics_id"), inverseJoinColumns = @JoinColumn(name="fighter_id"))
	 @ElementCollection(targetClass=Fighter.class)
	 @JoinTable(name="LIST_OF_OPONENTS",joinColumns=@JoinColumn(name="statistics_id"))
     @Column(name="fighter_id")
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

	public Statistics() {
		
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
	public int getNumberOfWins() {
		return numberOfWins;
	}

	public int getNumberofLosts() {
		return numberofLosts;
	}

	public int getNumberOfStandOffs() {
		return numberOfStandOffs;
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

//	public String toString() {
//		return "Proffesional Record [" + numberOfWins + "-" + numberOfStandOffs + "-" + numberofLosts + "]";
//	}
	
	@Override
	public String toString() {
		return "Statistics [statistics_id=" + statistics_id + ", numberOfWins=" + numberOfWins + ", numberofLosts="
				+ numberofLosts + ", numberOfStandOffs=" + numberOfStandOffs + ", division=" + division
				+ ", divisionRating=" + divisionRating + ", listOfOponents=" + listOfOponents + "]";
	}

}
