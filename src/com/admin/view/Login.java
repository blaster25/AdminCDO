package com.admin.view;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Accounts;
import model.entity.Usertype;
import model.service.CreateAccount;

import com.example.admincdo.AdmincdoUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class Login extends VerticalLayout implements View {

	Window dialog;
	TextField username = new TextField("Username");
	PasswordField password = new PasswordField("Password");
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(AdmincdoUI.PERSISTENT_UNIT);
	EntityManager entityManager = emfactory.createEntityManager();
	
	public Login() {
		// TODO Auto-generated constructor stub
		setSizeFull();
		dialog = new Window("Login");
		dialog.setClosable(false);
		dialog.setResizable(false);
		dialog.center();
		
//		new CreateAccount();
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(new MarginInfo(false, true, true, true));
		dialog.setContent(content);
		
		Label title = new Label("Login");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		
		HorizontalLayout form = new HorizontalLayout();
		form.setSpacing(true);
		form.addStyleName("wrapping");
		content.addComponent(form);
		
		username.setIcon(FontAwesome.USER);
		username.focus();
		form.addComponent(username);
		
		password.setIcon(FontAwesome.LOCK);
		form.addComponent(password);
		
		Button submit = new Button("Login");
		submit.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
		submit.setIcon(FontAwesome.ARROW_RIGHT);
		submit.setWidth("100%");
		submit.addListener(ClickEvent.class, this, "gotoMain");
		content.addComponent(submit);
	
	}
	
	public void gotoMain (ClickEvent event) {
		System.out.println("Username input : " + username.getValue());
		System.out.println("Password input : " + password.getValue());
		if( ! username.getValue().isEmpty() && ! password.getValue().isEmpty() ) {
			
			Query query = entityManager.createNamedQuery("find account");
			query.setParameter("user", username.getValue());
			query.setParameter("pass", password.getValue());
			List<Accounts> list = query.getResultList(); 
			
			if(! list.isEmpty()) {
				for(Accounts a: list ) {
					Usertype type = a.getUsertype();
					System.out.println("Usert type : " + type.getName());
					if(type.getName().equals("Administrator")) {
						UI.getCurrent().removeWindow(dialog);
						dialog.close();
						VaadinSession.getCurrent().setAttribute("id", a.getId());
						UI.getCurrent().getNavigator().navigateTo(Main.NAME);
					} else {
						Notification.show("Acount is invalid", Notification.TYPE_ERROR_MESSAGE);
					}
				}
			} else {
				Notification.show("Acount is invalid", Notification.TYPE_ERROR_MESSAGE);
			}
		} else {
			Notification.show("Empty field", Notification.TYPE_WARNING_MESSAGE);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		if( ! dialog.isAttached() )
			UI.getCurrent().addWindow(dialog);
	}

}
