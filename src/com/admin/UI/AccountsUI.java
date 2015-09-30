package com.admin.UI;

import java.util.Arrays;
import java.util.List;

import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer.EditDeleteButtonClickListener;

import model.entity.Information;
import model.entity.Municipal;
import model.entity.User_municipal;
import model.service.InformationService;
import model.service.UserService;
import others.filter.CustomFilter;
import others.filter.FullnameFilter;
import others.helper.GridInitialization;
import others.others.MGridCellFilter;
import others.property.MStringValueGenerator;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class AccountsUI extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5078907091566361506L;
	
	InformationService service = new InformationService();
	UserService userService = new UserService();
	
	GridCellFilter filter;
	
	public AccountsUI() {
		// TODO Auto-generated constructor stub
		
		setSizeFull();
		setMargin(new MarginInfo(false, true, false, true));
		
		Label title = new Label("List of Accounts");
		title.addStyleName(ValoTheme.LABEL_H2);
		title.addStyleName(ValoTheme.LABEL_COLORED);
		addComponent(title);
		
		TabSheet tab = new TabSheet();
		tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tab.setSizeFull();
		addComponent(tab);
		
		tab.addTab(this.admin(), "Administrator");
		tab.addTab(this.municipal(), "Municipal");
	}
	
	private VerticalLayout admin() {
		VerticalLayout layout = new VerticalLayout();
		
		final Grid grid = new Grid();
		layout.addComponent(grid);
		grid.setSizeFull();
		
		List<Information> result = service.get("Administrator");
		
		BeanItemContainer<Information> container = new BeanItemContainer<>(Information.class, result);
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		
		initGpc(gpc);
		grid.setContainerDataSource(gpc);
		grid.removeColumn("account");
		grid.removeColumn("mname");
		grid.removeColumn("lname");
		grid.removeColumn("fname");
		grid.removeColumn("infoid");
		initGrid(grid);
		initHeaderCell(grid);
		initFooterCell(grid);
		initColumnAlignment(grid);
		initFilter(grid);
		initTools(grid);
		return layout;
	}
	
	private VerticalLayout municipal() {
		VerticalLayout layout = new VerticalLayout();
		
		final Grid grid = new Grid();
		layout.addComponent(grid);
		
		List<User_municipal> result = userService.get_municipal();
		
		BeanItemContainer<User_municipal> container = new BeanItemContainer<>(User_municipal.class, result);
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		
		initGpc(gpc, 1);
		gpc.addGeneratedProperty("municipal", new MStringValueGenerator(MStringValueGenerator.OPTION_MUNICIPAL_NAME));
		grid.setContainerDataSource(gpc);
		grid.removeColumn("information");
		grid.removeColumn("usermunicipalid");
		initGrid(grid);
		initHeaderCell(grid);
		initFooterCell(grid);
		initColumnAlignment(grid);
		initFilter(grid);
		this.filter.setCustomFilter("municipal", CustomFilter.customFilter(this.filter, "municipal"));
		initTools(grid);
		return layout;
	}
	
	private void refreshMunicipal(Grid grid) {
		List<User_municipal> result = userService.get_municipal();
		
		BeanItemContainer<User_municipal> container = new BeanItemContainer<>(User_municipal.class, result);
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		
		this.initGpc(gpc, 1);
		
		grid.setContainerDataSource(gpc);
		this.initGrid(grid);
	}
	
	private void initGpc(GeneratedPropertyContainer gpc) {
		
		gpc.addGeneratedProperty("fullname", new MStringValueGenerator(MStringValueGenerator.OPTION_FORMATNAME));
		gpc.addGeneratedProperty("tools", new MStringValueGenerator());
	}
	
	private void initGpc(GeneratedPropertyContainer gpc, int option) {
		gpc.addGeneratedProperty("municipal", new MStringValueGenerator(MStringValueGenerator.OPTION_MUNICIPAL_NAME));
		gpc.addGeneratedProperty("fullname", new MStringValueGenerator(MStringValueGenerator.OPTION_USER_FULLNAME));
		gpc.addGeneratedProperty("contact", new MStringValueGenerator(MStringValueGenerator.OPTION_USER_CONTACT));
		gpc.addGeneratedProperty("email", new MStringValueGenerator(MStringValueGenerator.OPTION_USER_EMAIL));
		gpc.addGeneratedProperty("gender", new MStringValueGenerator(MStringValueGenerator.OPTION_USER_GENDER));
		gpc.addGeneratedProperty("tools", new MStringValueGenerator());
	}
	
	private void initGrid (Grid grid) {

		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.NONE);
		grid.setColumnOrder("fullname", "gender", "contact", "email");
		grid.setFrozenColumnCount(1);
		grid.getColumn("email").setHeaderCaption("E-mail");
		grid.getColumn("fullname").setHeaderCaption("Full name");
	}
	
	
	private void initHeaderCell (Grid grid) {
		grid.getDefaultHeaderRow().setStyleName("boldheader");
	}
	private void initTools (final Grid grid) {
		grid.getColumn("tools")
			.setRenderer(new EditDeleteButtonValueRenderer(
					new EditDeleteButtonClickListener() {
						
						@Override
						public void onEdit(RendererClickEvent event) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onDelete(RendererClickEvent event) {
							// TODO Auto-generated method stub

							if(event.getItemId() instanceof User_municipal) {
								userService.delete_municipal((User_municipal)event.getItemId());
								refreshMunicipal(grid);
							} else if (event.getItemId() instanceof Information) {
								System.out.println("Information : " + event.getItemId().toString());
							}
						}
					}));
	}
	private void initColumnAlignment(Grid grid) {
		GridInitialization.columnAlignment(grid, new String[] {"gender", "contact"}, true);
	}
	private void initFooterCell (Grid grid) {
		GridInitialization.footerCell(grid, 1, "fullname");
	}
	private void initFilter (Grid grid) {
		this.filter = new GridCellFilter(grid);
		
		ComboBox filterGender = this.filter.setComboBoxFilter("gender", Arrays.asList("Male", "Female"));
		filterGender.setItemIcon("Male", FontAwesome.MALE);
		filterGender.setItemIcon("Female", FontAwesome.FEMALE);
		
		this.filter.setCustomFilter("fullname", CustomFilter.fullnameFilter(this.filter, "fullname"));
	}

}
