package others.property;

import java.util.List;

import model.entity.Barangay;
import model.entity.Information;
import model.entity.Municipal;

import org.jsoup.select.Evaluator.IsLastChild;

import others.helper.MStringFormatter;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.PropertyValueGenerator;

public class MStringValueGenerator extends PropertyValueGenerator<String> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9032352363697686976L;
	private String text;
	private String options = "";
	public static final String OPTION_MUNICIPAL_NAME = "municipalname";
	public static final String OPTION_BARANGAY_NAME = "barangayname";
	public static final String OPTION_USER_FULLNAME = "userfullname";
	public static final String OPTION_USER_GENDER = "gender";
	public static final String OPTION_USER_BIRTHDATE = "birthdate";
	public static final String OPTION_USER_CONTACT = "contact";
	public static final String OPTION_USER_EMAIL = "email";
	public static final String OPTION_USER_ADDRESS = "address";
	public static final String OPTION_VOTER = "voter";
	public static final String OPTION_ADMIN = "admin";
	public static final String OPTION_FORMATNAME = "formatname";
	
	/**
	 * the default is "space" string
	 */
	public MStringValueGenerator() {
		// TODO Auto-generated constructor stub
		this.text = ".";
	}
	
	/**
	 * 1. to format the name
	 * @param option
	 */
	public MStringValueGenerator(String option) {
		// TODO Auto-generated constructor stub
		this.options = option;
	}
	
	@Override
	public String getValue(Item item, Object itemId, Object propertyId) {
		// TODO Auto-generated method stub
		switch (this.options) {
			case MStringValueGenerator.OPTION_FORMATNAME:
				this.text = this.format_name(item);
				break;
			case MStringValueGenerator.OPTION_MUNICIPAL_NAME:
				this.text = this.municipal_name(item);
				break;
			case MStringValueGenerator.OPTION_USER_FULLNAME:
				this.text = this.user_fname(item);
				break;
			case MStringValueGenerator.OPTION_BARANGAY_NAME:
				this.text = this.barangay_name(item);
				break;
			case MStringValueGenerator.OPTION_USER_ADDRESS:
				this.text = this.byProperty(item, MStringValueGenerator.OPTION_USER_ADDRESS);
				break;
			case MStringValueGenerator.OPTION_USER_BIRTHDATE:
				this.text = this.byProperty(item, MStringValueGenerator.OPTION_USER_BIRTHDATE);
				break;
			case MStringValueGenerator.OPTION_USER_CONTACT:
				this.text = this.byProperty(item, MStringValueGenerator.OPTION_USER_CONTACT);
				break;
			case MStringValueGenerator.OPTION_USER_EMAIL:
				this.text = this.byProperty(item, MStringValueGenerator.OPTION_USER_EMAIL);
				break;
			case MStringValueGenerator.OPTION_USER_GENDER:
				this.text = this.byProperty(item, MStringValueGenerator.OPTION_USER_GENDER);
				break;
			default:
				this.text = ".";
		}
		return this.text;
	}

	@Override
	public Class<String> getType() {
		// TODO Auto-generated method stub
		return String.class;
	}
	
	private String barangay_name (Item item) {
		Barangay barangay = (Barangay)item.getItemProperty("barangay").getValue();
		return barangay.getName();
	}
	
	private String byProperty (Item item, String propertyId) {
		
		try {
			BeanItem<Information> info = new BeanItem<Information>((Information) item.getItemProperty("information").getValue());
			return (String)info.getItemProperty(propertyId).getValue();
		} catch (NullPointerException e) {
			System.out.println(e);
			return "";
		}
	}
	
	private String user_fname(Item item) {
		// TODO Auto-generated method stub
		Information info = (Information)item.getItemProperty("information").getValue();
		return MStringFormatter.capetalize(info.getFname())
				+ MStringFormatter.firstLetter(info.getMname()) + ". "
				+ MStringFormatter.capetalize(info.getLname());
	}
	
	private String municipal_name (Item item) {
		Municipal muni = (Municipal)item.getItemProperty("municipal").getValue();
		return muni.getName();
	}
	
	private String format_name (Item item) {
		
		String firstname = (String) item.getItemProperty("fname").getValue();
		String middlename = (String) item.getItemProperty("lname").getValue(); 
		String lastname = (String) item.getItemProperty("mname").getValue();
		
		return MStringFormatter.capetalize(firstname)
				+ MStringFormatter.firstLetter(middlename) + ". "
				+ MStringFormatter.capetalize(lastname);
	}
	


}
