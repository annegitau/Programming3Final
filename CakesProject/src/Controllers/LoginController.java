/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Database;
import Views.HomePage;
import Views.LoginView;
import Views.ViewAll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Anne Gitau
 */
public class LoginController implements ActionListener {
    /*   AddItem addIt= new AddItem();
    AddItemController addCo= new AddItemController(addIt);*/

    HomePage hp;
    Database dt;
    LoginView login;
    Statement st=null;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
     
    public LoginController(LoginView lv){
        this.login=lv;
    }

    public LoginController() {
        hp = new HomePage();
        dt = new Database();
        
    }
    
    public void control(){
        login.getSubmit().addActionListener(this);
        login.getCancel().addActionListener(this);
        login.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
         
        HomePage h=new HomePage();
        ViewAll vAll=new ViewAll();
       indexController stc=new indexController(vAll);
                  
       if (ae.getSource()==login.getCancel()){
           System.exit(0);
       }
       if (ae.getSource()==login.getSubmit()){
           
           String Sql="select * from admin where username='"+login.getUsername()+
             "' and lecturer='"+login.getPassword()+"'";
            
            stc.control();
            h.setVisible(true);
            login.setVisible(false);
            dt.login(login.getUsername(),login.getPassword());
       
       }
    }
}
