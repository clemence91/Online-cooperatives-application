/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import java.util.ArrayList;
import java.util.List;
import memoire.Domain.Dco;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class Dcodao extends GenericDao<Dco>{
  public Dco getOnedcoBuId(String dco) {
        Session ss = SessionManager.getSession();
        Dco c = (Dco) ss.get(Dco.class, dco);
        ss.close();
        return c;
    }

public Dco findDco(String phoneNumber) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Dco s where s.phoneNo=?");
        q.setString(0, phoneNumber);
        Dco ss = (Dco) q.uniqueResult();
        s.close();
        return ss;
    }
    public Dco findDcobyUsername(String username ) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Dco s where s.username=? " );
        q.setString(0, username);
       
        Dco ss = (Dco) q.uniqueResult();
        s.close();
        return ss;
    }
        public List<Dco> findAllDco() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("Select a from Dco a");
        List<Dco> list =q.list();
        s.close();
        return list;
    }  
         public Dco logindco(String username1, String password1) {
         Session ss = SessionManager.getSession();
        Query q = ss.createQuery("select a from Dco a where a.username=?");
        q.setString(0, username1);
        q.setString(1, password1);
        return (Dco) q.uniqueResult();
        
    }  
//          
          public List<Dco> GetDco() {
        Session c = SessionManager.getSession();
        Query q = c.createQuery("from Dco");
        List<Dco> list = q.list();
        c.close();
        return list;

    }

    public List<Dco> get2() {
        List<Dco> list = new ArrayList<>();
        for (Dco t : new Dcodao().GetDco()) {
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
