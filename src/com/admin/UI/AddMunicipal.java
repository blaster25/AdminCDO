package com.admin.UI;

import model.entity.Barangay;
import model.entity.Municipal;
import model.service.BarangayService;
import model.service.MunicipalService;
import others.validator.UniqueNameValidation;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class AddMunicipal extends Window {

	TextField municipal;
	CheckBox hasDistrict;
	ComboBox countDistrict;
	TextField barangayname;
	DateField dateCreated;
	
	FormLayout m; 
	FormLayout b;
	
	Municipal newMunicipal;
	Barangay newBarangay;
	
	BeanFieldGroup<Barangay> barangayFieldGroup;
	BeanFieldGroup<Municipal> municipalFieldGroup;
	
	MunicipalService mservice = new MunicipalService();
	BarangayService bservice = new BarangayService();
	
	
	
	public AddMunicipal() {
		// TODO Auto-generated constructor stub
		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setResizable(false);
		setModal(true);
		setWidth("50%");
		setHeight("90%");
		
		initComponent();
		m = this.municipal();
		b = this.barangay();
		
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		content.setHeightUndefined();
		content.setWidth("100%");
		
		content.addComponent(m);
		content.setComponentAlignment(m, Alignment.MIDDLE_CENTER);
		
		content.addComponent(b);
		content.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
		setContent(content);
	}
	
	private void initComponent () {
		this.newMunicipal = new Municipal();
		this.newBarangay = new Barangay();
		
		this.municipalFieldGroup = new BeanFieldGroup<Municipal>(Municipal.class);
		this.municipalFieldGroup.setItemDataSource(this.newMunicipal);
		this.municipalFieldGroup.setBuffered(true);
		
		this.barangayFieldGroup = new BeanFieldGroup<Barangay>(Barangay.class);
		this.barangayFieldGroup.setItemDataSource(this.newBarangay);
		this.barangayFieldGroup.setBuffered(true);
		
		this.municipal = new TextField("Municipal/City name");
		this.municipal.addValidator(new UniqueNameValidation("Municipal", "The municipal name is already exist"));
		this.municipal.setImmediate(true);
		this.hasDistrict = new CheckBox("has district ?");
		this.hasDistrict.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if((boolean) event.getProperty().getValue()) {
					countDistrict.setVisible(true);
				} else {
					countDistrict.setVisible(false);
				}
			}
		});
		this.countDistrict = new ComboBox("District");
		this.countDistrict.setValidationVisible(false);
		this.countDistrict.addItems(1, 2, 3, 4, 5);
		this.countDistrict.setNullSelectionAllowed(false);
		this.countDistrict.setVisible(false);
		this.barangayname = new TextField("Barangay name");
		this.dateCreated = new DateField("Date Created");
		this.dateCreated.setEnabled(false);
		
		this.municipalFieldGroup.bind(this.municipal, "name");
		this.municipalFieldGroup.bind(this.hasDistrict, "hasDistrick");
		
		this.barangayFieldGroup.bind(this.countDistrict, "districk");
		this.barangayFieldGroup.bind(this.barangayname, "name");
		this.barangayFieldGroup.bind(this.dateCreated, "date_registered");
		
	}
	
	private FormLayout municipal () {
		
		Label label = new Label("Municipal Detail");
		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		
		FormLayout form = new FormLayout();
		form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		form.addComponent(label);
		
		form.addComponent(this.municipal);
		form.addComponent(this.hasDistrict);
		form.addComponent(new Button("Submit", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				try {
					municipalFieldGroup.commit();
					m.setEnabled(false);
					b.setEnabled(true);
					mservice.post(newMunicipal);
				} catch (CommitException e) {
					
				}
			}
		}));
		
		return form;
	}
	
	private FormLayout barangay () {
		
		Label label = new Label("Barangay's of Municipal");
		label.addStyleName(ValoTheme.LABEL_H2);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		
		FormLayout form = new FormLayout();
		form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		form.addComponent(label);
		
		form.addComponent(this.countDistrict);
		form.addComponent(this.barangayname);
		form.addComponent(this.dateCreated);
		
		
		form.addComponent(new Button("Save", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try {
					barangayFieldGroup.commit();
					newBarangay.setMunicipal(newMunicipal);
					bservice.post(newBarangay);
					clearBarangay();
				} catch (CommitException e) {
					
				}
			}
		}));
		form.setEnabled(false);
		
		return form;
	}
	
	public void clearBarangay () {
		this.newBarangay = new Barangay();
		this.barangayFieldGroup.clear();
		this.barangayFieldGroup = new BeanFieldGroup<Barangay>(Barangay.class);
		this.barangayFieldGroup.setItemDataSource(this.newBarangay);
		bindB();
	}
	
	private void bindB () {
		this.barangayFieldGroup.bind(this.countDistrict, "districk");
		this.barangayFieldGroup.bind(this.barangayname, "name");
		this.barangayFieldGroup.bind(this.dateCreated, "date_registered");
	}

	@Override
	public void addCloseListener(CloseListener listener) {
		// TODO Auto-generated method stub
		super.addCloseListener(listener);
		System.out.println("Closing");
	}

}

