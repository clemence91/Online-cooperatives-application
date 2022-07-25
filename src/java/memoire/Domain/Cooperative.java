/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoire.Domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author Niyigena Clemence
 */
@Entity
public class Cooperative {

    @Id
    private String requestno;
    private Date requestDate;
    private Date approvalDate;

    private Date consideredDate;

    @Column(unique = true, nullable = true)
    private String tinno;
    @Column(unique = true, nullable = true)
    private String name;
    private Date regdate;
   

   

    private String activity;
    private String mission;
    private String vision;
    private String investement;
    private String membernumber;
    private String type;
    private String fileName;
    private boolean approved;
    private boolean permited;
    private String comment;
@ManyToOne
@JoinColumn(name="District_name")
private District district;
    @ManyToOne
    @JoinColumn(name = "dco_id")
    private Dco dco;


    @ManyToOne
    @JoinColumn(name = "sco_id")
    private Sco sco;
    
    @ManyToOne
    @JoinColumn(name = "Sector_id")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "cooperativeleader_id")
    private Cooperativeleader cooperativeleader;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }



    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getConsideredDate() {
        return consideredDate;
    }

    public void setConsideredDate(Date consideredDate) {
        this.consideredDate = consideredDate;
    }

    public String getTinno() {
        return tinno;
    }

    public void setTinno(String tinno) {
        this.tinno = tinno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getInvestement() {
        return investement;
    }

    public void setInvestement(String investement) {
        this.investement = investement;
    }

    public String getMembernumber() {
        return membernumber;
    }

    public void setMembernumber(String membernumber) {
        this.membernumber = membernumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isPermited() {
        return permited;
    }

    public void setPermited(boolean permited) {
        this.permited = permited;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Cooperativeleader getCooperativeleader() {
        return cooperativeleader;
    }

    public void setCooperativeleader(Cooperativeleader cooperativeleader) {
        this.cooperativeleader = cooperativeleader;
    }

}
