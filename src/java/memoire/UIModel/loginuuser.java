/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.UIModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import memoire.Dao.Dcodao;
import memoire.Dao.RegisterDao;
import memoire.Dao.Scodao;
import memoire.Domain.Dco;
import memoire.Domain.Register;
import memoire.Domain.Sco;


/**
 *
 * @author Niyigena Clemence
 */
@SessionScoped
@ManagedBean(name="log")
public class loginuuser {
    private Dcodao  dcodao = new Dcodao();
     private Scodao scodao = new Scodao();
      private RegisterDao  regdao = new RegisterDao();
      private String username;
    private String password;
    private Dco dco= new Dco();
    private Sco sco = new Sco();
    private Register register = new Register();
     public String loginusers() {
         
          
        try {
            Dco dco= new Dcodao().findDcobyUsername(username);
            Sco sco= new Scodao().findScobyUsername(username);
            Register register=new RegisterDao().findregisterbyUsername(username);
            if (dco != null) {
                if (dco.getPassword().equals(password) && dco.getAccess().contains("Dco")) {
                    this.dco = dco;
                    return "districtOfficerApprover";
                }
                if (sco.getPassword().equals(password) && sco.getAccess().contains("Sco")) {
                    this.sco = sco;
                    return "SectorApprover";
                }
                if (register.getPassword().equals(password) && register.getAccess().contains("Admin")) {
                    this.register = register;
                    return "AdminApprover";
                }
            }
        } catch (Exception ex) {

        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "Login";
    }

    public Dcodao getDcodao() {
        return dcodao;
    }

    public void setDcodao(Dcodao dcodao) {
        this.dcodao = dcodao;
    }

    public Scodao getScodao() {
        return scodao;
    }

    public void setScodao(Scodao scodao) {
        this.scodao = scodao;
    }

    public RegisterDao getRegdao() {
        return regdao;
    }

    public void setRegdao(RegisterDao regdao) {
        this.regdao = regdao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
}
