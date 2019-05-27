package MMA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import util.HibernateUtil;



public class HibernateTest {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
	      Session session = HibernateUtil.getSessionFactory().openSession();	 
	  try {
		  Statistics statistics = new Statistics(27, 0, 0, Division.LIGHTWEIGHT, DivisionRating.Champion);
		  
		Fighter Tony = new Fighter("Tiramisu", "Tony"," Fergusson",20,27,
				new Statistics(22, 0, 3, Division.LIGHTWEIGHT, DivisionRating.TOP_1),new AfroAmerican("Afro-American", true));
		Fighter Khabib = new Fighter("Eagle", "Khabib","Nurmagomedov",24,36,
				statistics, new Caucasian("Caucasian", true));
		Fighter John = new Fighter("Bones", "John","Jones",19,33,
				new Statistics(24, 1, 0, Division.LIGHTHEAVYWEIGHT, DivisionRating.Champion), new AfroAmerican("Afro-American", true));
		Fighter Edson = new Fighter("Eddy", "Edson","Barbozaa",14,19,
				new Statistics(18, 1, 5, Division.LIGHTWEIGHT, DivisionRating.TOP_5), new AfroAmerican("Afro-American", false));
		Khabib.getStatistics().setListOfOponents(Arrays.asList(Tony, Edson));
        
		Coach JohnCavnnah = new Coach("John", "Cavannah", 7, 27, new Caucasian("Caucasian", true),1800,7);
		CoachInCareer Gracie = new CoachInCareer("Tiger", "Carlos", "Gracie", 32, 47, new Statistics(21, 0, 3, Division.LIGHTWEIGHT, DivisionRating.TOP_2), 1700, 19, new AfroAmerican("Afro-American", true));
		Compartment compartment = new Compartment("Samandar", 456,0, EnumSet.of(CompartmentType.BattleArena,CompartmentType.TrainingArena));
		Compartment Lev = new Compartment("Lev", 234,17, EnumSet.of(CompartmentType.BattleArena));
		
		 Workout workout = new Workout("FullContact", WorkoutTypes.FullContact, "Front-Kick");
		 workout.addTechnique("Kimura");
		 workout.addTechnique("Back-Feast");
		 
		Team teamKhabirov = new Team();
		teamKhabirov.setCompartment(compartment);
		teamKhabirov.setTeamsName("Khabirov Team");
		teamKhabirov.doWorkout(workout);
		Khabib.setTeam(teamKhabirov);
		Promotion promotion = new Promotion("UFC");
		 Contract contract = new Contract(1, 3000, new Integer(2000), John, promotion);
		 Contract contract2 = new Contract(3, 500000, new Integer(2000), Khabib, promotion);
		 Contract contract3 = new Contract(2, 60000, new Integer(2000), Tony, promotion);
		 Contract contract4 = new Contract(4, 900, new Integer(2000), Edson, promotion);
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse( "2019-07-30" ); 
			promotion.organizeEvent("Global Fight Night 23",date);
			promotion.organizeEvent("Global Fight Night 24",date);
			promotion.organizeEvent("Global Fight Night 26",date);
		    
		    
	
		 
		 SponsorshipAssociation Reebok = new SponsorshipAssociation("Reebok", John);
		 Reebok.sponsorAFighter(Tony);
		 Reebok.sponsorAFighter(Edson);
		 Reebok.sponsorSpecialFighter(John);
		 Reebok.sponsorSpecialFighter(Edson);
		 
		 Team aka = new Team("AKA");
		 aka.setCompartment(Lev);
     	 Tony.setTeam(aka);
     	 aka.doWorkout(workout);
     	// Khabib.setTeam(aka);
     	 
     	 Association association = new Association("GFC-Association");
     	 John.setAssociation(association);
     	 Edson.setAssociation(association);
		 
		session.beginTransaction();
		
	//	session.save(statistics);
		session.save(JohnCavnnah);
		session.save(workout);
		session.save(contract);
		session.save(contract2);
		session.save(contract3);
		session.save(contract4);
		session.save(Reebok);
		session.getTransaction().commit();
		System.out.println(Khabib.getAnnualSalary());
		System.out.println("row added");
		contract.setPromotion(promotion);
		
		   contract.setPromotion(promotion);
		   contract.setPromotion(promotion);
		    System.out.println("-----------------------list of contracts");
		    for(Contract contracts : promotion.getListOfContracts())
		    	System.out.println(contracts.getHonorariumSettledByPromotion());
		        System.out.println();
		    System.out.println("-----------------------duplicates");
		    for(Contract myContract : promotion.getDuplicatesVector())
		    System.out.println(myContract.getHonorariumSettledByPromotion());
		    System.out.println("-----------------------list of contracts");
	//	 promotion.removeContract(contract);
		    for(Contract myContracts : promotion.getListOfContracts())
		        System.out.println(myContracts.getHonorariumSettledByPromotion());
		    System.out.println("-----------------------duplicates");
		    for(Contract myContract : promotion.getDuplicatesVector())
		    System.out.println(myContract.getHonorariumSettledByPromotion());
		    for(Contract list :promotion.getListOfContracts() )
		    System.out.println(list.getFighter().getAge());
  }
  finally {
	  session.close();
//	factory.close();
}
	

}

	
	

}
