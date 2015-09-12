package com.admin.UI;

import com.admin.view.Main;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AccountsUI extends VerticalLayout {

	public AccountsUI() {
		// TODO Auto-generated constructor stub
		Main.views.removeStyleName(ValoTheme.PANEL_BORDERLESS);
		
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		
		addComponent(new Label("List of Accounts"));
	}

}
