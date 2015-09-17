/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import model.ActorStrategy;
import model.Actor_DAO_Strategy;
import model.Actor_DTO;

/**
 *
 * @author alex
 */
public class ActorService {
    
    private Actor_DTO dto;
    
    public ActorService(Actor_DTO dto){
        setDTO(dto);
    }

    public Actor_DTO getActorDTO() {
        return dto;
    }

    public final void setDTO(Actor_DTO dto) {
        this.dto = dto;
    }

    public void createActorRecord(List<String> values) throws SQLException, ClassNotFoundException{
        
        dto.createActorForDAO(values);
        
    }
    
    public void updateActorRecord(List<String> values) throws SQLException, ClassNotFoundException{
        dto.updateActorInDatabase(values);
    }
    
    public void deleteActorRecords(List<String> keys) throws SQLException, ClassNotFoundException{
        dto.deleteRecordsFromDatabase(keys);
    }
    
    public List<ActorStrategy> getAllActors() throws SQLException, ClassNotFoundException{
        return dto.getAllActors();
    }
    
    public ActorStrategy getActorByID(String id) throws SQLException, ClassNotFoundException{
        return dto.getActorById(id);
    }
    
    
    
}
