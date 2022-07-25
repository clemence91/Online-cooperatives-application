/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Niyigena Clemence
 */
@Entity
public class Province {
    @Id
    private String poviname;
@OneToMany(mappedBy = "province")
    private List<District> ditrictpro;

    public List<District> getDitrictpro() {
        return ditrictpro;
    }

    public void setDitrictpro(List<District> ditrictpro) {
        this.ditrictpro = ditrictpro;
    }

    public String getPoviname() {
        return poviname;
    }

    public void setPoviname(String poviname) {
        this.poviname = poviname;
    }

    public Province(String poviname) {
        this.poviname = poviname;
    }

    public Province() {
    }
    
    
}
