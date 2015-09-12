package com.admin.UI;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class FormUI extends VerticalLayout {

// 1.) ADMIN 2.) Municipal Staff 3.) Municipal Staff With Municipal ID
	private int type;
	
	
	private TextField fname;
	private TextField mname;
	private TextField lname;
	private OptionGroup gender;
	private DateField dateofbirth;
	private ComboBox assignedMunicipal;
	
	private TextField contact;
	private TextField email;
	private TextArea address;
	
	public FormUI() {
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setSpacing(true);
		
		this.type = 1;
		this.init();
		
		addComponent(this.formLayout());
		setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
		addComponent(this.actionBtn());
		setComponentAlignment(getComponent(1), Alignment.MIDDLE_CENTER);
	}
	
	public FormUI(int initTpe) {
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setSpacing(true);
		
		this.type = initTpe;
		this.init();

		addComponent(this.formLayout());
		setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
		addComponent(this.actionBtn());
		setComponentAlignment(getComponent(1), Alignment.MIDDLE_CENTER);
	}
	
	private HorizontalLayout formLayout () {
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setSpacing(true);
		
		layout.addComponent(this.basic());
		layout.addComponent(this.location());
		
		return layout;
	}
	
	private FormLayout basic () {
		
		Label title = new Label("Personal");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		
		FormLayout form = new FormLayout();
		form.addComponent(title);
		form.addComponent(fname);
		form.addComponent(mname);
		form.addComponent(lname);
		form.addComponent(gender);
		form.addComponent(dateofbirth);
		
		return form;
	}
	
	private FormLayout location () {
		
		Label title = new Label("Others");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		
		FormLayout form = new FormLayout();
		
		
		form.addComponent(title);
		if(this.type == 2)
			form.addComponent(this.assignedMunicipal);
		
		form.addComponent(this.contact);
		form.addComponent(this.email);
		form.addComponent(this.address);
		
		return form;
	}
	
	private HorizontalLayout actionBtn () {
		HorizontalLayout action = new HorizontalLayout();
		action.setSizeUndefined();
		action.setMargin(new MarginInfo(false, false, true, false));
		action.setSpacing(true);
		
		Button btn = new Button();
		btn.setCaption("<b>Save");
		btn.setCaptionAsHtml(true);
		btn.setIcon(FontAwesome.SAVE);
		btn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		action.addComponent(btn);
		action.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
		
		btn = new Button();
		btn.setCaption("<b>Clear");
		btn.setCaptionAsHtml(true);
		btn.setIcon(FontAwesome.REPEAT);
		btn.setStyleName(ValoTheme.BUTTON_PRIMARY);
		action.addComponent(btn);
		action.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
		
		return action;
	}
	
	private void init() {
		
		String asterisk = "<b style=\"color: red\">*</b>";
		
		this.fname = new TextField("First name " + asterisk);
		this.fname.setCaptionAsHtml(true);
		this.mname = new TextField("Middle name");
		this.lname = new TextField("Last name " + asterisk);
		this.lname.setCaptionAsHtml(true);
		this.gender = new OptionGroup("Gender " + asterisk);
		this.gender.setCaptionAsHtml(true);
		this.gender.setStyleName("horizontal");
		this.gender.addItems("Male", "Female");		
		
		this.dateofbirth = new DateField("Birth date " + asterisk);
		this.dateofbirth.setCaptionAsHtml(true);
		this.assignedMunicipal = new ComboBox("Municipal Assigned " + asterisk);
		this.assignedMunicipal.setCaptionAsHtml(true);
		
		
		this.contact = new TextField("Contact number");
		this.email = new TextField("E-mail Address");
		this.address = new TextArea("Address " + asterisk);
		this.address.setCaptionAsHtml(true);
		this.address.setRows(5);
	}

}
