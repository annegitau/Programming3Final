package Controllers;

import Models.Database;
import Models.TableModel;
import Views.AddView;
import Views.DeleteView;
import Views.HomePage;
import Views.SellView;
import Views.TransactionView;
import Views.UpdateView;
import Views.ViewAll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 *@author Anne Gitau
 */
public class ApplicationController implements ActionListener{
    HomePage applicationController;
    AddView newProduct;
    ViewAll indexView;
    UpdateView updateView;
    DeleteView deleteView;
    TableModel tableModel;
    TransactionView tView;
    SellView sView;
    Database db;
    public ApplicationController(){
        applicationController = new HomePage();
        tableModel = new TableModel();
        applicationController.getCakeTable().setModel(tableModel);
        newProduct = new AddView();
        indexView = new ViewAll();
        updateView = new UpdateView();
        deleteView = new DeleteView();
        tView = new TransactionView();
        sView = new SellView();
    }
    
    public void buttonControl() {
        applicationController.getAdd().addActionListener(this);
        applicationController.getDelete().addActionListener(this);
        applicationController.getUpdate().addActionListener(this);
        applicationController.getViewAll().addActionListener(this);
        applicationController.getAddMn().addActionListener(this);
        applicationController.getDeleteMn().addActionListener(this);
        applicationController.getUpdateMn().addActionListener(this);
        applicationController.getViewAllMn().addActionListener(this);
        applicationController.getTransaction().addActionListener(this);
        applicationController.getSell().addActionListener(this);
        applicationController.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==applicationController.getAdd()|| e.getSource()==applicationController.getAddMn()){
            AddProductController newP = new AddProductController(newProduct, 
            tableModel,applicationController);
            //applicationController.setVisible(false);
        }
        else if(e.getSource()==applicationController.getUpdate() || e.getSource()==applicationController.getUpdateMn()){
            UpdateController update = new UpdateController(updateView);
        }
        else if(e.getSource()==applicationController.getDelete() || e.getSource()==applicationController.getDeleteMn()){
            DeleteProductController delete = 
                    new DeleteProductController(deleteView);
        }
        else if(e.getSource()==applicationController.getViewAll()|| e.getSource()==applicationController.getViewAllMn()){
            indexController index = new indexController(indexView);
        }
        
       
        
        else if(e.getSource()==applicationController.getTransaction()){
            TransactionsController tController = new TransactionsController(tView);
        }
        
        else if(e.getSource()==applicationController.getSell()){
            //TransactionsController tController = new TransactionsController(tView);
            SellingController sController = new SellingController(sView);
        }
        
    }
    
}
