
package MMA;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import util.HibernateUtil;

//Ok
@Entity
@Table(name="PROMOTION")
public class Promotion implements Serializable{
 	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int promotion_id;
		
	private Integer yearOfEstablishment;

	private static TreeSet<Event> eventExtent = new TreeSet<>(Comparator.reverseOrder());
	
	@Column(name="promotion_name",length=30)
	private String promotionName;
	
	public Promotion(String promotionName, Integer yearOfEstablishment) {
		this.setPromotionName(promotionName);
		this.setYearOfEstablishment(yearOfEstablishment);
	}
	
	public Promotion() {
		
	}
	
	public static Promotion findPromotionByName(String promotionName) {
		Promotion promotion = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			session.beginTransaction();
			
			String hql = "SELECT p FROM Promotion p " +
		             "WHERE promotionName ='" + promotionName + "'";
			Query query = session.createQuery(hql);
		    promotion = (Promotion)query.uniqueResult();
			if(promotion==null) 
			throw new RuntimeException("fighter is null");
	  }
		   finally {
		   session.close();		
		}
		 
		 return promotion;
	}
	
	public void setPromotionName(String promotionName) {
		if (promotionName == null)
			throw new NullPointerException("Promotions name can't be null");
		else
			this.promotionName = promotionName;
	}
	
	public String getPromotionName() {
		return promotionName;
	}
	
	//-------------------->|Association With An Attribute|
	
	   // ordered for an association
	    @OneToMany(mappedBy = "promotion")
		private Set<Contract> listOfContracts = new TreeSet<>();
	  

		public void addContract(Contract contract) {
			if (contract.getPromotion() != this || contract.getPromotion()==null)
				throw new RuntimeException("Contract with different promotion or null");
		         duplicatesVector.add(contract);  
			if (!this.listOfContracts.contains(contract))
				listOfContracts.add(contract);
//			else
//				throw new IllegalArgumentException("Contract already signed");
			
		}     
		
		public void removeContract(Contract contract) {
			if (contract.getPromotion() != this || contract.getPromotion()==null)
				throw new RuntimeException("Contract with different promotion or null");
			removeDuplicates(contract);
			if (this.listOfContracts.contains(contract))
				listOfContracts.remove(contract);
//			else
//				throw new IllegalArgumentException("Contract doesn't exist");
				
		}
  
		  
	    @OneToMany(mappedBy = "promotion")
		private List<Contract> duplicatesVector = new ArrayList<>();

	    
		public void removeDuplicates(Contract contract) {
			Iterator<Contract> it = duplicatesVector.iterator();
			while (it.hasNext()) {
			    Contract s = it.next();
			    if(s.equals(contract))
			    it.remove();
			}
//			for(Contract myContract : duplicatesVector)
//				if(myContract.equals(contract))
//			duplicatesVector.remove(myContract);
		}
		
		public List<Contract> getDuplicatesVector() {
			return new ArrayList<>(duplicatesVector);
		}
		
		
		public  Set<Contract> getListOfContracts() {
			return new TreeSet<>(listOfContracts);
		}
		//------------------> 
		

	
	//-----------------> |Composition Association|
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private Set<Event> listOfEvents = new HashSet<Event>();

    
    //add
//    public void organizeEvent(String eventName, Date dateOfEvent) {
//		if(eventName==null || dateOfEvent==null)
//			throw new IllegalArgumentException("Invalid arguments");
//		else {
//			if(listOfEvents
//					.stream()
//					.anyMatch(event->event.getEventName().equals(eventName))){	
//     	    throw new RuntimeException("This event is already organized");
//		}else 
//		listOfEvents.add(new Event(eventName, dateOfEvent));
//		}
//
//	}
//    
	
	public Integer getYearOfEstablishment() {
		return yearOfEstablishment;
	}

	public void setYearOfEstablishment(Integer yearOfEstablishment) {
		if(yearOfEstablishment==null)
			throw new RuntimeException("parameter is null");
		else
		this.yearOfEstablishment = yearOfEstablishment;
	}
	
	public void organizeEvent(Event event) {
		System.out.println("****"+eventExtent.size());
		if(!listOfEvents.contains(event)) {
		if (event==null ) {
		throw new RuntimeException("Given parameter is null");
		}
		if(this.eventExtent.stream().anyMatch(p->p.getEventName().equals(event.getEventName()))) {
	    throw new RuntimeException("This event is alredy oraganized");
		}
		listOfEvents.add(event); 
		eventExtent.add(event);
		
		 
		}
	}


    //remove
	public void cancelEvent(String eventName) {
		if(eventName==null)
			throw new IllegalArgumentException("Event is null");
		else {
		/*	if(listOfEvents
					.stream()
					.filter(event->event.getEventName().equals(eventName))
					.collect(Collectors.toList()).size()>0) { */
			if(listOfEvents
					.stream()
					.anyMatch(event->event.getEventName().equals(eventName))){
			listOfEvents
			.stream()
			.filter(event->event.getEventName().equals(eventName))
			.filter(event->event.getDateOfEvent().after(new Date()))
			.findFirst()
            .map(event->listOfEvents.remove(event))
            .map(event->eventExtent.remove(event));
			}
			else
				throw new RuntimeException("No such event was organized");	
		}
	}
	
	//getLasEvent()
	
	public List<String> getEventByDate(Date dateOfEvent) {
		if(dateOfEvent==null)
		 throw new IllegalArgumentException("Date of event is null");
		else {
	return listOfEvents
		.stream()
		.filter(event->event.getDateOfEvent().equals(dateOfEvent))
		.map(event->event.getEventName())
		.collect(Collectors.toList());
	
		}
		
	}
	

	public static List<Event> getEventsByDate(String dateOfEvent) {
	List<Event> events = new ArrayList<Event>();
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 try {
		session.beginTransaction();
		String hql = "SELECT e FROM Event e " +
	             "WHERE e.dateOfEvent='" + dateOfEvent + "'";
		Query query = session.createQuery(hql);
		events = query.list();
	}
	   finally {
		   session.close();		
		}
	return events;
	}
	
	public static Event getLastEventByDate() {
		List<Event> events = new ArrayList<Event>();
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			session.beginTransaction();
			String hql = "SELECT e FROM Event e";
			Query query = session.createQuery(hql);
			events = query.list();
			
		}
		   finally {
			   session.close();		
			}
				return Collections.max(events, Comparator.comparing(event -> event.getDateOfEvent()));

		}

	
	
	
	public Set<Event> getListOfEvents() {
		return new HashSet<>(listOfEvents);
	}



	
	//------------------>

	

}
