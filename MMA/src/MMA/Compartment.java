package MMA;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Compartment implements TrainingArena,BattleArena {
	 private String Name;
	 private int rentPrice;
	 private int holdingDownPrice;
	 private int tax;
	 private final int pricePerDayForTraining = 120;
	 private final int pricePerDayForEvent = 800;
	 private Set<CompartmentType> compartments = EnumSet.noneOf(CompartmentType.class);
	  
	 
	public Set<CompartmentType> getCompartments() {
		return new HashSet<>(compartments);
	}


	public Compartment(String Name,CompartmentType compartmentType,int periodOfTime) {
		this.setName(Name);
		this.addCompartment(compartmentType,  periodOfTime);
	}

	 
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
	 @Override
	 public void setRentPrice(int periodOfTime) {
		    if(periodOfTime<0 )
		    throw new IllegalArgumentException("Can't be negative");
		    else {
		    this.rentPrice=(periodOfTime*pricePerDayForTraining)+this.tax;
		    }

}
    @Override
        public int getRentPrice() {
     	// TODO Auto-generated method stub
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
	public void setHoldDownPrice(int periodOfHoldingDown) {
		// TODO Auto-generated method stub
		 if(periodOfHoldingDown<0)
			    throw new IllegalArgumentException("Can't be negative");
			    else {
			    this.holdingDownPrice=periodOfHoldingDown*pricePerDayForEvent;
			    }
		
	}

	@Override
	public int getHoldDownPrice() {
		// TODO Auto-generated method stub
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


	
	public void setTax(int tax) {
		// TODO Auto-generated method stub
		if(tax<0)
			  throw new IllegalArgumentException("Can't be negative");	
				else
				this.tax = tax;
		
	}


	public int getTax() {
		return tax;
	}
		  
	  public int getPricePerDayForTraining() {
			return pricePerDayForTraining;
		}


		public int getPricePerDayForEvent() {
			return pricePerDayForEvent;
		}


	
}
