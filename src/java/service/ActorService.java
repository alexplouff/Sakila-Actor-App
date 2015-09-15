/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.ActorStrategy;

/**
 *
 * @author alex
 */
public class ActorService {
    
    private ActorStrategy actor;
    
    public ActorService(){}
    
    public ActorService(ActorStrategy actor){
        setActor(actor);
    }

    public ActorStrategy getActor() {
        return actor;
    }

    public final void setActor(ActorStrategy actor) {
        this.actor = actor;
    }
    
    
}
