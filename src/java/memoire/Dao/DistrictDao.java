/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Dao;

import memoire.Domain.District;
import org.hibernate.Session;



/**
 *
 * @author Niyigena Clemence
 */
public class DistrictDao extends GenericDao<District>{
    public District OneDistrict(String prov){
        Session ss = SessionManager.getSession();
        District provi = (District) ss.get(District.class, prov);
        ss.close();
        return provi;
    }   
}
