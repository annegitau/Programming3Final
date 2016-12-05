package Models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anne Gitau
 */
public class TableModel extends AbstractTableModel{
    
    Object[][] products;
    Database productsDB;
    String[] columnNames;
    public TableModel(){
        productsDB = Database.getDatabaseInstance();
        productsDB.connectToDatabase();
        columnNames = productsDB.columnNames();
        products = productsDB.getAllProducts();
        productsDB.closeDbConnection();
    }
    
    
    public int getRowCount(){
        return products.length;
    }
  public int getColumnCount(){      
       return columnNames.length;
  }
  public Object getValueAt(int row, int column){
      return products[row][column];
  }
  
  @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
}
