package ui;

import common.IBuilding;
import common.IBusiness;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class GraphsController implements Initializable {

    private IBusiness business;
    
    @FXML
    private Label nameL;
    
    @FXML
    private LineChart graph;
    
    @FXML
    private ComboBox buildingsCB;
    
    private ObservableList<IBuilding> buildings;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        business.loadBuildings();
        UI.getInstance().getStage().setTitle("Graphs");
        buildings = FXCollections.observableArrayList(business.getBuildings());
        buildingsCB.setItems(buildings);
        buildingsCB.getSelectionModel().selectFirst();
        System.out.println(business.getBuildings().get(0).getName());
    }

    public void addBuilding() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/AddBuilding.fxml"));
            Scene scene = new Scene(root);
            UI.getInstance().getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(GraphsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editBuilding() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/EditBuilding.fxml"));
            Scene scene = new Scene(root);
            UI.getInstance().getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(GraphsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeBuilding() {
        business.removeBuilding((IBuilding)buildingsCB.getValue());
    }
    
}
