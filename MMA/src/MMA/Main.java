package MMA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		List<Fighter> oponents = new ArrayList<>();
		Fighter Tony = new Fighter("Tiramisu", "Tony"," Fergusson",20,27,
				new Statistics(22, 0, 3, Division.LIGHTWEIGHT, DivisionRating.TOP_1),new AfroAmerican("Afro-American", true));
		Fighter Khabib = new Fighter("Eagle", "Khabib","Nurmagomedov",24,36,
				new Statistics(27, 0, 0, Division.LIGHTWEIGHT, DivisionRating.Champion), new Caucasian("Caucasian", true));
		Fighter John = new Fighter("Bones", "John","Jones",19,33,
				new Statistics(24, 1, 0, Division.LIGHTHEAVYWEIGHT, DivisionRating.Champion), new AfroAmerican("Afro-American", true));
		Fighter Edson = new Fighter("Eddy", "Edson","Barbozaa",14,19,
				new Statistics(18, 1, 5, Division.LIGHTWEIGHT, DivisionRating.TOP_5), new AfroAmerican("Afro-American", false));
		Khabib.getStatistics().setListOfOponents(Arrays.asList(Tony, Edson));
		Promotion promotion = new Promotion("UFC");
		Promotion acb = new Promotion("ACB");
		 Contract contract = new Contract(4, 500000, new Integer(2000), John, promotion);
		oponents.add(Khabib);
		Fighter.removeFighterExtent(Tony);
		SponsorshipAssociation Reebok = new SponsorshipAssociation("Reebok", John);
		Reebok.sponsorAFighter(Tony);
		Reebok.sponsorAFighter(Edson);
 	    Reebok.sponsorSpecialFighter(Tony);
 	    Reebok.sponsorSpecialFighter(Edson);
	//	Fighter.removeFighterExtent(Khabib);
//		System.out.println(Fighter.ViewRecordByNickName(Khabib.getNickName().toString()));
//		System.out.println(John.getAnnualSalary());
//		for (Fighter listOfOponents : Khabib.getStatistics().getListOfOponents())
//			System.out.print("| " + listOfOponents.getNickName() + " |");
//		System.out.println();
//		System.out.println(Khabib.getStatistics().getDivision());
//		System.out.println("------");
//		for (String f : Fighter.ViewTopFighters()) {
//			System.out.println(f);
//		}
//		System.out.println("------------------");
//		for (String f : Fighter.ViewTopFighters(Division.LIGHTWEIGHT)) {
//			System.out.println(f);
//		}
//		
//		//----------Binary ----------- 
//		 SponsorshipAssociation Reebok = new SponsorshipAssociation("Reebok", Khabib);
//		 System.out.println(Reebok.getSponsoredFighters().contains(Khabib));
//		 System.out.println(Khabib.getSponsors().contains(Reebok));
//		 Reebok.sponsorAFighter(Tony);
//		 Reebok.unsponsorAFighter(Tony);
//		 System.out.println(Tony.getSponsors().contains(Reebok));
//		 Khabib.refuseSponsorship(Reebok);
//		 System.out.println(Reebok.getSponsoredFighters().contains(Khabib));
//		 System.out.println(Khabib.getSponsors().contains(Reebok));
//		 
//		 //---------With An Attribute--------
//		 System.out.println("---------With An Attribute--------");
//	//	 System.out.println(promotion.getListOfContracts().get(0).getFighter().getName());
//		 System.out.println(John.getContract().getPromotion().getPromotionName());
//	//	 promotion.removeContract(contract);
//		 System.out.println(promotion.getListOfContracts().contains(contract)); 
//		 contract.excludePromotion(promotion);
//		 contract.excludeFighter(John);
//		 System.out.println(John.getContract()==null);
//		 System.out.println(promotion.getListOfContracts().size()>0);
//		 System.out.println(contract.getFighter()==null);
//		 System.out.println(contract.getPromotion()==null);
//      //   System.out.println(contract.getPromotion().getPromotionName());
////		 System.out.println(promotion.getListOfContracts().get(0).getFighter().getOfficialName());
////		
////		 System.out.println(promotion.getListOfContracts().size()>0);
//		 System.out.println("-----------------------------");
//		 
//		//----------Qualified -----------
//		 System.out.println("-------------------------------");
//		 Team aka = new Team("AKA");
//		 Tony.setTeam(aka);
//		 System.out.println(Tony.getTeam().getTeamsName());
//		 System.out.println(aka.getFighters().containsKey("Tiramisu"));
//		 Tony.refuceTeam(aka);
//		 System.out.println(aka.getFighters().containsKey("Tiramisu"));
//		 Tony.setTeam(aka);
//	     System.out.println(Tony.getTeam().getTeamsName());
//	     aka.unsignFighter(Tony);
//	 //    System.out.println(Tony.getTeam().getTeamsName());
//		 
//		 //---------Composition-----------
//		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = format.parse( "2019-07-30" ); 
//			promotion.organizeEvent("Global Fight Night 23",date);
//			promotion.getEventByDate(date).forEach(System.out::println);
//			promotion.organizeEvent("Global Fight Night 24",date);
//			promotion.organizeEvent("Global Fight Night 26",date);
////			System.out.println(promotion.getListOfEvents().size());
//		    promotion.cancelEvent("Global Fight Night 24");
////			System.out.println(promotion.getListOfEvents().size());
//		    
//		    
//		 //--------------abstract-------------------------
//			Person zabit = new Fighter("ZaBeast", "Zabit","Magomedsharipov",10, 22, new Statistics(17, 0, 1, Division.LIGHTWEIGHT, DivisionRating.TOP_10), new Caucasian("Caucasian", false));
//			 Contract contractZabit = new Contract(4, 500000, new Integer(2000), (Fighter) zabit, promotion);
//			System.out.println(zabit.getLastName());
//           System.out.println(zabit.getSalary());
//            
//        	//-------------------overlapping-------------------
//            EnumSet<CompartmentType> of = EnumSet.of(CompartmentType.TrainingArena,CompartmentType.BattleArena);
//            Compartment compartment = new Compartment("Californian AKA CLUB", 20, 30,of );  
//           System.out.println("Rent Price:"+compartment.getRentPrice() + " " +"Holding Price:"+ compartment.getHoldDownPrice());
//            
//        	
//     
//        	CoachInCareer coachInCar = new CoachInCareer("ZaBeast", "Zabit","Magomedsharipuv",10, 18,new Statistics(17, 0, 1, Division.LIGHTWEIGHT, DivisionRating.TOP_10),new Integer(4000),7,new AfroAmerican("Afro-American", true));
//        	Contract contract2 = new Contract(4, 30000, new Integer(2000), coachInCar, promotion);
//        	Contract contract3 = new Contract(4, 90000, new Integer(2000), Tony, promotion);
//        	coachInCar.getStatistics().setListOfOponents(oponents);
//        	System.out.println(coachInCar.getTotalSalary());
//        	System.out.println(coachInCar.identifyCoachsRank());
//        	System.out.println(((AfroAmerican) coachInCar.getEthnicity()).isMedical_inspectionHealthy());
//            System.out.println(((Caucasian)zabit.getEthnicity()).isDoping_testClear());
//	
//            
//       //----------- A dynamic-inheritance------------
//    aka.signFighter(Khabib);
//	Workout workout = new  Workout("Crossfit Super Sets", WorkoutTypes.CrossFit, "Bench Press");
//    aka.doWorkout(workout);
//    aka.getWorkout().addActivity("Delta Pumping");
//    System.out.println(aka.getWorkout().identifyHeartRate(aka.getFighter("Eagle")));
//    
//    for(Contract contracts : promotion.getListOfContracts())
//	System.out.println(contracts.getHonorariumSettledByPromotion());
//    
//    for(Person p : Person.getPersonExtent())
//   System.out.println(p.getName());
//    Contract contract5 = new Contract(4, 700000, new Integer(2000), Khabib, promotion);
//	
//	contract.setPromotion(promotion);
//	   contract.setPromotion(promotion);
//	   contract.setPromotion(promotion);
//	    System.out.println("-----------------------");
//	    for(Contract contracts : promotion.getListOfContracts())
//	    	System.out.println(contracts.getHonorariumSettledByPromotion());
//	        System.out.println();
//	    System.out.println("-----------------------");
//	    for(Contract myContract : promotion.getDuplicatesVector())
//	    System.out.println(myContract.getHonorariumSettledByPromotion());
//	    System.out.println("-----------------------");
//	 promotion.removeContract(contract);
//	    for(Contract myContracts : promotion.getListOfContracts())
//	        System.out.println(myContracts.getHonorariumSettledByPromotion());
//	    System.out.println("-----------------------");
//	    for(Contract myContract : promotion.getDuplicatesVector())
//	    System.out.println(myContract.getHonorariumSettledByPromotion());
	}

}
