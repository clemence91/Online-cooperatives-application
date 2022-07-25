/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import java.util.List;
import memoire.Domain.Cooperative;
import memoire.Domain.District;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class GenericDao<C> {
     public void create(C c){
        Session ss = SessionManager.getSession();
        ss.beginTransaction();
        ss.save(c);
        ss.getTransaction().commit();
        ss.close();
    }
    public void Update(C c){
        Session ss = SessionManager.getSession();
        ss.beginTransaction();
        ss.update(c);
        ss.getTransaction().commit();
        ss.close();
    }
    public void delete(C c){
        Session ss = SessionManager.getSession();
        ss.beginTransaction();
        ss.delete(c);
        ss.getTransaction().commit();
        ss.close();
    }
     public List<C> getAll(String query) {
        Session s = SessionManager.getSession();
        List<C> list = s.createQuery(query).list();
        s.close();
        return list;
    }
    
    public List<Cooperative> getAllBy(String query) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("SELECT c From Cooperative c WHERE c.sector=?");
        q.setString(0, query);
        List<Cooperative> list = q.list();
        s.close();
        return list;
    }
    public List<District> findAllDistricts(){
        Session s = SessionManager.getSession();
        Query q = s.createQuery("SELECT c From District c");
        List<District> list = q.list();
        s.close();
        return list;
    }
    public List<Cooperative> getAllByDistrict(String query) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("SELECT c From Cooperative c WHERE c.district=?");
        q.setString(0, query);
        List<Cooperative> list = q.list();
        s.close();
        return list;
    }
}
