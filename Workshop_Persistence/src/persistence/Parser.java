/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fsr19
 */
public class Parser {
    
    private Gson gson;
    
    private String fileName = "data.txt";
    
    public Parser() {
        gson = new Gson();
    }
    
    public boolean writeObject(Type type, Object obj) {
        String json = gson.toJson(obj, type);
        
        File file = new File(fileName);
        
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public Object loadObject(Type type) {
        Object obj = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bfReader = new BufferedReader(fileReader);
            
            String json = "";
            String line;
            while((line = bfReader.readLine()) != null) {
                json += line;
            }   
            
            obj = gson.fromJson(json, type);
        } catch (FileNotFoundException ex) { } catch (IOException ex) { } 
        
        return obj;
    }
}
