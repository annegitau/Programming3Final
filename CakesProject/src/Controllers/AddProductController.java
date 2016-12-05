package Controllers;

import Models.Database;
import Models.TableModel;
import Views.AddView;
import Views.HomePage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Anne Gitau
 */
public class AddProductController implements ActionListener{
    AddView newProductForm;
    TableModel tm;
    HomePage hp;
    ApplicationController ac= new ApplicationController();
    public AddProductController(AddView view, TableModel t, HomePage h){
        newProductForm = view;
        tm = t;
        hp= h;
        viewControl();
    }
    
    public void viewControl() {
        newProductForm.getAdd().addActionListener(this);
        newProductForm.getCancel().addActionListener(this);
        newProductForm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==newProductForm.getAdd()){
           String ID = newProductForm.getId();
           String cake = newProductForm.getCake();
           String size = newProductForm.getsize();
           String qnt = newProductForm.getQuantity();
           String prc = newProductForm.getPrice();
           
           Database productsDatabase = Database.getDatabaseInstance();
           productsDatabase.connectToDatabase();
           
           int quantity = Integer.parseInt(qnt);
               float price = Float.parseFloat(prc);
               
            productsDatabase.addProduct(ID, cake,
                       size, quantity, price);
            hp.getCakeTable().setModel(tm);
                newProductForm.setVisible(false);
                //ac.buttonControl();
                hp.setVisible(true);
               // JOptionPane.showMessageDialog(null, cake + "Has been added");
                
           /*if(!emptyString(ID) && !emptyString(cake) && !emptyString(size)
                   &&!emptyString(prc) && !emptyString(qnt))
           {
               int quantity = Integer.parseInt(qnt);
               float price = Float.parseFloat(prc);
               
           }
           else{
               JOptionPane.showMessageDialog(null,
                "The form has errors",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);
           }
           */
       }
      if(e.getSource()==newProductForm.getCancel()){
           newProductForm.setVisible(false);
           
       }
    }
    
    public boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean isFloat(String str){
        try{
            Float.parseFloat(str);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean emptyString(String s){
        boolean r = true;
        if(s.length() > 1){
            r =false;
        }
        return r;
    }
    
}
