package test.csv;

import java.util.Date;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVConvertor;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;
import org.seasar.s2csv.csv.convertor.CSVDateConvertor;

/** */@CSVEntity(header=true)
public final class ApartmentCsv {
	/** */@CSVColumn(columnIndex=0)
	public int buildAutoCD;
	/** */@CSVColumn(columnIndex=1)
	public String zipCode;
	/** */@CSVColumn(columnIndex=2)
	public int prefCD;
	/** */@CSVColumn(columnIndex=3)
	public int cityCD;
	/** */@CSVColumn(columnIndex=4)
	public String address;
	/** */@CSVColumn(columnIndex=5)
	public String buildingName;
	/** */@CSVColumn(columnIndex=6)
	public String buildingKana;
	/** */@CSVColumn(columnIndex=7)
	public boolean newBuiltFlg;
	/** */@CSVColumn(columnIndex=8)
	@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyy-MM-dd hh:mm:ss.S")
	public Date inaugurationDate;
	/** */@CSVColumn(columnIndex=9)
	public Integer deckerFeck;
	/** */@CSVColumn(columnIndex=10)
	public int tradingKB;
	/** */@CSVColumn(columnIndex=11)
	public String appearanceFileName;
	/** */@CSVColumn(columnIndex=12)
	public int roomAutoCD;
	/** */@CSVColumn(columnIndex=13)
	public int roomClassCD;
	/** */@CSVColumn(columnIndex=14)
	public Float houseRent;
	/** */@CSVColumn(columnIndex=15)
	public Float commonServiceExpense;
	/** */@CSVColumn(columnIndex=16)
	public Float rewardMoney;
	/** */@CSVColumn(columnIndex=17)
	public Float bondMoney;
	/** */@CSVColumn(columnIndex=18)
	public Float extinguishmentMoney;
	/** */@CSVColumn(columnIndex=19)
	public Float parkingPrice;
	/** */@CSVColumn(columnIndex=20)
	public Integer perksSearchCD1;
	/** */@CSVColumn(columnIndex=21)
	public Integer perksSearchCD2;
	/** */@CSVColumn(columnIndex=22)
	public Integer perksSearchCD3;
	/** */@CSVColumn(columnIndex=23)
	public String perksContent;
	/** */@CSVColumn(columnIndex=24)
	public Float proprietaryEstate;
	/** */@CSVColumn(columnIndex=25)
	public Integer layoutBlockCD;
	/** */@CSVColumn(columnIndex=26)
	public String layoutContent;
	/** */@CSVColumn(columnIndex=27)
	public int layoutSortNum;
	/** */@CSVColumn(columnIndex=28)
	public String layoutNote;
	/** */@CSVColumn(columnIndex=29)
	public int roomNum;
	/** */@CSVColumn(columnIndex=30)
	public Integer roomStories;
	/** */@CSVColumn(columnIndex=31)
	public String roomPosition;
	/** */@CSVColumn(columnIndex=32)
	public String roomDirection;
	/** */@CSVColumn(columnIndex=33)
	public int roomPriorityCD;
	/** */@CSVColumn(columnIndex=34)
	public String remarks;
	/** */@CSVColumn(columnIndex=35)
	public String floorplanFileName;
	/** */@CSVColumn(columnIndex=36)
	public String roomPhotoFileName1;
	/** */@CSVColumn(columnIndex=37)
	public String roomPhotoFileName2;
	/** */@CSVColumn(columnIndex=38)
	public String roomPhotoFileName3;
	/** */@CSVColumn(columnIndex=39)
	public boolean newArticleFlg;
	/** */@CSVColumn(columnIndex=40)
	@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyy-MM-dd hh:mm:ss.S")
	public Date updateDate;
	/** */@CSVColumn(columnIndex=41)
	@CSVConvertor(convertor=CSVDateConvertor.class,convertSetProp="pattern=yyyy-MM-dd hh:mm:ss.S")
	public Date gexpireDate;
	/** */@CSVColumn(columnIndex=42)
	public String roomCD;

	/** */@CSVColumn(columnIndex=43)
	public String structureContent;
	/** */@CSVColumn(columnIndex=44)
	public String roomClassContent;
	/** */@CSVColumn(columnIndex=45)
	public String commonServiceExpenseStr;
	/** */@CSVColumn(columnIndex=46)
	public String rewardMoneyStr;
	/** */@CSVColumn(columnIndex=47)
	public String bondMoneyStr;
	/** */@CSVColumn(columnIndex=48)
	public String extinguishmentMoneyStr;
	/** */@CSVColumn(columnIndex=49)
	public String parkingContent;
	/** */@CSVColumn(columnIndex=50)
	public String moveInAge;
	
}
