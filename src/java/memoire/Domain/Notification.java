/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Niyigena Clemence
 */
@Entity
public class Notification {
    @Id
    private String notificationNo;
    private Date notificationDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "dco_id")
    private Dco dco;
    
    @ManyToOne
    @JoinColumn(name = "sco_id")
    private Sco sco;
    
    @ManyToOne
    @JoinColumn(name = "rester_id")
    private Register register;

    @OneToOne
    @JoinColumn(name = "cooperativeleader_id")
    private Cooperativeleader cooperativeleader;

    public String getNotificationNo() {
        return notificationNo;
    }

    public void setNotificationNo(String notificationNo) {
        this.notificationNo = notificationNo;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dco getDco() {
        return dco;
    }

    public void setDco(Dco dco) {
        this.dco = dco;
    }

    public Sco getSco() {
        return sco;
    }

    public void setSco(Sco sco) {
        this.sco = sco;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Cooperativeleader getCooperativeleader() {
        return cooperativeleader;
    }

    public void setCooperativeleader(Cooperativeleader cooperativeleader) {
        this.cooperativeleader = cooperativeleader;
    }

    
}
