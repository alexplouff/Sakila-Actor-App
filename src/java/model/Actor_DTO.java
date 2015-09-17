/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class Actor_DTO {

    private Actor_DAO_Strategy dao;

    public Actor_DTO() {
        setDao(dao);
    }

    public Actor_DAO_Strategy getDao() {
        return dao;
    }

    public final void setDao(Actor_DAO_Strategy dao) throws IllegalArgumentException {
        if (dao != null) {
            this.dao = dao;
        } else {
            throw new IllegalArgumentException("DAO must be passed in.");
        }
    }

    public void createActorForDAO(List<String> values) throws SQLException, ClassNotFoundException {
        if (values.size() < 1) {
            throw new IllegalArgumentException("Not Enough Data To Create A Record");
        } else {
            ActorStrategy actor = new Actor(null, values.get(0), values.get(1));
            dao.createNewActor(actor);
        }
    }

    public void deleteRecordsFromDatabase(List<String> str_PrimaryKeys) throws SQLException, ClassNotFoundException {
        if (str_PrimaryKeys.size() < 1) {
            throw new IllegalArgumentException("Empty Delete List");
        } else {

            List digitized_primaryKeys = new ArrayList<>();

            for (String key : str_PrimaryKeys) {
                if (key.matches("\\d+")) {
                    digitized_primaryKeys.add(Double.valueOf(key));
                } else {
                    throw new IllegalArgumentException("Records Were Not Deleted");
                }
                
            }
            dao.deleteRecordsFromDatabase(digitized_primaryKeys);
        }
    }
    
    public void updateActorInDatabase(List<String> values) throws SQLException, ClassNotFoundException, IllegalArgumentException{
        if(values.size() < 1){
            throw new IllegalArgumentException("Empty Update List");
        } else {
            
            ActorStrategy actor = new Actor(values.get(0), values.get(1), values.get(2));
                                          // firstName       lastName          ID / PK
            dao.updateRecord(actor);
        }
        
    }
    
    public List<ActorStrategy> getAllActors() throws SQLException, ClassNotFoundException{
        return dao.getAllActors();
    }
    
    public ActorStrategy getActorById(String actorID) throws SQLException, ClassNotFoundException, IllegalArgumentException{
        if(actorID.matches("\\d+")){
            return dao.getRecordByID(Integer.valueOf(actorID));
        }
        else {
            throw new IllegalArgumentException("Please Enter A Number");
        }
    }
}
