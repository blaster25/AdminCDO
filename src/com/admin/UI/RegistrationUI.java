package com.admin.UI;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class RegistrationUI extends VerticalLayout {

	public RegistrationUI() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		setMargin(new MarginInfo(false, true, true, true));
		
		TabSheet tab = new TabSheet();
		tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tab.addStyleName("centered-tabs");
		tab.setSizeFull();
		
		addComponent(tab);
		
		FormUI municipal = new FormUI(2);
		FormUI admin = new FormUI();
		
		tab.addTab(municipal, "Municipal Staff", FontAwesome.USERS);
		tab.addTab(admin, "Admin Staff", FontAwesome.USERS);
	}

}
