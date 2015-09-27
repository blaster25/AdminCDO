package others.helper;

import org.vaadin.gridutil.GridUtil;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.CellReference;
import com.vaadin.ui.Grid.CellStyleGenerator;
import com.vaadin.ui.Grid.FooterCell;
import com.vaadin.ui.Grid.FooterRow;

public class GridInitialization {

	/**
	 * 
	 * @param grid
	 * @param option 1. total nunmber of row
	 */
	public static void footerCell (Grid grid, int option, String position) {
		
		FooterRow footerRow = (grid.getFooterRowCount() == 0) ? grid.appendFooterRow() : grid.getFooterRow(0);
		FooterCell footerCell = footerRow.getCell(position);
		
		switch(option) {
			case 1:
				footerCell.setText(String.format("Total : %d",
						grid.getContainerDataSource().getItemIds().size()));
				break;
				
		}
	}
	
	/**
	 * 
	 * @param grid
	 * @param positions list of column values to be aligned
	 * @param isCenter whether center or right
	 */
	public static void columnAlignment (Grid grid, final String[] columns, final boolean isCenter) {
		grid.setCellStyleGenerator(new CellStyleGenerator() {
			
			@Override
			public String getStyle(CellReference cellReference) {
				// TODO Auto-generated method stub
				String toReturn = null;
				
				for(String column: columns ) {
					if(cellReference.getPropertyId().equals(column)) {
						return (isCenter) ? GridUtil.ALIGN_CELL_CENTER : GridUtil.ALIGN_CELL_RIGHT;
					}
				}
				return null;
			}
		});
		
	}
}
