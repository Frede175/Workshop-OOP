/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IReading;
import java.util.Date;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Reading implements IReading {
    private Date date;
    private double measure;

    
    public Reading(Date date, double measure) {
        this.date = date;
        this.measure = measure;
    }
    
    @Override
    public Date getDate() {
       return date;
    }

    @Override
    public double getMeasure() {
        return measure;
    }
    
}
