/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.TableModel;
import Views.ViewAll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Anne Gitau
 */
public class indexController implements ActionListener{
    TableModel tableModel;
    ViewAll tableView;
    
    public indexController(ViewAll view){
        tableModel = new TableModel();
        tableView = view;
        tableView.getTableView().setModel(tableModel);
        control();
    }
    
    public void control(){
        tableView.getBack().addActionListener(this);
        tableView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("BACK")) {
            tableView.setVisible(false);
        }
    }
}
