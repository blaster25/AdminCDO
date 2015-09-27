package com.admin.UI;

import java.util.Arrays;
import java.util.List;

import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer.EditDeleteButtonClickListener;

import model.entity.Information;
import model.service.InformationService;
import others.filter.CustomFilter;
import others.filter.FullnameFilter;
import others.helper.GridInitialization;
import others.others.MGridCellFilter;
import others.property.StringValueGenerator;

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
		
		List<Information> result = service.get("Municipal");
		
		BeanItemContainer<Information> container = new BeanItemContainer<>(Information.class, result);
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		
		initGpc(gpc);
		grid.setContainerDataSource(gpc);
		initGrid(grid);
		initHeaderCell(grid);
		initFooterCell(grid);
		initColumnAlignment(grid);
		initFilter(grid);
		initTools(grid);
		return layout;
	}
	
	private void initGpc(GeneratedPropertyContainer gpc) {
		gpc.addGeneratedProperty("fullname", new StringValueGenerator(1));
		gpc.addGeneratedProperty("tools", new StringValueGenerator());
	}
	
	private void initGrid (Grid grid) {

		grid.setSizeFull();
		grid.removeColumn("account");
		grid.removeColumn("mname");
		grid.removeColumn("lname");
		grid.removeColumn("fname");
		grid.removeColumn("infoid");
		grid.setSelectionMode(SelectionMode.NONE);
		grid.setColumnOrder("fullname", "gender", "contact", "email");
		grid.setFrozenColumnCount(1);
		grid.getColumn("email").setHeaderCaption("E-mail");
		grid.getColumn("fullname").setHeaderCaption("Full name");
	}
	
	
	private void initHeaderCell (Grid grid) {
		grid.getDefaultHeaderRow().setStyleName("boldheader");
	}
	private void initTools (Grid grid) {
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
