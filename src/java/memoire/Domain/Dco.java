
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
public class Dco {
     @Id
    private String nationalId;
 
    private String Fname;
    private String Lname;
    private String gender;
  private String password;
  private String username;
  
private String status;

   
    @Column(unique = true)
    private String phoneNo;
    @Column(unique = true)
    private String email;
    private Date dob;
        private boolean state;
        @Transient
private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @OneToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    
    @OneToMany(mappedBy = "dco")
    private List<Cooperative> cooperative;
    
    @OneToMany(mappedBy = "dco")
    private List<Notification> notiffication;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
 public String getPassword() {
        return password;
    }
 
private String access;
 
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
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
   
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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
