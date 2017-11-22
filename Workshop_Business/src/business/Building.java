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
import java.util.Date;

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
    
    @Override
    public void removeSensor(ISensor sensor) {
        int i = sensors.indexOf(sensor);
        if (i != -1) {
           sensors.remove(i);
        }
    }

    public void addSensor(int id, SensorType type) {
        Sensor sensor = new Sensor(id, type);
        sensor.addReading(new Date(2017, 11, 22), 10 * Math.random());
        sensor.addReading(new Date(2017, 11, 23), 20 * Math.random());
        sensors.add(sensor);
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public ArrayList<ISensor> getSensors() {
        ArrayList<ISensor> sensors = (ArrayList<ISensor>) this.sensors.clone();
        return sensors;
    }

    public void addReading(ISensor sensor, Date date, double measure) {
        int i = sensors.indexOf(sensor);
        if (i != -1) {
           sensors.get(i).addReading(date, measure);
        }
    }
    
    
}
