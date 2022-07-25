/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.List;
import javax.persistence.Column;
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
public class District {

    @Id
    @Column(nullable = false, unique = true)
    private String distname;
    @OneToMany(mappedBy = "district")
    private List<Sector> secto;
    @ManyToOne
    @JoinColumn(name = "provincename")
    private Province province;

    @OneToOne(mappedBy = "district")
    private Dco dco;
    @OneToMany(mappedBy = "district")
    private List<Cooperative> cooperative;

    public Province getProvince() {
        return province;
    }

    public District(String distname) {
        this.distname = distname;
    }

    public District() {
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Cooperative> getCooperative() {
        return cooperative;
    }

    public void setCooperative(List<Cooperative> cooperative) {
        this.cooperative = cooperative;
    }

    public Dco getDco() {
        return dco;
    }

    public void setDco(Dco dco) {
        this.dco = dco;
    }

    public String getDistname() {
        return distname;
    }

    public void setDistname(String distname) {
        this.distname = distname;
    }

    public List<Sector> getSecto() {
        return secto;
    }

    public void setSecto(List<Sector> secto) {
        this.secto = secto;
    }

}
