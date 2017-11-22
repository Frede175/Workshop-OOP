/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IAddress;
import common.IBuilding;
import common.ISensor;
import common.SensorType;
import java.util.ArrayList;

/**
 *
 * @author fsr19
 */
public class Building implements IBuilding {
    
    private ArrayList<Sensor> sensors = new ArrayList<>();
    private String name;
    private Address address;

    public Building(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public IAddress getAddress() {
        return address;
    }
    
    
    public void removeSensor(ISensor sensor) {
        int i = sensors.indexOf(sensor);
        if (i != -1) {
           sensors.remove(i);
        }
    }

    public void addSensor(int id, SensorType type) {
        Sensor sensor = new Sensor(id, type);
        sensors.add(sensor);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    
}
