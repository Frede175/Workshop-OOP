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
public enum SensorType {
    TEMPERATURE("Temperature", "Celsius"),
    CO2("CO2", "PPM");
    
    private String name;
    private String unit;

    private SensorType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
    
    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
    
}
