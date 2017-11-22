/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author fsr19
 */
public interface IBusiness {
    void injectPersistence(IPersistence persistence);
    void createBuilding(String name, String street, String number, String zipCode, String country);
    void createSensor(SensorType type, int id);
    void removeBuilding(IBuilding building);
    void removeSensor(IBuilding building, ISensor sensor);
}