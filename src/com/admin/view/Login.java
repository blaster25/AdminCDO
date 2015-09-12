package com.admin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Login extends VerticalLayout implements View {

	public Login() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		setMargin(true);
		
		FormLayout form = new FormLayout();
		form.setCaption("<h2><b><center>Login</h2>");
		form.setCaptionAsHtml(true);
		form.addComponent(new TextField("Username"));
		form.addComponent(new PasswordField("Password"));
		form.addComponent(new Button("Login", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				getUI().getNavigator().navigateTo(Main.NAME);
			}
		}));
		
		addComponent(form);
		setComponentAlignment(form, Alignment.TOP_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
