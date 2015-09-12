package com.admin.UI;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeUI extends VerticalLayout {

	public HomeUI() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		setMargin(new MarginInfo(false, true, true, true));
		
		Label text = new Label();
		text.setCaption("<h2><b>Welcome to our Home Page</h2>");
		text.setCaptionAsHtml(true);
		text.setContentMode(ContentMode.HTML);
		text.setValue("Lorem ipsum dolor sit amet, consectetur adipisicin"
				+ "g elit. Eius, vel, dolorum! Dolorum quibusdam ad fugit "
				+ "exercitationem, voluptates quos, quam quo laborum volupt"
				+ "ate reprehenderit, nulla illo beatae aliquid. At, placeat, ipsum.");
		addComponent(text);
		
		
		
		text = new Label();
		text.setCaption("<h2><b>About on this site</h2>");
		text.setCaptionAsHtml(true);
		text.setContentMode(ContentMode.HTML);
		text.setValue("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius,"
				+ " vel, dolorum! Dolorum quibusdam ad fugit exercitationem, voluptates"
				+ " quos, quam quo laborum voluptate reprehenderit, nulla illo beatae ali"
				+ "quid. At, placeat, ipsum.");
		addComponent(text);
	}

}
