package com.admin.UI;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AccountInfo extends VerticalLayout {

	private TextField fname;
	private TextField mname;
	private TextField lname;
	private DateField birthdate;
	private OptionGroup sex;
	
	private TextField contact;
	private TextField email;
	private TextArea address;
	
	
	public AccountInfo() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		this.init();
		
		HorizontalLayout subLayout = new HorizontalLayout();
		subLayout.setMargin(true);
		subLayout.setSizeUndefined();
		
		subLayout.addComponent(this.information());
		subLayout.addComponent(this.importantInfo());
		
		addComponent(subLayout);
		setComponentAlignment(getComponent(0), Alignment.TOP_CENTER);
		addComponent(this.actionsBtn());
		setComponentAlignment(getComponent(1), Alignment.TOP_CENTER);
	}
	
	private FormLayout information () {
		
		FormLayout form = new FormLayout();
		form.setWidth("500px");
		form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		
		Label label = new Label("Personal Info");
		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		form.addComponent(label);
		
		form.addComponent(this.fname);
		form.addComponent(this.mname);
		form.addComponent(this.lname);
		form.addComponent(this.sex);
		form.addComponent(this.birthdate);
		
		return form;
	}
	
	private FormLayout importantInfo () {
		
		FormLayout form = new FormLayout();
		form.setWidth("500px");
		form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		
		Label label = new Label("Important Information");
		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		form.addComponent(label);
		
		form.addComponent(this.contact);
		form.addComponent(this.email);
		form.addComponent(this.address);
		
		return form;
	}
	
	private HorizontalLayout actionsBtn () {
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setSpacing(true);
		
		Button btn = new Button("Save Edit");
		btn.setIcon(FontAwesome.PENCIL);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		layout.addComponent(btn);
		
		btn = new Button("Cancel");
		btn.setIcon(FontAwesome.REFRESH);
		layout.addComponent(btn);
		
		return layout;
	}
	
	private void init() {
		this.fname = new TextField("First name");
		this.mname = new TextField("Middle name");
		this.lname = new TextField("Last name");
		this.birthdate = new DateField("Birthday");
		this.sex = new OptionGroup("Gender");
		this.sex.addItems("Female", "Male");
		this.sex.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		
		this.contact = new TextField("Contact Number");
		this.email = new TextField("E-mail Address");
		this.address = new TextArea("Address");
		this.address.setRows(3);
		
	}

}
