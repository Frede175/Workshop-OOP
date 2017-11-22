/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starter;

import business.BusinessFacade;
import common.IBusiness;
import common.IPersistence;
import common.IUI;
import persistence.PersistenceFacade;
import ui.UI;

/**
 *
 * @author fsr19
 */
public class Workshop_Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IPersistence persistence = new PersistenceFacade();
        
        IBusiness business = new BusinessFacade();
        
        business.injectPersistence(persistence);
        
        IUI ui = new UI();
        
        ui.injectBusiness(business);
        ui.startApplication(args);
    }
    
    
}
