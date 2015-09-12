package com.admin.UI;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MunicipalUI extends VerticalLayout {

	public MunicipalUI() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		
		addComponent(new Label("Municipals"));
	}

}
