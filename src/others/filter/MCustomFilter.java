package others.filter;

import model.entity.Municipal;
import others.helper.MStringFormatter;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;

public class MCustomFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2128524217728732774L;
	private final Object propertyId;
	private final String value;

	public MCustomFilter(final Object propertyId, final String value) {
		// TODO Auto-generated constructor stub
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
		Municipal municipal = (Municipal)item.getItemProperty("municipal").getValue();

		if (municipal instanceof Municipal) {
			String municipalName = municipal.getName().toLowerCase();
			return municipalName.contains(this.value);
		}
		return false;
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
