package memoire.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    public static SessionFactory f = null;
    public SessionManager(){
        
    }
    public static Session getSession(){
        if(f==null){
            Configuration c = new Configuration().configure();
            f=c.buildSessionFactory();
        }
        return f.openSession();
    }
}
