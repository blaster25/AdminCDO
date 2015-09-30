package com.admin.UI;

import model.entity.Municipal;
import model.service.MunicipalService;

import org.vaadin.gridutil.cell.GridCellFilter;
import org.vaadin.gridutil.renderer.BooleanRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer.EditDeleteButtonClickListener;

import others.helper.GridInitialization;
import others.property.MStringValueGenerator;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class MunicipalUI extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6488408969759602585L;
	TextField searchField;
	MunicipalService service = new MunicipalService();
	
	private GridCellFilter filter;
	
	public MunicipalUI() {
		// TODO Auto-generated constructor stub
		setWidth("100%");
		setSpacing(true);
		setHeightUndefined();
		setMargin(new MarginInfo(false, true, false, true));
		
		Button add = new Button("Add Municipal");
		
		add.addStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setIcon(FontAwesome.PLUS);
		addComponent(add);
		setComponentAlignment(add, Alignment.MIDDLE_RIGHT);
		
		
		final Grid grid = genInit();
		initFilter(grid);
		initColumnAlignment(grid);
		initFooterCell(grid);
		addComponent(grid);
		add.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				AddMunicipal addForm = new AddMunicipal();
				addForm.addCloseListener(new CloseListener() {
					
					@Override
					public void windowClose(CloseEvent e) {
						// TODO Auto-generated method stub
						grid.setContainerDataSource(dataSource());
						initFooterCell(grid);
					}
				});
				UI.getCurrent().addWindow(addForm);
				
			}
		});
		
	}
	
	private Grid genInit () {
		
		Grid grid = new Grid();
		grid.getDefaultHeaderRow().setStyleName("boldheader");
		grid.setSizeFull();
		grid.setContainerDataSource(dataSource());
		grid.setColumnOrder("name", "hasDistrick", "links");
		grid.removeColumn("municipalid");
//		grid.setFrozenColumnCount(1);
		grid.setSelectionMode(SelectionMode.NONE);
		grid.getColumn("name").setHeaderCaption("Municipal/City");
		grid.getColumn("hasDistrick")
			.setRenderer(new BooleanRenderer());
		grid.getColumn("tools")
			.setRenderer(new EditDeleteButtonValueRenderer(new EditDeleteButtonClickListener() {
				
				@Override
				public void onEdit(RendererClickEvent event) {
					// TODO Auto-generated method stub
					Notification.show(event.getItemId()
							.toString() + " want's to get deleted", Type.WARNING_MESSAGE);
				}
				
				@Override
				public void onDelete(RendererClickEvent event) {
					// TODO Auto-generated method stub
					Notification.show(event.getItemId()
							.toString() + " want's to get edited", Type.HUMANIZED_MESSAGE);
				}
			}));
		
		grid.setEditorEnabled(true);
		grid.getColumn("tools").setEditable(false);
		grid.setEditorFieldGroup(new FieldGroup());
		grid.getColumn("hasDistrick").setEditable(false);
		
		TextField name = new TextField();
		name.addValidator(new RegexpValidator("^\\p{Alpha}+ \\p{Alpha}+$", "Need the First and Last name"));
		grid.getColumn("name").setEditorField(name);
//		grid.addItemClickListener(new ItemClickListener() {
//			
//			@Override
//			public void itemClick(ItemClickEvent event) {
//				// TODO Auto-generated method stub
//				Notification.show("Value : " + container.getContainerProperty(event.getItemId(), event.getPropertyId()).getValue().toString());
//			}
//		});
		
		return grid;
	}
	
	private void initFilter (Grid grid) {
		this.filter = new GridCellFilter(grid);
		
		this.filter.setTextFilter("name", true, true, "name starts with");
		this.filter.setBooleanFilter("hasDistrick");
		
	}
	
	private void initColumnAlignment (Grid grid) {
		GridInitialization.columnAlignment(grid, new String[] {"hasDistrick"}, true);
	}
	
	private void initFooterCell (Grid grid) {
		GridInitialization.footerCell(grid, 1, "name");
	}
	
	public GeneratedPropertyContainer dataSource () {
		final BeanItemContainer<Municipal> container = new BeanItemContainer<>(Municipal.class, service.get());
		
		GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
		
		gpc.addGeneratedProperty("links", new PropertyValueGenerator<String>() {

			@Override
			public String getValue(Item item, Object itemId, Object propertyId) {
				// TODO Auto-generated method stub
				return "View Barangay";
			}

			@Override
			public Class<String> getType() {
				// TODO Auto-generated method stub
				return String.class;
			}
		});
		gpc.addGeneratedProperty("tools", new MStringValueGenerator());
		
		return gpc;
	}

}
