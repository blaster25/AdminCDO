package com.admin.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class NavigationMenu extends HorizontalLayout {

	public NavigationMenu() {
		// TODO Auto-generated constructor stub
		setWidth("100%");
		setMargin(new MarginInfo(false, true, true, true));
		
		Label brand = new Label("<h2><b>ADMINISTRATOR</h2>");
		brand.setContentMode(ContentMode.HTML);
		addComponent(brand);
		setComponentAlignment(this.getComponent(0), Alignment.MIDDLE_LEFT);
		
		MenuBar menu = new MenuBar();
		menu.addItem("Home", FontAwesome.HOME, new MenuCommandListener("home", true));
		MenuItem entries = menu.addItem("Entries", FontAwesome.CHAIN, null);
		entries.addItem("Registration", new MenuCommandListener("registration"));
		entries.addItem("Municipals", new MenuCommandListener("municipals"));
		entries.addItem("Accounts", new MenuCommandListener("accounts"));
		
		menu.addItem("Reports", new MenuCommandListener("reports"));
		
		MenuItem accountSet = menu.addItem("Settiings", FontAwesome.GEAR, null);
		accountSet.addItem("Information", FontAwesome.USER, new MenuCommandListener("info-settings"));
		accountSet.addItem("Account", FontAwesome.ARCHIVE, new MenuCommandListener("account-settings"));
		accountSet.addItem("Logout", new MenuCommandListener("", true));
		
		addComponent(menu);
		setComponentAlignment(menu, Alignment.MIDDLE_RIGHT);
	}
	
	class MenuCommandListener implements MenuBar.Command {

		String subLink;
		boolean isAbstract = false;
		public MenuCommandListener(String link) {
			// TODO Auto-generated constructor stub
			this.subLink = link;
		}
		
		public MenuCommandListener(String link, boolean isAbstractPage) {
			// TODO Auto-generated constructor stub
			this.subLink = link;
			this.isAbstract = isAbstractPage; 
		}
		
		@Override
		public void menuSelected(MenuItem selectedItem) {
			// TODO Auto-generated method stub
//			getUI().getNavigator().navigateTo(Main.NAME + "/" + this.subLink);
			System.out.println(this.subLink);
			if (this.isAbstract) {
				getUI().getNavigator().navigateTo(this.subLink);
			} else {
//				System.out.println(Main.NAME + "/" + this.subLink);
				getUI().getNavigator().navigateTo(Main.NAME + "/" + this.subLink);
			}
			
		}
		
	}

}
