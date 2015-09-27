package others.filter;

import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;

import others.helper.MStringFormatter;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.themes.ValoTheme;

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
		String fullname = MStringFormatter.capetalize(""+item.getItemProperty("fname").getValue())
							+ MStringFormatter.firstLetter(""+item.getItemProperty("mname").getValue()) + ". "
							+ MStringFormatter.capetalize(""+item.getItemProperty("lname").getValue());

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
