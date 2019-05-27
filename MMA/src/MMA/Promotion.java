
package MMA;

import java.io.Serializable;
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Ok
@Entity
@Table(name="PROMOTION")
public class Promotion implements Serializable{
 	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int promotion_id;
	
	@Column(name="promotion_name",length=30)
	private String promotionName;
	
	public Promotion(String promotionName) {
		this.setPromotionName(promotionName);
	}
	
	public Promotion() {
		
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
	@OneToMany(cascade = CascadeType.ALL)
    private Set<Event> listOfEvents = new HashSet<Promotion.Event>();

    
    //add
    public void organizeEvent(String eventName, Date dateOfEvent) {
		if(eventName==null || dateOfEvent==null)
			throw new IllegalArgumentException("Invalid arguments");
		else {
			if(listOfEvents
					.stream()
					.anyMatch(event->event.getEventName().equals(eventName))){	
     	    throw new RuntimeException("This event is already organized");
		}else 
		listOfEvents.add(new Event(eventName, dateOfEvent));
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
            .map(event->listOfEvents.remove(event));
			}
			else
				throw new RuntimeException("No such event was organized");	
		}
	}
	
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
	
	public Set<Event> getListOfEvents() {
		return new HashSet<>(listOfEvents);
	}
	
	
	@Entity
	@Table(name="EVENT")
	private class Event{
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int event_id;
		
		private String eventName;
		private Date dateOfEvent;
		
		private Event(String eventName, Date dateOfEvent) {
			this.setEventName(eventName);
			this.setDateOfEvent(dateOfEvent);
			
		}
		
		private Event() {
			
		}

		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			if (eventName == null)
				throw new IllegalArgumentException("Name of event is null");
			else
			this.eventName = eventName;
		}

		public Date getDateOfEvent() {
			return dateOfEvent;
		}

		public void setDateOfEvent(Date dateOfEvent) {
			if (dateOfEvent == null || !(dateOfEvent instanceof Date))
				throw new IllegalArgumentException("Invalid date");
			else
			this.dateOfEvent = dateOfEvent;
		}
		
		
	}
	
	//------------------>

	

}
