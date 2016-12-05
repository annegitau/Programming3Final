package Models;

import java.awt.HeadlessException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anne Gitau
 */
public class Database {
     private static final Database databaseInstance = new Database();
    
    public static Database getDatabaseInstance() {
        return databaseInstance;
    }
    
    Connection conn = null;

	
    public Connection connectToDatabase(){        
      
        Statement statement;
        
        try{
            

            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/beeceebakery?user=root&password=");
            
            statement = conn.createStatement();
            
            // Creating a database
            String createDatabaseSqlStatement = "CREATE DATABASE IF NOT EXISTS "
                    + "beeceebakery";
            statement.executeUpdate(createDatabaseSqlStatement);
            
            // Make the program use EssentialsDatabase database
            String useDatabaseStatement = "USE beeceebakery";
            statement.executeUpdate(useDatabaseStatement);
            
            // Creating Products Table
            String createTableSqlStatement = "CREATE TABLE IF NOT EXISTS "
                    + "bakery("
                    + "productID VARCHAR(255) NOT NULL, "
                    + "cake VARCHAR(255),"
                    + "size VARCHAR(255),"
                    + "quantity INT,"
                    + "price FLOAT,"
                    + "PRIMARY KEY (productID))";
            statement.executeUpdate(createTableSqlStatement);
            
            // Creating Transactions Table
            String createTransactionsTableQuery = "CREATE TABLE IF NOT EXISTS "
                    + "Transactions("
                    + "transactionID MEDIUMINT NOT NULL AUTO_INCREMENT, "
                    + "cake VARCHAR(255),"
                    + "quantity INT,"
                    + "amount_paid FLOAT,"
                    + "PRIMARY KEY (transactionID))";
            statement.executeUpdate(createTransactionsTableQuery);
            
            String createAdminTableQuery = "CREATE TABLE IF NOT EXISTS "
                    + "admin("
                    + "username VARCHAR(255),"
                    + "password VARCHAR(255)"
                    + "PRIMARY KEY (username))";
            statement.executeUpdate(createTransactionsTableQuery);
                    
        }catch (Exception e) {
            System.err.println("Sorry could not connect to "
                    + "beeceebakery Database");  
        }     
         return null;
        
    }
    public void login(String user, String pass){
         try {
             String loginSqlStatement= "Select * from admin where username = ? and password = ?" ;
             
             PreparedStatement statement =
                     conn.prepareStatement(loginSqlStatement);
             statement.executeUpdate();
             ResultSet rs = statement.executeQuery();
             while(rs.next())
                 user = rs.getString("username");
             
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        
    
    
    
    public void addProduct(String id, String ck,  
            String sz, int qt, float pr){
        /**
         * Adds a new book to the database
         */
        try{
            String addToProductsSqlStatement = "INSERT INTO bakery "
                + "(ID, cake, size, quantity, price) "
                + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement statement = 
                    conn.prepareStatement(addToProductsSqlStatement);
            statement.setString(1, id);
            statement.setString(2, ck);
            statement.setString(3, sz);
            statement.setInt(4, qt);
            statement.setFloat(5, pr);
            statement.execute();
            
        }catch(Exception ex){
            System.err.println("Sorry could not add new cake to bakery "
                    + "table");
        }        
    }
    
    public void updateProduct(String id, String ck,
            String sz, int qt, float pr){
        /**
         * Changes the attributes of an existing cake in bakery table
         */
        try{
            String updateProductSqlStatement = "UPDATE bakery "
                    + "SET ID=?, cake=?, size=?, "
                    + "quantity=?, price=? "
                    + "WHERE ID=?";
            PreparedStatement statement = 
                    conn.prepareStatement(updateProductSqlStatement);
            statement.setString(1, id);
            statement.setString(2, ck);
            statement.setString(3, sz);
            statement.setInt(4, qt);
            statement.setFloat(5, pr);
            statement.setString(6, id);
            statement.execute();
        }catch(Exception ex){
            System.err.println("Could not update record with id: " + id +
                    "in the bakery table");
        }
    }
    
    public String[] getArray(String id, String ck,
            String sz, int qt, float pr){
        /**
         * Returns a a string array based on parameters given
         */
        String[] product = new String[6];
        product[0] = id;
        product[1] = ck;
        product[2] =sz ;
        product[3] = Integer.toString(qt);
        product[4] = Float.toString(pr);
        return product;
    }
    
    public String[] getProduct(String productID){
        /**
         * Returns a product whose ID is provided from Products table
         */
        String[] product = new String[5];
        try{
            String getProductQuery = "SELECT * FROM bakery WHERE ID = ?";
            PreparedStatement statement = 
                    conn.prepareStatement(getProductQuery);
            statement.setString(1, productID);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
                product = getArray(rs.getString("ID"), 
                        rs.getString("cake"), 
                        rs.getString("size"), rs.getInt("quantity"),
                        rs.getFloat("price"));
        }catch(Exception ex){
            System.err.println("Could not get a bakery with id: " + productID);
        } 
        return product;       
    }
    
    public String[][] getAllProducts(){
        /**
         * Returns a Two dimensional array of 
         * Products in the database
         */
        return covertArrayList(productsAll(), 5);
    }
    
    public String[][] getAllTrasacions(){
        return covertArrayList(getTransactions(), 4);
    }
    
    public String[][] covertArrayList(ArrayList<String[]> arrList, int arrSize){
        String[][] stringArray = new String[arrList.size()][arrSize];
        for(int i = 0; i < arrList.size();i++){
            stringArray[i] = arrList.get(i);
        }
        return stringArray;
    }
    
    public ArrayList<String[]> productsAll(){
        /**
         * Returns an array list of all products in the database
         */
        ArrayList<String[]> products = new ArrayList<>();
        String getAllProductsQuery = "SELECT * FROM bakery";
        try{
            PreparedStatement statement = 
                    conn.prepareStatement(getAllProductsQuery);
            ResultSet result = statement.executeQuery();
            while(result.next())
                products.add(getArray(result.getString("ID"), 
                        result.getString("cake"),
                        result.getString("size"), result.getInt("quantity"),
                        result.getFloat("price")));
        }catch(Exception ex){
            System.err.println("Sorry could not fetch all cakes");
        }       
        return products;
    }
    
    
    public void deleteProduct(String productId){
        /*
        * Removes a product record from the database
        */
        try{
            String sqlString = "DELETE FROM bakery WHERE ID=?";
            PreparedStatement sqlStatement =conn.prepareStatement(sqlString);
            sqlStatement.setString(1, productId);
            sqlStatement.executeUpdate();
        }catch(Exception ex){
            System.err.println("Could not update record with id: " + productId +
                    "in the bakery table");
        }
    }
    
    public int getProductQuantity(String productID){
        /**
         * Returns the quantity of the products in the database
         */
        
        int quantity = 0;
        String sqlString = "SELECT quantity FROM bakery WHERE ID=?";
        try{
            PreparedStatement sqlStatement =conn.prepareStatement(sqlString);
            sqlStatement.setString(1, productID);
            sqlStatement.executeUpdate();
            ResultSet rs = sqlStatement.executeQuery();
            while(rs.next())
                quantity = rs.getInt("quantity");            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return quantity;
    }
    
    public String[] columnNames(){
        String[] columns = {"productID", "cake", "cartegory", 
            "quantity", "price"};
        return columns;
    }
    
    public void addTransaction(String title, int quantity, float amount){
        /**
         * Adds a new transaction to Transactions table
         */
        try{
            String addToTransactionsSqlStatement = "INSERT INTO Transactions "
                + "(title, quantity, amount_paid) "
                + "VALUES (?, ?, ?)";
            
            PreparedStatement statement = 
                    conn.prepareStatement(addToTransactionsSqlStatement);
            statement.setString(1, title);
            statement.setInt(2, quantity);
            statement.setFloat(3, amount);
            statement.execute();
        }catch(Exception ex){
            System.err.println("Sorry could not add to transactions table");
        }
    }
    
    public ArrayList<String[]> getTransactions(){
        /**
         * Returns an array list of all transactions in the database
         */
         ArrayList<String[]> transactions = new ArrayList<>();
        String getAllTransactionsQuery = "SELECT * FROM Transactions";
        try{
            PreparedStatement statement = 
                    conn.prepareStatement(getAllTransactionsQuery);
            ResultSet result = statement.executeQuery();
            while(result.next())
                transactions.add(getTArray(result.getInt("transactionID"),
                        result.getString("title"), result.getInt("quantity"),
                        result.getFloat("amount_paid")));
        }catch(Exception ex){
            System.err.println("Sorry could not fetch all transactions");
        }       
        return transactions;
    }
    
    public String[] getTArray(int id, String cake, int quantity, float amount){
        String[] arr = new String[4];
        arr[0] = Integer.toString(id);
        arr[1] = cake;
        arr[2] = Integer.toString(quantity);
        arr[3] = Float.toString(amount);
        return arr;
    }
    
    public String[] transactionColumns(){
        String[] s = {"transactionID", "cake", "quantity", "amount_paid"};
        return s;
    }
    
    public boolean adminLogin(String password, String email){
        boolean login = false;
        if(password.equalsIgnoreCase("njeri") && email.equalsIgnoreCase(email))
            login = true;
        return login;
    }
    
    public void closeDbConnection() {
        /**
         * Close Database connection
         */
        try{
            conn.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
}
