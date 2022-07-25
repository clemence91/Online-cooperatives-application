/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Niyigena Clemence
 */
@Entity
public class Sector {
    @Id
    private String sectname;
    @OneToMany(mappedBy = "sector")
    private List<Cooperativeleader> leader;
    @OneToOne(mappedBy = "sector")
    private Sco sco;
    
    @OneToMany(mappedBy = "sector")
    private List<Cooperative> cooperative;
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    public Sector(String sectname) {
        this.sectname = sectname;
    }

    public Sector() {
    }

    public Sco getSco() {
        return sco;
    }

    public void setSco(Sco sco) {
        this.sco = sco;
    }

    public String getSectname() {
        return sectname;
    }

    public void setSectname(String sectname) {
        this.sectname = sectname;
    }

    public List<Cooperativeleader> getLeader() {
        return leader;
    }

    public void setLeader(List<Cooperativeleader> leader) {
        this.leader = leader;
    }

    

    
    public List<Cooperative> getCooperative() {
        return cooperative;
    }

    public void setCooperative(List<Cooperative> cooperative) {
        this.cooperative = cooperative;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    
}
