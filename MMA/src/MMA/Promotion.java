//findFirst 
//isPresent
//boxed
//removeIf
//
//----stream-----
//
//
//contains
//
//----list------

package MMA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//Ok
public class Promotion implements Serializable {

	private String promotionName;
	private List<Contract> listOfContracts = new ArrayList<>();
    private Set<Event> listOfEvents = new HashSet<Promotion.Event>();

	public Promotion(String promotionName) {
		this.setPromotionName(promotionName);
	}

	public void addContract(Contract contract) {
		if (contract.getPromotion() != this)
			throw new IllegalArgumentException("Contract with different promotion");
		if (this.listOfContracts.contains(contract))
			throw new IllegalArgumentException("Contract already signed");
		else
			listOfContracts.add(contract);
	}

	public void removeContract(Contract contract) {
		if (contract.getPromotion() != this)
			throw new IllegalArgumentException("Contract with different promotion");
		if (!this.listOfContracts.contains(contract))
			throw new IllegalArgumentException("Contract doesn't exist");
		else
			listOfContracts.remove(contract);
	}

	public void setPromotionName(String promotionName) {
		if (promotionName == null)
			throw new NullPointerException("Promotions name can't be null");
		else
			this.promotionName = promotionName;
	}

	public List<Contract> getListOfContracts() {
		return new ArrayList<>(listOfContracts);
	}

	public String getPromotionName() {
		return promotionName;
	}
	
	public void organizeEvent(String eventName, Date dateOfEvent) {
		if(eventName==null || dateOfEvent==null)
			throw new IllegalArgumentException("Invalid arguments");
		else
		listOfEvents.add(new Event(eventName, dateOfEvent));
	}

	public void cancelEvent(String eventName) {
		if(eventName==null)
			throw new IllegalArgumentException("Event is null");
		else {
			listOfEvents
			.stream()
			.filter(event->event.getEventName().equals(eventName))
			.filter(event->event.getDateOfEvent().after(new Date()))
			.findFirst()
            .map(event->listOfEvents.remove(event));
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
	
	private class Event{
		private String eventName;
		private Date dateOfEvent;
		
		private Event(String eventName, Date dateOfEvent) {
			this.setEventName(eventName);
			this.setDateOfEvent(dateOfEvent);
			
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

	

}
