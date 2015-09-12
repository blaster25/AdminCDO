package com.admin.UI;

import java.awt.Font;

import com.admin.view.Main;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UpdateAccount extends VerticalLayout {

	private TextField username;
	private PasswordField password;
	private PasswordField confirmpass;
	
	public UpdateAccount() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		this.init();
		
		Main.views.addStyleName(ValoTheme.PANEL_WELL);
		
		addComponent(this.changeUsername());
		setComponentAlignment(getComponent(0), Alignment.TOP_CENTER);
		addComponent(this.updatePassword());
		setComponentAlignment(getComponent(1), Alignment.TOP_CENTER);
	}
	
	private FormLayout changeUsername () {
		
		FormLayout form = new FormLayout();
		form.setWidth(400, Unit.PIXELS);
		
		Label title = new Label("Username");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		
		form.addComponent(title);
		
		form.addComponent(this.username);
		
		Button btn = new Button("Submit");
		btn.setIcon(FontAwesome.ARROW_RIGHT);
		btn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		form.addComponent(btn);
		
		return form;
	}
	
	private FormLayout updatePassword () {
		
		FormLayout form = new FormLayout();
		form.setSpacing(true);
		form.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		form.setWidth(400, Unit.PIXELS);
		
		Label title = new Label("Password");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		
		form.addComponent(title);
		
		form.addComponent(this.password);
		form.addComponent(this.confirmpass);
		
		Button btn = new Button("Submit");
		btn.setIcon(FontAwesome.ARROW_RIGHT);
		btn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		form.addComponent(btn);
		
		return form;
	}
	
	private void init () {
		
		this.username = new TextField("Username");
//		this.username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		this.username.setIcon(FontAwesome.USER);
		
		this.password = new PasswordField("New Password");
//		this.password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		this.password.setIcon(FontAwesome.LOCK);
		
		this.confirmpass = new PasswordField("Confirm Passwod");
//		this.confirmpass.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		this.confirmpass.setIcon(FontAwesome.LOCK);
	}

}
