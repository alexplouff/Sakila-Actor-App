/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public class Actor_DAO implements Actor_DAO_Strategy {
    
    private SQL_Accessor sql_Accessor;
    private final static String TABLE = "actor.sakila";
    private final static String ALL_RECORDS_QUERY = "SELECT * FROM " + TABLE;
    private final static String PRIMARY_KEY_COLUMN = "actor_id";
    private final static String FIRST_NAME_COLUMN = "first_name";
    private final static String LAST_NAME_COLUMN = "last_name";
    private final static String NULL_REPLACEMENT_VALUE = "Not Entered";
    private final static String DIGIT_REG_EX = "\\d+";
    private static List COLUMNS;
    
    
    
    public Actor_DAO(SQL_Accessor accessor){
        setSql_Accessor(accessor);
        COLUMNS = new ArrayList<>();
        COLUMNS.add(PRIMARY_KEY_COLUMN);
        COLUMNS.add(FIRST_NAME_COLUMN);
        COLUMNS.add(LAST_NAME_COLUMN);
                
    }

    public SQL_Accessor getSql_accessor() {
        return sql_Accessor;
    }

    public final void setSql_Accessor(SQL_Accessor sql_Accessor) {
        this.sql_Accessor = sql_Accessor;
    }
    
    @Override
    public List<ActorStrategy> getAllActors() throws SQLException, ClassNotFoundException{
        List<ActorStrategy> actorList = null;
        try{
            actorList = new ArrayList<>(sql_Accessor.getRecords(ALL_RECORDS_QUERY));
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return actorList;
    }
    
    @Override
    public ActorStrategy getRecordByID(int id) throws SQLException, ClassNotFoundException{
        List<Map<String,Object>> recordValues = null;
        ActorStrategy actor = null;
        try{
            recordValues = 
                    new ArrayList(
                            sql_Accessor.getRecords(ALL_RECORDS_QUERY + " WHERE "
                               + PRIMARY_KEY_COLUMN + " = " + id));
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        for(Map<String,Object> value : recordValues){
            Object o = value.get("actor_id");
            String actorID = o.toString();
            o = value.get(FIRST_NAME_COLUMN);
            String firstName = (o == null) ? NULL_REPLACEMENT_VALUE : o.toString();
            o = value.get(LAST_NAME_COLUMN);
            String lastName = (o == null) ? NULL_REPLACEMENT_VALUE : o.toString();
            
            actor = new Actor(actorID, firstName, lastName);
        }
        
        return actor;
    }
    
    @Override
    public void createNewActor(ActorStrategy actor) throws SQLException, ClassNotFoundException{
        
        
        List<String> columns = new ArrayList<>();
        columns.add("first_name");
        columns.add("last_name");
        
        List values = new ArrayList<>();
        values.add(actor.getFirstName());
        values.add(actor.getLastName());
        try{
            sql_Accessor.createRecord(TABLE, columns, values);
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    @Override
    public void deleteRecordsFromDatabase(List primaryKeys) throws SQLException, ClassNotFoundException{
        
        /*
            If the application is written in such a way to prevent the user from
            entering an illegal character is it still worth looping through the
            data for the illegal characters?
        */
        
        if(primaryKeys.size() > 0){
            
        try{
            sql_Accessor.deleteRecords(TABLE, PRIMARY_KEY_COLUMN, primaryKeys);
        }
        catch (SQLException ex) {
                System.out.println(ex.getLocalizedMessage());
        }
        
        } else {
            throw new IllegalArgumentException("Possible Bad Value in P.K List");
        }
    }
    
    @Override
    public void updateRecord(ActorStrategy actor) throws SQLException, ClassNotFoundException{
        
        List values = new ArrayList();
        values.add(actor.getFirstName());
        values.add(actor.getLastName());
        
        try{
        sql_Accessor.updateRecord(TABLE, COLUMNS, values, PRIMARY_KEY_COLUMN, actor.getId());
        } 
        catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
