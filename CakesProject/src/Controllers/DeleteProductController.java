package Controllers;

import Models.Database;
import Views.DeleteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Anne Gitau
 */
public class DeleteProductController implements ActionListener{
    DeleteView deleteForm;
    Database db;    
    String cake;
    
    public DeleteProductController(DeleteView form){
        deleteForm = form;
        db = Database.getDatabaseInstance();
        db.connectToDatabase();
        formControl();
    }
    
    public void formControl(){
        deleteForm.setVisible(true);
        deleteForm.getFind().addActionListener(this);
        deleteForm.getDelete().addActionListener(this);
        deleteForm.getCancel().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deleteForm.getFind()){
            System.out.println("Find");
            String id = deleteForm.getItemId();
            String[] product = db.getProduct(id);
            cake = product[1];
            deleteForm.setCake(product[1]);
            deleteForm.setsize(product[2]);
            deleteForm.setQuantity(product[3]);
            deleteForm.setPrice(product[4]);
        }
        
        else if(e.getSource()==deleteForm.getDelete()){
            String id = deleteForm.getItemId();
            db.deleteProduct(id);
            deleteForm.setVisible(false);
            
            JOptionPane.showMessageDialog(null,
                "Deleted cake: " + cake,
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);
        }
        
        else if(e.getActionCommand().equalsIgnoreCase("CANCEL")){
            deleteForm.setVisible(false);
        }
    }
}
