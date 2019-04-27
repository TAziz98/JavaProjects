package MMA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		List<Fighter> oponents = new ArrayList<>();

		Fighter Tony = new Fighter("Tiramisu", "Tony Fergusson",
				new Statistics(22, 0, 3, Division.LIGHTWEIGHT, DivisionRating.TOP_1));
		Fighter Khabib = new Fighter("Eagle", "Khabib Nurmagomedov",
				new Statistics(27, 0, 0, Division.LIGHTWEIGHT, DivisionRating.Champion));
		Fighter John = new Fighter("Bones", "John Jones",
				new Statistics(24, 1, 0, Division.LIGHTHEAVYWEIGHT, DivisionRating.Champion));
		Fighter Edson = new Fighter("Eddy", "Edson Barbozaa",
				new Statistics(18, 1, 5, Division.LIGHTWEIGHT, DivisionRating.TOP_5));
		Khabib.getStatistics().setListOfOponents(Arrays.asList(Tony, Edson));
		Promotion promotion = new Promotion("UFC");
		Contract contract = new Contract(4, 500000, new Integer(2000), John, promotion);
		oponents.add(Tony);
		System.out.println(Fighter.ViewRecordByNickName(Khabib.getNickName().toString()));
		System.out.println(John.getAnnualSalary());
		for (Fighter listOfOponents : Khabib.getStatistics().getListOfOponents())
			System.out.print("| " + listOfOponents.getOfficialName() + " |");
		System.out.println();
		System.out.println(Khabib.getStatistics().getDivision());
		System.out.println("------");
		for (String f : Fighter.ViewTopFighters()) {
			System.out.println(f);
		}
		System.out.println("------------------");
		for (String f : Fighter.ViewTopFighters(Division.LIGHTWEIGHT)) {
			System.out.println(f);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse( "2018-04-30" ); 
		promotion.organizeEvent("Global Fight Night 24",date);
		promotion.getEventByDate(date).forEach(System.out::println);
		promotion.cancelEvent("Global Fight Night 24");
		promotion.getEventByDate(date).forEach(System.out::println);
	}
	

}
