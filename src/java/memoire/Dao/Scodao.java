/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import java.util.ArrayList;
import java.util.List;
import memoire.Domain.Cooperative;
import memoire.Domain.Sco;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class Scodao extends GenericDao<Sco>{
    public Sco getScoBuId(String sco) {
        Session ss = SessionManager.getSession();
        Sco c = (Sco) ss.get(Sco.class, sco);
        ss.close();
        return c;
    }
 
   public Sco findSco(String phoneNumber) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Sco s where s.phoneNo=?");
        q.setString(0, phoneNumber);
        Sco ss = (Sco) q.uniqueResult();
        s.close();
        return ss;
    }
    public Sco findScobyUsername(String username) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Sco s where s.username=?");
        q.setString(0, username);
       Sco ss = (Sco) q.uniqueResult();
        s.close();
        return ss;
    }
   
        public List<Sco> findAllSco() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("Select a from Sco a");
        List<Sco> list =q.list();
        s.close();
        return list;
    }
         public Sco loginsco(String username2, String password2) {
         Session ss = SessionManager.getSession();
        Query q = ss.createQuery("select a from Sco a where a.username=? and a.password=?");
        q.setString(0, username2);
        q.setString(1, password2);
        return (Sco) q.uniqueResult();
        
    }  

        public List<Cooperative> findCooperativebySector() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Cooperative s where s.sector=? ");
        List<Cooperative> list =q.list();
        s.close();
        return list;
    }
//         
         public List<Sco> GetSco() {
        Session c = SessionManager.getSession();
        Query q = c.createQuery("from Sco");
        List<Sco> list = q.list();
        c.close();
        return list;

    }

    public List<Sco> get3() {
        List<Sco> list = new ArrayList<>();
        for (Sco t : new Scodao().GetSco()) {
            if (t.getStatus().equalsIgnoreCase("ACTIVE")) {
                t.setAction("Block");
            } else {
                t.setAction("Activate");
                t.setStatus("Blocked");
            }
            list.add(t);
        }
        return list;
    }
        
    
}
