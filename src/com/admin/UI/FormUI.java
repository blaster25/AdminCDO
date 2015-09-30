package com.admin.UI;

import model.entity.Accounts;
import model.entity.Information;
import model.entity.Municipal;
import model.entity.User_municipal;
import model.entity.Usertype;
import model.service.AccountsSerivice;
import model.service.InformationService;
import model.service.MunicipalService;
import model.service.UserService;
import model.service.UsertTypeService;
import others.helper.FormHelper;
import others.helper.MStringFormatter;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class FormUI extends VerticalLayout {

// 1.) ADMIN 2.) Municipal Staff 3.) Municipal Staff With Municipal ID
	
	
	private TextField fname;
	private TextField mname;
	private TextField lname;
	private OptionGroup gender;
	private DateField dateofbirth;
	private ComboBox assignedMunicipal;
	
	private TextField contact;
	private TextField email;
	private TextArea address;
	
	private Window progressWindow = new Window();
	
	
	MunicipalService mService = new MunicipalService();
	InformationService iService = new InformationService();
	AccountsSerivice aService = new AccountsSerivice();
	UsertTypeService utService = new UsertTypeService();
	UserService userService = new UserService();
	
	Usertype usertype;
	Information information;
	
	BeanFieldGroup<Information> beanInformation;
	
	public FormUI() {
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setSpacing(true);
		
		this.usertype = utService.get(1);
		this.refreshForm();
		this.init();
		this.bind();
		
		addComponent(this.formLayout());
		setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
		addComponent(this.actionBtn());
		setComponentAlignment(getComponent(1), Alignment.MIDDLE_CENTER);
	}
	
	public FormUI(int initType) {
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setSpacing(true);
		
		this.usertype = utService.get(initType);
		this.refreshForm();
		this.init();
		this.initMunicipal();
		this.bind();

		addComponent(this.formLayout());
		setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
		addComponent(this.actionBtn());
		setComponentAlignment(getComponent(1), Alignment.MIDDLE_CENTER);
	}
	
	private HorizontalLayout formLayout () {
		
		this.progressWindow.setClosable(false);
		this.progressWindow.setResizable(false);
		this.progressWindow.center();
		this.progressWindow.setModal(true);
		this.progressWindow.setSizeUndefined();
		
		VerticalLayout progressLayout = new VerticalLayout();
		progressLayout.setMargin(true);
		this.progressWindow.setContent(progressLayout);
		ProgressBar bar = new ProgressBar();
		bar.setIndeterminate(true);
		progressLayout.addComponent(bar);
		progressLayout.setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
		
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
		if(this.usertype.getName().equals("Municipal"))
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
		btn.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String username = "";
				UI.getCurrent().addWindow(progressWindow);
				try {
					try {
						beanInformation.commit();
						Accounts acc = FormHelper.generateAccount(usertype);
						aService.post(acc);
						information.setAccount(acc);
						iService.post(information);
						
						username = acc.getUsername();
						if(usertype.getName().equals("Municipal")) {
							User_municipal user = new User_municipal();
							user.setInformation(information);
							user.setMunicipal((Municipal) assignedMunicipal.getValue());
							user.setExpertise("Programmer");
							user.setPosition("Backend");
							userService.post_municipal(user);
						}
						refreshForm();
						bind();
//						UI.getCurrent().removeWindow(progressWindow);
						Notification.show(MStringFormatter.firstLetter(information.getFname()) + " was successfuly registered\n"
								+ "with an account of " +  username + ".", Type.HUMANIZED_MESSAGE);
					} catch (CommitException e) {
						e.printStackTrace();
						Notification.show("Please fill up the form properly", Type.WARNING_MESSAGE);
					} finally{
						progressWindow.close();
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					Notification.show("Please fill up the form properly", Type.WARNING_MESSAGE);
				} finally {
					progressWindow.close();
				}
			}
		});
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
	
	public void refreshForm () {
		this.information = new Information();
		this.beanInformation = new BeanFieldGroup<Information>(Information.class);
		this.beanInformation.setItemDataSource(this.information);
		this.beanInformation.setBuffered(true);
	}
	
	
	public void bind () {
		this.beanInformation.bind(this.fname, "fname");
		this.beanInformation.bind(this.mname, "mname");
		this.beanInformation.bind(this.lname, "lname");
		this.beanInformation.bind(this.gender, "gender");
		this.beanInformation.bind(this.dateofbirth, "birthdate");
		this.beanInformation.bind(this.contact, "contact");
		this.beanInformation.bind(this.email, "email");
		this.beanInformation.bind(this.address, "address");
	}
	
	private void init() {
		
		String requiredMsg = "The %s is required";
		
		this.fname = new TextField("First name ");
		this.fname.setRequired(true);
		this.fname.setRequiredError(String.format(requiredMsg, "first name"));
		this.mname = new TextField("Middle name");
		this.lname = new TextField("Last name ");
		this.lname.setRequired(true);
		this.lname.setRequiredError(String.format(requiredMsg, "last name"));
		this.gender = new OptionGroup("Gender ");
		this.gender.setRequired(true);
		this.gender.setRequiredError(String.format(requiredMsg, "gender"));
		this.gender.setStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		this.gender.addItems("Male", "Female");		
		
		this.dateofbirth = new DateField("Birth date ");
		this.dateofbirth.setRequired(true);
		this.dateofbirth.setRequiredError(String.format(requiredMsg, "birth date"));
		
		this.contact = new TextField("Contact number");
		this.email = new TextField("E-mail Address");
		this.address = new TextArea("Address ");
		this.address.setRequired(true);
		this.address.setRequiredError(String.format(requiredMsg, "address"));
		this.address.setRows(5);
	}
	
	private void initMunicipal () {
		String requiredMsg = "The %s is required";
		this.assignedMunicipal = new ComboBox("Municipal Assigned ");
		this.assignedMunicipal.setRequired(true);
		this.assignedMunicipal.setRequiredError(String.format(requiredMsg, "municipal assigned"));
		this.assignedMunicipal.setNewItemsAllowed(false);
		this.assignedMunicipal.setFilteringMode(FilteringMode.CONTAINS);
		this.assignedMunicipal.setContainerDataSource(new BeanItemContainer<>(Municipal.class, mService.get()));
		this.assignedMunicipal.setItemCaptionPropertyId("name");
	}

}
