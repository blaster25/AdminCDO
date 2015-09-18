package com.admin.UI;

import model.Accounts;
import model.Usertype;

import com.admin.view.Main;
import com.example.admincdo.AdmincdoUI;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AccountsUI extends VerticalLayout {

	private JPAContainer<Accounts> accounts;
	
	public AccountsUI() {
		accounts = JPAContainerFactory.make(Accounts.class, AdmincdoUI.PERSISTENT_UNIT);
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		
		Label title = new Label("List of Accounts");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		addComponent(title);
		
		Table listAccount = new Table(null, accounts);
		listAccount.addGeneratedColumn("userType", new Table.ColumnGenerator() {
			
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				// TODO Auto-generated method stub
				Property<Usertype> prop = source.getItem(itemId).getItemProperty(columnId);
				if(prop.getType().equals(Usertype.class)) {
					Label label = new Label(prop.getValue().getName());
					return label;
				}
				return null;
			}
		});
		listAccount.setPageLength(8);
		listAccount.setSizeFull();
		listAccount.setVisibleColumns(new Object[] {"id", "username", "password", "userType"});
		listAccount.setColumnHeader("id", "ID#");
		listAccount.setColumnHeader("username", "Username");
		listAccount.setColumnHeader("password", "Password");
		listAccount.setColumnHeader("userType", "User type");
		addComponent(listAccount);
	}

}
