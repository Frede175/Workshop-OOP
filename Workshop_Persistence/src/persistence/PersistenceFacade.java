/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IPersistence;
import java.lang.reflect.Type;

/**
 *
 * @author fsr19
 */
public class PersistenceFacade implements IPersistence {

    private Parser parser;
    
    public PersistenceFacade() {
        parser = new Parser();
    }
    
    
    @Override
    public boolean save(Type type, Object obj) {
        return parser.writeObject(type, obj);
    }

    @Override
    public Object load(Type type) {
        return parser.loadObject(type);
    }
    
}
