/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fsr19
 */
public class UI extends Application implements IUI{
    
    private static UI ui;
    
    public static UI getInstance(){
        return ui;
    }
    
    private IBusiness business;
    
    private Stage stage;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        UI.getInstance().stage = primaryStage;
        UI.getInstance().getBusiness().loadBuildings();
        Parent root;
        if(UI.getInstance().getBusiness().getBuildings() != null && UI.getInstance().getBusiness().getBuildings().isEmpty()) {
            root = FXMLLoader.load(getClass().getResource("fxml/AddBuilding.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("fxml/Graphs.fxml"));
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void injectBusiness(IBusiness business) {
        this.business = business;
    }

    @Override
    public void startApplication(String[] args) {
        ui = this;
        launch(args);
    }

    IBusiness getBusiness() {
        return business;
    }
    
    Stage getStage() {
        return stage;
    }
    
}
