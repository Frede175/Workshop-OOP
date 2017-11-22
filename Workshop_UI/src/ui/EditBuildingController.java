package ui;

import common.IBuilding;
import common.IBusiness;
import common.ISensor;
import common.SensorType;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class EditBuildingController implements Initializable {

    private IBusiness business;
    
    private IBuilding building;
    
    @FXML
    private Label nameL;
    
    @FXML
    private TextField nameTF;

    @FXML
    private TableView sensorsTV;
    
    @FXML
    private RadioButton tempT;
    
    @FXML
    private RadioButton co2T;
    
    @FXML
    private ToggleGroup typeGroup;
    
    @FXML
    private TableColumn nameC;
    
    @FXML
    private TableColumn typeC;
    
    @FXML
    private Button removeBtn;
    
    private final String column1MapKey = "id";
    
    private final String column2MapKey = "type";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
    }
    
    public void setup() {
        nameC.setCellValueFactory(new MapValueFactory(column1MapKey));
        typeC.setCellValueFactory(new MapValueFactory(column2MapKey));
        nameL.setText(building.getName());
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
            cellFactoryForMap = (TableColumn<Map, String> p) -> 
                new TextFieldTableCell(new StringConverter() {
                    @Override
                        public String toString(Object t) {
                        return t.toString();
                    }
                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
            });
        nameC.setCellFactory(cellFactoryForMap);
        typeC.setCellFactory(cellFactoryForMap);
        reload();
    }
    
    private void reload() {
        // TO DO reload sensors into tableview
        ObservableList<Map> allData = FXCollections.observableArrayList();
        if(!building.getSensors().isEmpty()) {
            removeBtn.setDisable(false);
            for(ISensor sensor : building.getSensors()) {
                Map<String, String> dataRow = new HashMap<>();
                
                dataRow.put(column1MapKey, String.valueOf(sensor.getID()));
                dataRow.put(column2MapKey, String.valueOf(sensor.getType()));
                
                allData.add(dataRow);
            }
        } else {
            removeBtn.setDisable(true);
        }
        sensorsTV.setItems(allData);
    }
    
    public void setBuilding(IBuilding building) {
        this.building = building;
    }
    
    private int getNextSensorID() {
        int nextID = 0;
        for(ISensor sensor : building.getSensors()) {
            if(nextID < sensor.getID()) {
                nextID = sensor.getID();
            }
        }
        return nextID + 1;
    }
    
    public void create() {
        if(tempT.isSelected()) {
            building.addSensor(getNextSensorID(), SensorType.TEMPERATURE);
        } else {
            building.addSensor(getNextSensorID(), SensorType.CO2);
        }
        business.saveBuildings();
        reload();
    }
    
    public void remove() {
        if(sensorsTV.getSelectionModel().getSelectedIndex() != -1) {
            business.removeSensor(building, building.getSensors().get(sensorsTV.getSelectionModel().getSelectedIndex()));
            sensorsTV.getSelectionModel().selectLast();
            reload();
        } else {
            business.removeSensor(building, building.getSensors().get(building.getSensors().size() - 1));
            sensorsTV.getSelectionModel().selectLast();
            reload();
        }
    }
    
    public void close() {
        business.saveBuildings();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Graphs.fxml"));
            Scene scene = new Scene(root);
            UI.getInstance().getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(AddBuildingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
