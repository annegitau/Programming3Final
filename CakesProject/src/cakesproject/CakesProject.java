/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cakesproject;

import Controllers.ApplicationController;
import Models.Database;

/**
 *
 * @author Anne Gitau
 */
public class CakesProject {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {        
        ApplicationController cake = new ApplicationController();
        cake.buttonControl();
        Database db = Database.getDatabaseInstance();
        db.connectToDatabase();        
    }
    
}
