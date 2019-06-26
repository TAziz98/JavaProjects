package MMA;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EVENT")
public class Event implements Comparable<Event>{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int event_id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Promotion promotion;
	

	private String eventName;
	
	private String eventPlace ;
	
	private Integer entrancePrice;



	@Temporal(TemporalType.DATE)
	private Date dateOfEvent;
	
	private Event(Promotion promotion, String eventName, Date dateOfEvent, String eventPlace, Integer entrancePrice) {
		this.setPromotion(promotion);
		this.setEventName(eventName);
		this.setEventPlace(eventPlace);
		this.setEntrancePrice(entrancePrice);
		this.setDateOfEvent(dateOfEvent);
		
	}
	
	public static Event  organize(Promotion promotion, String eventName, Date dateOfEvent,  String eventPlace, Integer entrancePrice) throws Exception {
		if(promotion == null) {
		throw new Exception("The given promotion does not exist!");
		}
		Event event = new Event(promotion, eventName, dateOfEvent, eventPlace, entrancePrice);
		promotion.organizeEvent(event);
		return event;
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
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		if(promotion==null)
			throw new RuntimeException("Given parameter is null");
		else
		this.promotion = promotion;
	}


	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		if(eventPlace==null) {
			throw new RuntimeException("Given parameter is null");
		}
		else
		this.eventPlace = eventPlace;
	}

	public Integer getEntrancePrice() {
		return entrancePrice;
	}

	public void setEntrancePrice(Integer entrancePrice) {
		if(entrancePrice==null)
		throw new RuntimeException("Given parameter is null");
		else
		this.entrancePrice = entrancePrice;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}