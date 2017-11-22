/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author fsr19
 */
public interface ISensor {
    ArrayList<IReading> getRecords();
    ArrayList<IReading> getRecordsByInterval(Date startDate, Date endDate);
    ArrayList<IReading> getRecordsByMeasure(double measure);
}
