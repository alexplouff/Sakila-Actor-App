/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alex
 */
public interface Actor_DAO_Strategy {

    public abstract void createNewActor(ActorStrategy actor) throws SQLException, ClassNotFoundException;

    public abstract void deleteRecordsFromDatabase(List primaryKeys) throws SQLException, ClassNotFoundException;

    public abstract List<ActorStrategy> getAllActors() throws SQLException, ClassNotFoundException;

    public abstract void updateRecord(ActorStrategy actor) throws SQLException, ClassNotFoundException;
    
    public abstract ActorStrategy getRecordByID(int id) throws SQLException, ClassNotFoundException;
}
