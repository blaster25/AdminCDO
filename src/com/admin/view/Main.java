package com.admin.view;

import com.admin.UI.AccountInfo;
import com.admin.UI.AccountsUI;
import com.admin.UI.HomeUI;
import com.admin.UI.MunicipalUI;
import com.admin.UI.RegistrationUI;
import com.admin.UI.UpdateAccount;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@DesignRoot
public class Main extends VerticalLayout implements View {

	public static String NAME = "home";
	public static Panel views;
	private String previewsLink;

	public Main() {

		// TODO Auto-generated constructor stub
		setHeightUndefined();
		setMargin(new MarginInfo(false, true, false, true));
		
		views = new Panel();
		views.setSizeFull();
		views.setContent(new HomeUI());
		
		
		addComponent(new NavigationMenu());
		addComponent(views);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		views.setStyleName(ValoTheme.PANEL_BORDERLESS);
		
		if(event.getParameters() == null || event.getParameters().isEmpty()) {
			views.setContent(new HomeUI());
		} else {
//			System.out.println(event.getParameters());
			switch(event.getParameters()) {
				case "registration" :
					views.setContent(new RegistrationUI());
					break;
				case "municipals" :
					views.setContent(new MunicipalUI());
					break;
				case "accounts" :
					views.setContent(new AccountsUI());
					break;
				case "info-settings" :
					views.setContent(new AccountInfo());
					break;
				case "account-settings" :
					views.setContent(new UpdateAccount());
					break;
			}
		}
		
		this.previewsLink = this.NAME + "/" + event.getParameters();
		
//		System.out.println(this.previewsLink);
	}

}
