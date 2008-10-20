package test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/** */
@Entity
public class Apartment {

	/** */
	public Integer buildautocd;

	/** */
	public String zipcode;

	/** */
	public Integer prefcd;

	/** */
	public Integer citycd;

	/** */
	public String address;

	/** */
	public String fulladdress;

	/** */
	public String buildingname;

	/** */
	public String buildingkana;

	/** */
	public Boolean newbuiltflg;

	/** */
	@Temporal(TemporalType.DATE)
	public Date inaugurationdate;

	/** */
	public Integer builtyear;

	/** */
	public Integer deckerfeck;

	/** */
	public Integer tradingkb;

	/** */
	public Integer structurecd;

	/** */
	public String structurecontent;

	/** */
	public String appearancefilename;

	/** */
	public Integer roomclasscd;

	/** */
	public String roomclasscontent;

	/** */
	public Integer houserent;

	/** */
	public Integer commonserviceexpense;

	/** */
	public String commonserviceexpensestr;

	/** */
	public Integer rewardmoney;

	/** */
	public String rewardmoneystr;

	/** */
	public Integer bondmoney;

	/** */
	public String bondmoneystr;

	/** */
	public Integer extinguishmentmoney;

	/** */
	public String extinguishmentmoneystr;

	/** */
	public Integer parkingprice;

	/** */
	public String parkingcontent;

	/** */
	public String moveinage;

	/** */
	public Integer perkssearchcd1;

	/** */
	public Integer perkssearchcd2;

	/** */
	public Integer perkssearchcd3;

	/** */
	public String perksmaincontent;

	/** */
	public String perksallcontent;

	/** */
	public Integer layoutblockcd;

	/** */
	public Integer layoutcd;

	/** */
	public String layoutcontent;

	/** */
	public Integer layoutsortnum;

	/** */
	public String layoutnote;

	/** */
	public Integer roomnum;

	/** */
	public Integer roomstories;

	/** */
	public String roomposition;

	/** */
	public String roomdirection;

	/** */
	public Integer roomprioritycd;

	/** */
	public String remarks;

	/** */
	public String floorplanfilename;

	/** */
	public String roomphotofilename1;

	/** */
	public String roomphotofilename2;

	/** */
	public String roomphotofilename3;

	/** */
	public Boolean newarticleflg;

	/** */
	@Temporal(TemporalType.DATE)
	public Date updatedate;

	/** */
	@Temporal(TemporalType.DATE)
	public Date expiredate;

}