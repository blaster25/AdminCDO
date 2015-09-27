package others.filter;

import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.themes.ValoTheme;

public class CustomFilter {

	public static CellFilterComponent<HorizontalLayout> fullnameFilter(final GridCellFilter cellFilter, final String columnId) {
		CellFilterComponent<HorizontalLayout> filter = new CellFilterComponent<HorizontalLayout>() {

			TextField tf = new TextField();

			@Override
			public HorizontalLayout layoutComponent() {
				tf.addStyleName(ValoTheme.TEXTFIELD_TINY);
				tf.setWidth(100, Unit.PERCENTAGE);
				tf.setTextChangeEventMode(TextChangeEventMode.EAGER);
				tf.addTextChangeListener(new TextChangeListener() {
					
					@Override
					public void textChange(TextChangeEvent event) {
						// TODO Auto-generated method stub

						if(event.getText().isEmpty()) {
							cellFilter.removeFilter(columnId);
						} else {
							cellFilter.replaceFilter(new FullnameFilter(columnId, event.getText()), columnId);
						}
					}
				});
				
				HorizontalLayout hLayout = new HorizontalLayout();
				hLayout.addStyleName("filter-header");
				hLayout.addComponent(this.tf);
				hLayout.setExpandRatio(this.tf, 1);
				return hLayout;
			}

			@Override
			public void clearFilter() {
				this.tf.setValue(null);
			}
		};
		return filter;
	}
}
