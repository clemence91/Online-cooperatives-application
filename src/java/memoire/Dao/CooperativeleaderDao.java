/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import java.util.List;
import memoire.Domain.Cooperativeleader;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class CooperativeleaderDao extends GenericDao<Cooperativeleader>{
    public Cooperativeleader getOne(String id){
        Session ss = SessionManager.getSession();
        Cooperativeleader coople = (Cooperativeleader) ss.get(Cooperativeleader.class, id);
        ss.close();
        return coople;
    }
    public List<Cooperativeleader> findAllCooperativeleader() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("Select a from Cooperativeleader a");
        List<Cooperativeleader> list =q.list();
        s.close();
        return list;
    }

    public Cooperativeleader getCooperativeleaderById(String ccop) {
        Session ss = SessionManager.getSession();
        Cooperativeleader c = (Cooperativeleader) ss.get(Cooperativeleader.class, ccop);
        ss.close();
        return c;
    }

    public Cooperativeleader findCooperativeleader(String username) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Cooperativeleader j where j.username=?");
        q.setString(0, username);
        Cooperativeleader j = (Cooperativeleader) q.uniqueResult();
        s.close();
        return j;
    }
}
