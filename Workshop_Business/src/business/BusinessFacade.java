/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IBuilding;
import common.IBusiness;
import common.IPersistence;
import common.ISensor;
import common.SensorType;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessFacade implements IBusiness {

    private ArrayList<Building> buildings = new ArrayList<>();
    private IPersistence persistence;

    @Override
    public void injectPersistence(IPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public void createBuilding(String name, String street, String number, String zipCode, String country) {
        Address address = new Address(street, number, zipCode, country);
        Building building = new Building(name, address);
        buildings.add(building);
        saveBuildings();
    }

    @Override
    public void removeBuilding(IBuilding building) {
        int i = buildings.indexOf(building);
        if (i != -1) {
            buildings.remove(i);
            
        }

    }

    @Override
    public void removeSensor(IBuilding building, ISensor sensor) {
        int i = buildings.indexOf(building);
        if (i != -1) {
           buildings.get(i).removeSensor(sensor);
           saveBuildings();
        }
    }

    @Override
    public void createSensor(IBuilding building, SensorType type, int id) {
        int i = buildings.indexOf(building);
        if (i != -1) {
           buildings.get(i).addSensor(id, type);
           saveBuildings();
        }
    }

    @Override
    public ArrayList<? extends IBuilding> getBuildings() {
        return buildings;
    }

    @Override
    public void loadBuildings() {
        buildings = (ArrayList<Building>) persistence.load(buildings.getClass());
    }

    @Override
    public void saveBuildings() {
        persistence.save(buildings);
    }

    
}
