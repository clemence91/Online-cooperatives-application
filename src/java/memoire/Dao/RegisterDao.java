/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import java.util.ArrayList;
import java.util.List;
import memoire.Domain.Register;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Niyigena Clemence
 */
public class RegisterDao extends GenericDao<Register>{
    public Register getOneBuId(String register) {
        Session ss = SessionManager.getSession();
         Register  c = ( Register) ss.get( Register.class,  register);
        ss.close();
        return c;
    }
 public Register loginreg(String username, String password) {
         Session ss = SessionManager.getSession();
        Query q = ss.createQuery("select a from Register a where a.username=? and a.password=?");
        q.setString(0, username);
        q.setString(1, password);
        return (Register) q.uniqueResult();
        
    }  
   public Register findREgister(String phoneNumber) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Register s where s.phoneNo=?");
        q.setString(0, phoneNumber);
        Register ss = (Register) q.uniqueResult();
        s.close();
        return ss;
    }
    public Register findregisterbyUsername(String nationalId) {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("from Register s where s.username=?");
        q.setString(0, nationalId);
        Register ss = (Register) q.uniqueResult();
        s.close();
        return ss;
    }
        public List<Register> findAllRegisters() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("Select a from Register a");
        List<Register> list =q.list();
        s.close();
        return list;
    }
//         
         public List<Register> GetRegister() {
        Session c = SessionManager.getSession();
        Query q = c.createQuery("from Register");
        List<Register> li = q.list();
        c.close();
        return li;

    }

    public List<Register> get1() {
        List<Register> li = new ArrayList<>();
        for (Register rt : new RegisterDao().GetRegister()) {
            if (rt.getStatus().equalsIgnoreCase("ACTIVE")) {
                rt.setAction("Block");
            } else {
                rt.setAction("Activate");
                rt.setStatus("Blocked");
            }
            li.add(rt);
        }
        return li;
    }
     
}
