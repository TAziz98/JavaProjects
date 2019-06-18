package MMA;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;


//FIXED
@Entity
@Table(name="COMPARTMENT")
public class Compartment implements TrainingArena,BattleArena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int compartment_id;
	
	@Column(name="name", length=30)
	 private String Name;
	 
	 @Column(name="rent_price", nullable = true)
	 private Integer rentPrice;
	 
	 @Column(name="holding_down_price", nullable = true)
	 private Integer holdingDownPrice;
	 
	 @Column(name="price_per_day_for_training", nullable = false)
	 private final int pricePerDayForTraining = 120;
	 
	 @Column(name="price_per_day_for_event", nullable = false)
	 private final int pricePerDayForEvent = 800;
	 
	 @ElementCollection(targetClass=CompartmentType.class)
	 @JoinTable(name="compartments_list",joinColumns=@JoinColumn(name="compartment_id"))
     @Column(name="compartments")
	 @Enumerated(EnumType.STRING)
	 private Set<CompartmentType> compartments;
	 // EnumSet.noneOf(CompartmentType.class);
	  
	public Compartment() {
		
	}
	 
	public Set<CompartmentType> getCompartments() {
		return new HashSet<>(compartments);
	}


	public Compartment(String Name,Integer periodOfTime, Integer periodOfHoldingDown, EnumSet<CompartmentType> compartmentTypes) {
		this.setName(Name);
		this.compartments=compartmentTypes;
		//this.addCompartment(compartmentType,  periodOfTime);
		if(compartmentTypes!=null && !compartmentTypes.isEmpty()) {
			if(compartmentTypes.contains(CompartmentType.BattleArena)) 
				this.setHoldDownPrice(periodOfHoldingDown);
			else this.holdingDownPrice = null;
			if(compartmentTypes.contains(CompartmentType.TrainingArena))
				this.setRentPrice(periodOfTime);
			else this.rentPrice = null;
		} else throw new RuntimeException("Compartment type is empty or null");
	}

	
	 
	 @Override
	 public void setRentPrice(Integer periodOfTime) {
		    if(compartments.contains(CompartmentType.TrainingArena)) {
		    if(periodOfTime==null)
		    throw new RuntimeException("Given parameter is null");
		    else {
		    if(periodOfTime<0 )
		    throw new IllegalArgumentException("Can't be negative");
		    else 
		    this.rentPrice=(periodOfTime*pricePerDayForTraining);
		    }
		    } else throw new RuntimeException("Compartment type doesn't correspond");
		    	

}
    @Override
        public Integer getRentPrice() {
     	// TODO Auto-generated method stub
    	 if(!compartments.contains(CompartmentType.TrainingArena))
    	 throw new RuntimeException("Compartment type doesn't correspond");
    	 else
    	 return rentPrice;
     }
    

	public boolean isAvailable() {
		// TODO Auto-generated method stub
		if(compartments.size()<1) 
			return true;
		else
			return false;
		
	}


	@Override
	public void setHoldDownPrice(Integer periodOfHoldingDown) {
		// TODO Auto-generated method stub
		 if(compartments.contains(CompartmentType.BattleArena)) {
			    if(periodOfHoldingDown==null)
			    throw new RuntimeException("Given parameter is null");
			    else {
		 if(periodOfHoldingDown<0)
			    throw new IllegalArgumentException("Can't be negative");
			    else 
			    this.holdingDownPrice=periodOfHoldingDown*pricePerDayForEvent;
			    }
		 } else throw new RuntimeException("Compartment type doesn't correspond");
		
	}

	@Override
	public Integer getHoldDownPrice() {
		// TODO Auto-generated method stub
		 if(!compartments.contains(CompartmentType.BattleArena))
		 throw new RuntimeException("Compartment type doesn't correspond");
		 else
		 return holdingDownPrice;
	}


	 public String getName() {
		return Name;
	}


	public void setName(String name) {
		if(name==null)
	    throw new RuntimeException("Given parameter(name) is null");
		else
		this.Name = name;
	}
		  
	  public int getPricePerDayForTraining() {
			return pricePerDayForTraining;
		}


		public int getPricePerDayForEvent() {
			return pricePerDayForEvent;
		}

		public static Compartment findComparmentByName(String compartmentName) {
			Compartment compartment = null;
			 Session session = HibernateUtil.getSessionFactory().openSession();
			 try {
				session.beginTransaction();
				
				String hql = "SELECT c FROM Compartment c " +
			             "WHERE Name ='" + compartmentName + "'";
				Query query = session.createQuery(hql);
				compartment = (Compartment)query.uniqueResult();
				if(compartment==null) 
				throw new RuntimeException("fighter is null");
		  }
			   finally {
			   session.close();		
			}
			 
			 return compartment;
		}
	
		 /*
		 public void addCompartment(CompartmentType compartmentType, int periodOfTme) {
			 if(compartmentType==null) 
			 throw new RuntimeException("Given parameter(compartmentType) is null");
			 else {
			 if(compartments.contains(compartmentType)) 
			 throw new RuntimeException("Given parameter(compartmentType) exists");
			 else {
			 compartments.add(compartmentType);
			 if(compartmentType.equals(CompartmentType.BattleArena))
			 this.setHoldDownPrice(periodOfTme);
			 else
			 this.setRentPrice(periodOfTme);	 
			 }
			 }
		 }
	*/
		 
		/*
		 public void removeComparment(CompartmentType compartmentType) {
			 if(compartmentType==null)
		     throw new RuntimeException("Given parameter(compartmentType) is null");
			 else {
				  if(!compartments.contains(compartmentType))
				  throw new RuntimeException("Compartments doesn't contain such compartment type ");
				  else {
				 compartments.remove(compartmentType);
				  if(compartmentType.equals(CompartmentType.BattleArena))
						 this.setHoldDownPrice(0);
				  else {
					  this.setRentPrice(0);
				      this.setTax(0);
				  }
				  }
			 }
			 
		 }
		 */
	
}
