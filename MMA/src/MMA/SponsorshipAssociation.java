package MMA;

import java.util.ArrayList;
import java.util.List;

public class SponsorshipAssociation {
	private String associationName;
	
 //------------------->Binary Association	
	private List<Fighter> sponsoredFighters = new ArrayList<>();
	
	public SponsorshipAssociation(String associationName,Fighter fighter) {
		this.setAssociationName(associationName);
		this.sponsorAFighter(fighter);
	}
	
	//add
	public void sponsorAFighter(Fighter fighter) {
		 if(fighter==null) 
				throw new RuntimeException("Given paramaeter(fighter) is null");
		 else {
			 if(sponsoredFighters.contains(fighter)) 
				 throw new RuntimeException("Fighter is already sponsored by "+this.associationName); 
			 else
			 sponsoredFighters.add(fighter);
			 if(!fighter.getSponsors().contains(this)) 
			 fighter.acceptSponsorship(this);
		 }
	}
	
	//remove
	public void unsponsorAFighter(Fighter fighter) {
		if (fighter == null) 
			throw new RuntimeException("Given parameter(fighter) is null");
		 else {
			if(!sponsoredFighters.contains(fighter)) 
				 throw new RuntimeException("Fighter is not sponsored by "+this.associationName); 	
			else
		   sponsoredFighters.remove(fighter);
			if(fighter.getSponsors().contains(this)) 
			fighter.refuseSponsorship(this);
		 
		}
	}

	public void setAssociationName(String associationName) {
		if(associationName==null)
			throw new RuntimeException("Given paramaeter(association name) is null");
		else
		this.associationName = associationName;
	}
	
	public String getAssociationName() {
		return associationName;
	}
	
	public List<Fighter> getSponsoredFighters() {
		return new ArrayList<>(sponsoredFighters);
	}
}


//-------------------->