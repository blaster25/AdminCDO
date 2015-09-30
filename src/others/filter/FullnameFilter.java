package others.filter;

import model.entity.Information;
import others.helper.MStringFormatter;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;

public class FullnameFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Object propertyId;
	private final String value;
	
	public FullnameFilter (final Object propertyId, final String value) {
		this.propertyId = propertyId;
		this.value = value;
	}
	
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
		if (this.value == null) {
			return true;
		}
		
		String fullname = "";
		if( item.getItemProperty("fname") == null ) {
			Information information = (Information)item.getItemProperty("information").getValue();
			fullname = MStringFormatter.capetalize(""+information.getFname())
					+ MStringFormatter.firstLetter(""+information.getMname()) + ". "
					+ MStringFormatter.capetalize(""+information.getLname());
		} else {
			fullname = MStringFormatter.capetalize(""+item.getItemProperty("fname").getValue())
					+ MStringFormatter.firstLetter(""+item.getItemProperty("mname").getValue()) + ". "
					+ MStringFormatter.capetalize(""+item.getItemProperty("lname").getValue());
		}
		

		if (fullname instanceof String) {
			String itemValue = fullname.toLowerCase();
			return itemValue.contains(this.value);
		}
		return false;
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
