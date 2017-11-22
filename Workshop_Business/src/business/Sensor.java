/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IReading;
import common.ISensor;
import common.SensorType;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Sensor implements ISensor {

    private int id;
    private SensorType type;
    private ArrayList<Reading> recordings = new ArrayList<Reading>();

    public Sensor(int id, SensorType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public ArrayList getRecords() {
        return recordings;
    }

    @Override
    public ArrayList<IReading> getRecordsByInterval(Date startDate, Date endDate) {
        ArrayList<IReading> recordsByInterval = new ArrayList<>();
        for (int i = 0; i < recordings.size(); i++) {
            if (recordings.get(i).getDate().compareTo(startDate) >= 0 && recordings.get(i).getDate().compareTo(endDate) <= 0) {
                recordsByInterval.add(recordings.get(i));
            }
            return recordsByInterval;
        }
        return null;
    }

    @Override
    public ArrayList<IReading> getRecordsByMeasure(double measure) {
        ArrayList<IReading> recordsByMeasure = new ArrayList<>();
            for (int i = 0; i < recordings.size(); i++) {
                if (recordings.get(i).getMeasure() == measure) {
                    recordsByMeasure.add(recordings.get(i));
                }
                return recordsByMeasure;
            }
        return null;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public void addReading(Date date, double measure) {
        recordings.add(new Reading(date, measure));
    }

}
