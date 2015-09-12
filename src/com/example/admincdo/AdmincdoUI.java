package com.example.admincdo;

import javax.servlet.annotation.WebServlet;

import com.admin.view.Login;
import com.admin.view.Main;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("admincdo")
public class AdmincdoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AdmincdoUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {

		setSizeFull();
		getPage().setTitle("Admin");
		
		navigator = new Navigator(this, this);
		
		navigator.addView("", new Login());
		navigator.addView(Main.NAME, new Main());
	}

}