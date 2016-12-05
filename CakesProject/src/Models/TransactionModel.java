/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Anne Gitau
 */
public class TransactionModel {
    Object[][] products;
    Database productsDB;
    String[] columnNames;
    public TransactionModel(){
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
  
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    
}
