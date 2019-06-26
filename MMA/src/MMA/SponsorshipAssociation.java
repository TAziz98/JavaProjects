package MMA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import util.HibernateUtil;

@Entity
@Table(name="SPONSORSHIPASSOCIATION")
public class SponsorshipAssociation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sponsorshipAssociation_id;
	
	@Column(name="association_name", length=30)
	private String associationName;
	
	@Column(length=30)
	private String contact_email;
	

	@Column(length=30)
	private String phone_numb;
		
 //------------------->Binary Association
	
	public SponsorshipAssociation(String associationName, String contact_email, String phone_numb) {
		this.setAssociationName(associationName);
		this.setContact_email(contact_email);
		this.setPhone_numb(phone_numb);
	}
	
	public SponsorshipAssociation() {
		
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "sponsors", fetch = FetchType.LAZY)
	private List<Fighter> sponsoredFighters = new ArrayList<>();
	

	//add
		public void sponsorAFighter(Fighter fighter) {
			 if(fighter==null) 
					throw new RuntimeException("Given paramaeter(fighter) is null");
			 else {
				 if(fighter.getEthnicity().getEthnicityName().equals("Caucasian"))
						throw new RuntimeException("We cant sposor caucasian ethnicity");
				 else {
				 if(sponsoredFighters.contains(fighter)) 
					 throw new RuntimeException("Fighter is already sponsored by "+this.associationName); 
				 else
				 sponsoredFighters.add(fighter);
				 if(!fighter.getSponsorsList(fighter.getNickName()).contains(this)) 
				 fighter.acceptSponsorship(this);
			 }
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
			if(specialSponsoredFighters.contains(fighter))
			specialSponsoredFighters.remove(fighter);
			if(fighter.getSponsors().contains(this)) 
			fighter.refuseSponsorship(this);
		 
		}
	}

	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "specialMakeSponsors", fetch = FetchType.LAZY)
	private List<Fighter> specialSponsoredFighters = new ArrayList<Fighter>();
	
	
	public void  sponsorSpecialFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("null value ");
		}
		else {
			if(sponsoredFighters.contains(fighter)) {
				if(!specialSponsoredFighters.contains(fighter)) {
					specialSponsoredFighters.add(fighter);
					 if(!fighter.getSpecialMakeSponsors().contains(this)) {
						 fighter.acceptSpecialSponsorShip(this);
					 }
				}else {
					throw new RuntimeException("This fighter is not sponsored by this sponsorship association");
				}
			}
		}
	}
	
	public void unSponsorSpecialFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("null value ");
		}
		else {
				if(!specialSponsoredFighters.contains(fighter)) {
			      specialSponsoredFighters.remove(fighter);
			      fighter.refuseSpecialSponsorShip(this);
				}
				else {
					throw new RuntimeException("does not contain");
				}
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
	
	public List<Fighter> getSpecialSponsoredFighters() {
		return specialSponsoredFighters;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		if (contact_email == null)
			throw new NullPointerException("null parameter applied");
		else		
		this.contact_email = contact_email;
	}

	public String getPhone_numb() {
		return phone_numb;
	}

	public void setPhone_numb(String phone_numb) {
		if (phone_numb== null)
			throw new NullPointerException("null parameter applied");
		else		
		this.phone_numb = phone_numb;
	}
}


//-------------------->