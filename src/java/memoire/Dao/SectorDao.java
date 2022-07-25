/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import memoire.Domain.Sector;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class SectorDao extends GenericDao<Sector>{
   public Sector OneSector(String id){
        Session ss = SessionManager.getSession();
        Sector di = (Sector) ss.get(Sector.class, id);
        ss.close();
        return di;
    }   
}
