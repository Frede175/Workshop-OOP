/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author fsr19
 */
public interface IBuilding {
    String getName();
    IAddress getAddress();
    ArrayList<ISensor> getSensors();
    void removeSensor(ISensor sensor);
    
    @Override
    String toString();
}
