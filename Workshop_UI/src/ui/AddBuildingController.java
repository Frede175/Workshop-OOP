package ui;

import common.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class AddBuildingController implements Initializable {

    private IBusiness business;
    
    @FXML
    private TextField nameTF;
    
    @FXML
    private TextField streetnameTF;
    
    @FXML
    private TextField houseNumberTF;
    
    @FXML
    private TextField zipcodeTF;
    
    @FXML
    private TextField countryTF;
    
    @FXML
    private Label error;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        UI.getInstance().getStage().setTitle("Add Building");
    }   
    
    public void create() {
        if(!nameTF.getText().isEmpty() && !streetnameTF.getText().isEmpty() && !houseNumberTF.getText().isEmpty() && !zipcodeTF.getText().isEmpty() && !countryTF.getText().isEmpty()) {
            business.createBuilding(nameTF.getText().toString(), streetnameTF.getText().toString(), houseNumberTF.getText().toString(), zipcodeTF.getText().toString(), countryTF.getText().toString());
            business.saveBuildings();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("fxml/Graphs.fxml"));
                Scene scene = new Scene(root);
                UI.getInstance().getStage().setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(AddBuildingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            error.setOpacity(1);
        }
    }
    
}
