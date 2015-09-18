package com.admin.UI;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Information;

import com.admin.view.Main;
import com.example.admincdo.AdmincdoUI;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
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
	
	
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	private EntityManager entityManager = emfactory.createEntityManager();
	
	private BeanItem<Information> bean;
	private FieldGroup fieldGroup;
	
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
		btn.addListener(ClickEvent.class, this, "saveListener");
		layout.addComponent(btn);
		
		btn = new Button("Cancel");
		btn.setIcon(FontAwesome.REFRESH);
		btn.addListener(ClickEvent.class, this, "cancelListener");
		layout.addComponent(btn);
		
		return layout;
	}
	
	private void init() {

		Query query = entityManager.createNamedQuery("find info");
		query.setParameter("infoid", Main.session);
		List<Information> userDetail = query.getResultList();
		
		if( ! userDetail.isEmpty() ) {
			for(Information in: userDetail) {
				
				
				this.bean = new BeanItem<Information>(in);
				this.fieldGroup = new FieldGroup(this.bean);
				this.fieldGroup.setBuffered(true);
				
				this.fname = new TextField("First name");
//				this.fname.setPropertyDataSource(this.bean.getItemProperty("fname"));
//				this.fname.setImmediate(true);
				this.mname = new TextField("Middle name");
//				this.mname.setPropertyDataSource(this.bean.getItemProperty("mname"));
//				this.mname.setImmediate(true);
				this.lname = new TextField("Last name");
//				this.lname.setPropertyDataSource(this.bean.getItemProperty("lname"));
//				this.lname.setImmediate(true);
				this.birthdate = new DateField("Birthday");
//				this.birthdate.setPropertyDataSource(this.bean.getItemProperty("birthdate"));
//				this.birthdate.setImmediate(true);
				this.sex = new OptionGroup("Gender");
//				this.sex.setPropertyDataSource(this.bean.getItemProperty("gender"));
//				this.sex.setImmediate(true);
				this.sex.addItems("Female", "Male");
				this.sex.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
				
				this.contact = new TextField("Contact Number");
//				this.contact.setPropertyDataSource(this.bean.getItemProperty("contact"));
//				this.contact.setImmediate(true);
				this.email = new TextField("E-mail Address");
//				this.email.setPropertyDataSource(this.bean.getItemProperty("email"));
//				this.email.setImmediate(true);
				this.address = new TextArea("Address");
//				this.address.setPropertyDataSource(this.bean.getItemProperty("address"));
//				this.address.setImmediate(true);
				this.address.setRows(3);
				
				this.fieldGroup.bind(this.fname, "fname");
				this.fieldGroup.bind(this.mname, "mname");
				this.fieldGroup.bind(this.lname, "lname");
				this.fieldGroup.bind(this.sex, "gender");
				this.fieldGroup.bind(this.birthdate, "birthdate");
				this.fieldGroup.bind(this.contact, "contact");
				this.fieldGroup.bind(this.email, "email");
				this.fieldGroup.bind(this.address, "address");
				
			}
		}
	}
	
	public void saveListener (ClickEvent event) {
		try {
//			BeanItem<Information> a = fieldGroup.getItemDataSource();
			fieldGroup.commit();
			Notification.show("Your update was successfuly change!", Notification.TYPE_HUMANIZED_MESSAGE);
		} catch (CommitException e) {
			Notification.show("Correct the coresponding error!", Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	public void cancelListener (ClickEvent event) {
		fieldGroup.discard();
	}

}
