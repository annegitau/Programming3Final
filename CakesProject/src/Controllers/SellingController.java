/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Database;
import Views.SellView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Anne Gitau
 */
public class SellingController implements ActionListener{
    SellView sView;
    Database db;
    public SellingController(SellView s){
        db = Database.getDatabaseInstance();
        db.connectToDatabase();
        sView = s;
        viewControl();
    }
    
    public void viewControl() {
        sView.getCancel().addActionListener(this);
        sView.getSell().addActionListener(this);
        sView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("CANCEL")){
            sView.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("SELL")){
            String name = sView.getCake();
            int qnt = sView.getQuantity();
            float amount_paid = sView.getAmountPaid();
            db.addTransaction(name, qnt, amount_paid);            
            sView.setVisible(false);
            JOptionPane.showMessageDialog(null, "Successfully sold");
        }
    }
}
