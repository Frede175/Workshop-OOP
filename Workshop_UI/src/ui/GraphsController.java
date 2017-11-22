package ui;

import common.IBuilding;
import common.IBusiness;
import common.IReading;
import common.ISensor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    
    private IBuilding currentBuilding;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        business.loadBuildings();
        UI.getInstance().getStage().setTitle("Graphs");
        buildings = FXCollections.observableArrayList(business.getBuildings());
        buildingsCB.setItems(buildings);
        buildingsCB.getSelectionModel().selectFirst();
        currentBuilding = (IBuilding) buildingsCB.getValue();
        reload();
        buildingsCB.valueProperty().addListener(new javafx.beans.value.ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue != null) {
                    currentBuilding = (IBuilding) newValue;
                } else {
                    currentBuilding = (IBuilding) business.getBuildings().get(0);
                }
                reload();
            }
        });
    }
    
    public void reload() {
        buildings = FXCollections.observableArrayList(business.getBuildings());
        buildingsCB.setItems(buildings);
        if(currentBuilding == (IBuilding) business.getBuildings().get(0)) {
            buildingsCB.getSelectionModel().selectFirst();
        }
        nameL.setText(currentBuilding.getName());
        loadGraph();
    }
    
    public void loadGraph() {
        graph.getData().clear();
        for(ISensor sensor : currentBuilding.getSensors()) {
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.setName(String.valueOf(sensor.getID()) + " (" + sensor.getType().toString() + ")");
            for(IReading reading : sensor.getRecords()) {
                series.getData().add(new XYChart.Data<String, Number>(reading.getDate().toString(), reading.getMeasure()));
            }
            graph.getData().add(series);
        }
        
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/EditBuilding.fxml"));
            Parent root = loader.load();
            EditBuildingController controller = loader.getController();
            controller.setBuilding(currentBuilding);
            controller.setup();
            Scene scene = new Scene(root);
            UI.getInstance().getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(GraphsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeBuilding() {
        business.removeBuilding(currentBuilding);
        if(!business.getBuildings().isEmpty()) {
            buildingsCB.getSelectionModel().select(0);
            reload();
        } else {
            addBuilding();
        }
    }
    
    
}
