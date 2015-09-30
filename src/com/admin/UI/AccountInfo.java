package com.admin.UI;

import model.entity.Information;
import model.service.InformationService;

import com.admin.view.Main;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
	
	
	private InformationService service = new InformationService();
	private BeanFieldGroup<Information> form;
	private Information result;
	private Label error;
	
	
	public AccountInfo() {
		// TODO Auto-generated constructor stub
		error = new Label();
		error.setVisible(false);
		error.setContentMode(ContentMode.HTML);
		error.addStyleName(ValoTheme.LABEL_FAILURE);
//		error.addStyleName(ValoTheme.LABEL_TINY);
		
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		this.init();
		
		HorizontalLayout subLayout = new HorizontalLayout();
		subLayout.setMargin(true);
		subLayout.setSizeUndefined();
		
		subLayout.addComponent(this.information());
		subLayout.addComponent(this.importantInfo());
		
		addComponent(error);
		addComponent(subLayout);
		setComponentAlignment(subLayout, Alignment.TOP_CENTER);
		addComponent(this.actionsBtn());
		setComponentAlignment(getComponent(2), Alignment.TOP_CENTER);
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
		btn.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try {
					form.commit();
					service.merger(result);
					Notification.show("Your update was successfuly change!", Notification.TYPE_HUMANIZED_MESSAGE);
					error.setVisible(false);
				} catch (CommitException e) {
//					Notification.show("Correct the coresponding error!", Notification.TYPE_ERROR_MESSAGE);
					for(Field<?> field: form.getFields()) {
						ErrorMessage errMsg = ((AbstractField<?>)field).getErrorMessage();
						if(errMsg != null) {
							error.setValue("Error in "
									+ field.getCaption() + ": "
									+ errMsg.getFormattedHtmlMessage());
							error.setVisible(true);
							break;
						}
					}
				}
			}
		});
		layout.addComponent(btn);
		
		btn = new Button("Cancel");
		btn.setIcon(FontAwesome.REFRESH);
		btn.addListener(ClickEvent.class, this, "cancelListener");
		layout.addComponent(btn);
		
		return layout;
	}
	
	private void init() {

		
		this.result = service.get(Main.session);
		this.form = new BeanFieldGroup<Information>(Information.class);
		this.form.setItemDataSource(this.result);
		this.form.setBuffered(true);
		
		
		this.fname = new TextField("First name");
		this.fname.setImmediate(true);
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
		
		this.form.bind(this.fname, "fname");
		this.form.bind(this.mname, "mname");
		this.form.bind(this.lname, "lname");
		this.form.bind(this.sex, "gender");
		this.form.bind(this.birthdate, "birthdate");
		this.form.bind(this.contact, "contact");
		this.form.bind(this.email, "email");
		this.form.bind(this.address, "address");
	}
	
	
	public void cancelListener (ClickEvent event) {
		form.discard();
	}

}
