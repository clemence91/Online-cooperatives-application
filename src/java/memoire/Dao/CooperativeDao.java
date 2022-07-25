
package memoire.Dao;

import java.util.ArrayList;
import java.util.List;
import memoire.Domain.Cooperative;
import org.hibernate.Query;
import org.hibernate.Session;


public class CooperativeDao extends GenericDao<Cooperative>{
     public List<Cooperative> findAllCooperatives() {
        Session s = SessionManager.getSession();
        Query q = s.createQuery("Select a from Cooperative a");
        List<Cooperative> list = q.list();
        s.close();
        return list;
    }
     

    public List<Cooperative> list() {
        Session s = SessionManager.getSession();
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.getDco()== null && r.isPermited() == true) {
                list.add(r);
            }
        }
        s.close();
        return list;
    }

    public List<Cooperative> pendingRequest(String nationId) {
        Session s = SessionManager.getSession();
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.getDco() == null) {
                if (String.valueOf(r.getCooperativeleader().getNationalId()).equals(nationId)) {
                    if (list.contains(r) == false) {
                        list.add(r);
                    }
                }
            }
        }
        s.close();
        return list;
    }

    public List<Cooperative> approvedCooperative(String nationId) {
        Session s = SessionManager.getSession();
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.getDco() != null && r.isPermited() == true) {
                if (String.valueOf(r.getCooperativeleader().getNationalId()).equals(nationId)) {
                    if (list.contains(r) == false) {
                        list.add(r);
                    }
                }
            }
        }
        s.close();
        return list;
    }

    public List<Cooperative> rejectedCooperative(String nationId) {
        Session s = SessionManager.getSession();
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.getDco()== null && r.isPermited() == false && r.isApproved() == true) {
                if (String.valueOf(r.getCooperativeleader().getNationalId()).equals(nationId)) {
                    if (list.contains(r) == false) {
                        list.add(r);
                    }
                }
            }
        }
        s.close();
         return list;
    }

    public List<Cooperative> notApprovedCooperative() {
        Session s = SessionManager.getSession();
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.isApproved() == false) {

                if (list.contains(r) == false) {
                    list.add(r);
                }

            }
        }
        s.close();
        return list;
    }
    
    public List<Cooperative> approveds() {
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative rr : findAllCooperatives()) {
            if (rr.isPermited() && rr.isApproved()) {
                if (list.contains(rr) == false) {
                    list.add(rr);
                }
            }
        }
        return list;
    }

    public List<Cooperative> rejecteds() {
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative rr : findAllCooperatives()) {
            if (rr.isPermited() == false && rr.isApproved()) {
                if (list.contains(rr) == false) {
                    list.add(rr);
                }
            }
        }
        return list;
    }

    public List<Cooperative> egides() {
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.isApproved() && r.isPermited()) {
                if (list.contains(r) == false) {
                    list.add(r);
                }
            }
        }
        return list;
    }

    public List<Cooperative> moses() {
        List<Cooperative> list = new ArrayList<>();
        for (Cooperative r : findAllCooperatives()) {
            if (r.isApproved() == true && r.isPermited() == false) {
                if (list.contains(r) == false) {
                    list.add(r);
                }
            }
        }
        return list;
    }
  
    
}
