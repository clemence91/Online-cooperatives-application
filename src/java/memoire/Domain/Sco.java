/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Niyigena Clemence
 */
@Entity
public class Sco {
     @Id
    private String nationalId;
   
    private String Fname;
    private String Lname;
    private String gender;
    private String password;
    private String username;
    private String access;
    @Column(unique = true)
    private String phoneNo;
    @Column(unique = true)
    private String email;
    private Date dob;
    @Transient
    private String action;
        private boolean state;
private String status;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  
   
    
    @OneToMany(mappedBy = "sco")
    private List<Cooperative> cooperative;
    
    @OneToMany(mappedBy = "sco")
    private List<Notification> notiffication;

   
 @OneToOne
    @JoinColumn(name = "Sector_id")
    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
   
    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<Cooperative> getCooperative() {
        return cooperative;
    }

    public void setCooperative(List<Cooperative> cooperative) {
        this.cooperative = cooperative;
    }

  
    public List<Notification> getNotiffication() {
        return notiffication;
    }

    public void setNotiffication(List<Notification> notiffication) {
        this.notiffication = notiffication;
    }
    
}
