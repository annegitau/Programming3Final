/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Database;
import Models.TableModel;
import Views.HomePage;
import Views.UpdateView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Anne Gitau
 */
public class UpdateController implements ActionListener{
    UpdateView editForm;
    Database db;
    HomePage hp;
    TableModel tm;
    
    public UpdateController(UpdateView view){
        editForm = view;
        db = Database.getDatabaseInstance();
        db.connectToDatabase();
        viewControl();
        //db.closeDbConnection();
    }
    
    public void viewControl() {
        editForm.getCancel().addActionListener(this);
        editForm.getFind().addActionListener(this);
        editForm.getUpdate().addActionListener(this);
        editForm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("FIND")){
            String id = editForm.getId();
            String[] product = db.getProduct(id);
            editForm.setsize(product[2]);
            editForm.setCake(product[1]);
            editForm.setPrice(product[4]);
            editForm.setQuantity(product[3]);
        }
        else if(e.getActionCommand().equalsIgnoreCase("UPDATE")){
            String id = editForm.getId();
            String cake = editForm.getCake();
            String size = editForm.getsize();
            int quantity = editForm.getQuantity();
            float price = editForm.getPrice();
            db.updateProduct(id, cake, size, quantity, price);  
           // hp.getCakeTable().setModel(tm);
///            hp.setVisible(true);
            editForm.setVisible(false);
            
            JOptionPane.showMessageDialog(null, cake + "Has successfully been edited");
        }
        else if(e.getActionCommand().equalsIgnoreCase("CANCEL")){
            editForm.setVisible(false);
        }
    }
    
}
