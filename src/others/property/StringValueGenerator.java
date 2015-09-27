package others.property;

import java.util.List;

import org.jsoup.select.Evaluator.IsLastChild;

import others.helper.MStringFormatter;

import com.vaadin.data.Item;
import com.vaadin.data.util.PropertyValueGenerator;

public class StringValueGenerator extends PropertyValueGenerator<String> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9032352363697686976L;
	private String text;
	private int options = 0;

	/**
	 * Use to initialize text
	 * @param text
	 */
	public StringValueGenerator(String text) {
		// TODO Auto-generated constructor stub
		this.text = text;
	}
	
	/**
	 * the default is "space" string
	 */
	public StringValueGenerator() {
		// TODO Auto-generated constructor stub
		this.text = " ";
	}
	
	/**
	 * 1. to format the name
	 * @param option
	 */
	public StringValueGenerator(int option) {
		// TODO Auto-generated constructor stub
		this.options = option;
	}
	
	@Override
	public String getValue(Item item, Object itemId, Object propertyId) {
		// TODO Auto-generated method stub
		switch (this.options) {
		case 1:
			this.text = this.format_name(item);
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
	
	private String format_name (Item item) {
		
		String firstname = (String) item.getItemProperty("fname").getValue();
		String middlename = (String) item.getItemProperty("lname").getValue(); 
		String lastname = (String) item.getItemProperty("mname").getValue();
		
		return MStringFormatter.capetalize(firstname)
				+ MStringFormatter.firstLetter(middlename) + ". "
				+ MStringFormatter.capetalize(lastname);
	}
	


}
