/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.TransactionsTableModel;
import Views.HomePage;
import Views.TransactionView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Anne Gitau
 */
public class TransactionsController implements ActionListener{
    TransactionView tView;
    HomePage hView;
    TransactionsTableModel model;
    public TransactionsController(HomePage hp){
        hView=hp;
    }
    public TransactionsController(TransactionView v){
        tView = v;
        
        model = new TransactionsTableModel();
        tView.getCakeTable().setModel(model);
        formControl();
    }
    
    public void formControl(){
        tView.getBackBtn().addActionListener(this);
        tView.getBack().addActionListener(this);
        tView.getExit().addActionListener(this);
        tView.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("BACK")) {
            tView.setVisible(false);
        }
       if (e.getSource()==tView.getExit())
        {
            System.exit(0);
            } 
       if (e.getSource()==tView.getBack())
        {
            tView.setVisible(false);
            hView.setVisible(true);
            }
         
    }
    }

