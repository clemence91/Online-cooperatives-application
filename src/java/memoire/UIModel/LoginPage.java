package memoire.UIModel;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import memoire.Dao.CooperativeDao;
import memoire.Dao.CooperativeleaderDao;
import memoire.Dao.Dcodao;
import memoire.Dao.DistrictDao;
import memoire.Dao.ProvinceDao;

import memoire.Dao.RegisterDao;
import memoire.Dao.Scodao;
import memoire.Dao.SectorDao;

import memoire.Domain.Cooperative;
import memoire.Domain.Cooperativeleader;
import memoire.Domain.Dco;
import memoire.Domain.District;
import memoire.Domain.Province;

import memoire.Domain.Register;
import memoire.Domain.Sco;
import memoire.Domain.Sector;

import org.primefaces.context.RequestContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name = "login")
@SessionScoped
public class LoginPage implements Serializable {

    private Sco sco = new Sco();
    private Dco dco = new Dco();
    private Dcodao dcodao = new Dcodao();
    private Scodao scodao = new Scodao();
    private RegisterDao registdao = new RegisterDao();
    private Register register = new Register();
    private Cooperativeleader cooperativeleader = new Cooperativeleader();
    private Cooperative cooperative = new Cooperative();
    private Boolean show = false;
    private List<Dco> list2 = new Dcodao().GetDco();
    private List<Dco> list = new Dcodao().get2();
    private List<Sco> lis2 = new Scodao().GetSco();
    private List<Sco> lis = new Scodao().get3();
    private List<Register> li2 = new RegisterDao().GetRegister();
    private List<Register> li = new RegisterDao().get1();
    private List<Sector> sectors = new SectorDao().getAll("from Sector");
    private List<District> districts = new DistrictDao().getAll("from District");
    private List<Province> provinces = new ProvinceDao().getAll("from Province");
    private List<Cooperative> allcooperatives = new CooperativeDao().list();
    private List<Cooperative> canceledOnes = new ArrayList<>();
    private List<Cooperative> approvedcoopera = new ArrayList<>();
    private List<Cooperative> pendingCooperative = new CooperativeDao().notApprovedCooperative();
    private String password = new String();
    private String username = new String();
    private String access = new String();
    private String seldistric;
    private String filename = new String();
    private List<Cooperative> cooperatives = new ArrayList<>();
    private Sector sector = new Sector();
    private District dist = new District();
    private Province province = new Province();
    private String namepro;
    private String sectorName;
    private String districtName;
    private List<Cooperative> approvedOnes;
    private String cooperativeName = new String();
    private List<File> allFileNames = new ArrayList<>();

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDist() {
        return dist;
    }

    public void setDist(District dist) {
        this.dist = dist;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void init(Sco sc) {
        this.sco = sc;
    }
    private boolean details = false;
    private boolean rejected = false;
    private boolean approved = false;
    private String firstValue = new String();

    public Scodao getSdo() {
        return sdo;
    }

    public void setSdo(Scodao sdo) {
        this.sdo = sdo;
    }

    private Scodao sdo = new Scodao();

    private boolean checkup = true;

    public boolean isCheckup() {
        return checkup;
    }

    public void setCheckup(boolean checkup) {
        this.checkup = checkup;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public String getSeldistric() {
        return seldistric;
    }

    public void setSeldistric(String seldistric) {
        this.seldistric = seldistric;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void viewFiles(Cooperative coop) {
        this.cooperative = coop;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void recordSco() {
        try {
             Date dd = new Date();
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        sDate.setTime(sco.getDob());
        eDate.setTime(dd);
        int years = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);

            if (sco.getPhoneNo().length() == 10 && sco.getPhoneNo().matches("[0-9]+")) {
                if (sco.getPhoneNo().startsWith("078") || sco.getPhoneNo().startsWith("072") || sco.getPhoneNo().startsWith("073")) {
                    if (sco.getEmail().endsWith("@gmail.com") ) {
                        if (years >= 18) {
                             
            sco.setAccess("Sco");
            sco.setStatus("ACTIVE");
            sector = new SectorDao().OneSector(test);

            sco.setSector(sector);
            new Scodao().create(sco);
            show = false;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sco  is well recorded", ""));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sco should be above 18 years old", ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email should be of gmail or yahoo type", username));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number should be one of the telecommunication commpanies operating in Rwanda", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number's length should be 10 digits", username));
            }
        
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordDco() {
        try {
             Date dd = new Date();
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        sDate.setTime(dco.getDob());
        eDate.setTime(dd);
        int years = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);

            if (dco.getPhoneNo().length() == 10 && dco.getPhoneNo().matches("[0-9]+")) {
                if (dco.getPhoneNo().startsWith("078") || dco.getPhoneNo().startsWith("072") || dco.getPhoneNo().startsWith("073")) {
                    if (dco.getEmail().endsWith("@gmail.com") ) {
                        if (years >= 18) {
                             
            dco.setState(true);
            dco.setStatus("ACTIVE");
            dist = new DistrictDao().OneDistrict(test1);
            dco.setDistrict(dist);
            dco.setAccess("Dco");
            new Dcodao().create(dco);
            this.dco=new Dco();
            show = false;

            
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "District Officer  is well recorded", ""));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "District Officer should be above 18 years old", ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email should be of gmail", username));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number should be one of the telecommunication commpanies operating in Rwanda", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number's length should be 10 digits", username));
            }
        
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordregister() {
        try {
            
            Date dd = new Date();
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        sDate.setTime(register.getDob());
        eDate.setTime(dd);
        int years = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);

            if (register.getPhoneNo().length() == 10 && register.getPhoneNo().matches("[0-9]+")) {
                if (register.getPhoneNo().startsWith("078") || register.getPhoneNo().startsWith("072") || register.getPhoneNo().startsWith("073")) {
                    if (register.getEmail().endsWith("@gmail.com") ) {
                        if (years >= 18) {
             
           register.setState(true);
            register.setStatus("ACTIVE");
            register.setAccess("Admin");
            new RegisterDao().create(register);
            show = false;

            

            
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin  is well recorded", ""));
                      
            register.setEmail("");
            register.setPhoneNo("");
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Admin should be above 18 years old", ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email should be of gmail ", username));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number should be one of the telecommunication commpanies operating in Rwanda", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number's length should be 10 digits", username));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private String name1;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void fillSectors() {
        sectors = new ArrayList<>();
        for (Sector dd : new SectorDao().getAll("from Sector")) {
            if (dd.getDistrict().getDistname().equals(name1)) {
                sectors.add(dd);
            }
        }
    }

    
    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Sco getSco() {
        return sco;
    }

    public void setSco(Sco sco) {
        this.sco = sco;
    }

    public Dco getDco() {
        return dco;
    }

    public void setDco(Dco dco) {
        this.dco = dco;
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

    public Cooperative getCooperative() {
        return cooperative;
    }

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public List<Dco> getList2() {
        return list2;
    }

    public void setList2(List<Dco> list2) {
        this.list2 = list2;
    }

    public List<Dco> getList() {
        return list;
    }

    public void setList(List<Dco> list) {
        this.list = list;
    }

    public List<Sco> getLis2() {
        return lis2;
    }

    public void setLis2(List<Sco> lis2) {
        this.lis2 = lis2;
    }

    public List<Sco> getLis() {
        return lis;
    }

    public void setLis(List<Sco> lis) {
        this.lis = lis;
    }

    public List<Register> getLi2() {
        return li2;
    }

    public void setLi2(List<Register> li2) {
        this.li2 = li2;
    }

    public List<Register> getLi() {
        return li;
    }

    public void setLi(List<Register> li) {
        this.li = li;
    }

    public List<Cooperative> getAllcooperatives() {
        return allcooperatives;
    }

    public void setAllcooperatives(List<Cooperative> allcooperatives) {
        this.allcooperatives = allcooperatives;
    }

    public List<Cooperative> getCanceledOnes() {
        return canceledOnes;
    }

    public void setCanceledOnes(List<Cooperative> canceledOnes) {
        this.canceledOnes = canceledOnes;
    }

    public List<Cooperative> getApprovedcoopera() {
        return approvedcoopera;
    }

    public void setApprovedcoopera(List<Cooperative> approvedcoopera) {
        this.approvedcoopera = approvedcoopera;
    }

    public List<Cooperative> getPendingCooperative() {
        return pendingCooperative;
    }

    public void setPendingCooperative(List<Cooperative> pendingCooperative) {
        this.pendingCooperative = pendingCooperative;
    }

    public List<Cooperative> getCooperatives() {
        return cooperatives;
    }

    public void setCooperatives(List<Cooperative> cooperatives) {
        this.cooperatives = cooperatives;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isDetails() {
        return details;
    }

    public void setDetails(boolean details) {
        this.details = details;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public void createAccountForSco() {
        if (checkup(this.sco.getPassword(), this.password)) {
            Sco stf = new Scodao().findSco(this.sco.getPhoneNo());
            if (stf != null) {
                stf.setUsername(sco.getUsername());
                stf.setPassword(password);
                new Scodao().Update(stf);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sector officer is well recorded", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not registered, Contack system Admin", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password you have entered doesn't match", ""));
        }
    }

    public void createAccountForDco() {
        if (checkup(this.dco.getPassword(), this.password)) {
            Dco d = new Dcodao().findDco(this.dco.getPhoneNo());
            if (d != null) {
                d.setUsername(dco.getUsername());
                d.setPassword(password);
                new Dcodao().Update(d);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dco account  is well recorded", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not registered, Contack system Admin", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password you have entered doesn't match", ""));
        }
    }
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createAccountForradmin() {
        if (checkup(this.register.getPassword(), this.password)) {
            Register r = new RegisterDao().findREgister(this.register.getPhoneNo());
            if (r != null) {
                r.setUsername(register.getUsername());
                r.setPassword(password);
                new RegisterDao().Update(r);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "register account is well recorded", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not registered, Contack system Admin", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password you have entered doesn't match", ""));
        }
    }

    public String staffLogin() {
        String sms = "";
        try {
            Register stf = new RegisterDao().findregisterbyUsername(username);
            if (stf != null) {
                register = stf;
                if (stf.getPassword().equals(password)) {
                    if (stf.isState()) {
                        if (stf.getAccess().equalsIgnoreCase("Admin")) {
                            sms = "AdminApprover.xhtml";
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry sir u are blocked", ""));
                        sms = "loginpage";
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password!", ""));
                    sms = "loginpage";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password.", ""));
                sms = "loginpage";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sms;
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

    public RegisterDao getRegistdao() {
        return registdao;
    }

    public void setRegistdao(RegisterDao registdao) {
        this.registdao = registdao;
    }

    public void shows() {
        show = true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkup(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        } else {
            return false;
        }
    }

    public void downloadCooperativeleader(Cooperativeleader leader) {
        this.cooperativeleader = leader;
    }

    public void recordCooerativerleader() {
        Date dd = new Date();
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        sDate.setTime(cooperativeleader.getDob());
        eDate.setTime(dd);
        int years = eDate.get(Calendar.YEAR) - sDate.get(Calendar.YEAR);
        if (checkup(this.cooperativeleader.getPassword(), this.password)) {
            if (cooperativeleader.getPhone().length() == 10 && cooperativeleader.getPhone().matches("[0-9]+")) {
                if (cooperativeleader.getPhone().startsWith("078") || cooperativeleader.getPhone().startsWith("072") || cooperativeleader.getPhone().startsWith("073")) {
                    if (cooperativeleader.getEmail().endsWith("@gmail.com") ) {
                        if (years >= 18) {
                            sector = new SectorDao().OneSector(test);

                            cooperativeleader.setSector(sector);
                            cooperativeleader.setAccess("Cooperativeleader");
                            new CooperativeleaderDao().create(cooperativeleader);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperativeleader  is well recorded", ""));
                            cooperativeleader.setNationalId("");
                            cooperativeleader.setFname("");
                            cooperativeleader.setLname("");
                            cooperativeleader.setPhone("");
                            cooperativeleader.setPassword("");
                            cooperativeleader.setEmail("");
                            
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "cooperativeleader should be above 18 years old", ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email should be of gmail", username));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number should be one of the telecommunication commpanies operating in Rwanda", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone number's length should be 10 digits", username));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password you have entered doesn't match", ""));
        }
    }

    public void showdetails() {
        details = true;
    }
    private String test1;

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public void recordCooperative() {
        try {
            createFolder();
            cooperative.setDistrict(new District(districtName));
            cooperative.setSector(new Sector(sectorName));
            cooperative.setRequestno(UUID.randomUUID().toString());
            cooperative.setApproved(false);
            cooperative.setRequestDate(new Date());
            cooperative.setRegdate(new Date());
            cooperative.setCooperativeleader(cooperativeleader);
            cooperative.setPermited(false);
            cooperative.setFileName(cooperativeName);
            cooperative.setName(cooperativeName);
            new CooperativeDao().create(cooperative);

            cooperatives = new CooperativeDao().pendingRequest(String.valueOf(cooperativeleader.getNationalId()));
            pendingCooperative = new CooperativeDao().notApprovedCooperative();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Request is well recorded", ""));
            details = false;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }

    public void considerRequest(Cooperative cooperative) {
        cooperative.setApproved(true);
        cooperative.setConsideredDate(new Date());
        cooperative.setDco(dco);
        this.cooperative = cooperative;
        new CooperativeDao().Update(this.cooperative);

        allcooperatives = new CooperativeDao().list();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperative is well considered", ""));
    }

    public void uploadFile(FileUploadEvent eventUpload) throws IOException, Exception {
        FacesMessage msg = new FacesMessage("Success", "You can upload other files!!!");
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
        System.out.println("Okay well done");
        try {
            copyFile(eventUpload.getFile().getFileName(), eventUpload.getFile().getInputstream());
            filename = eventUpload.getFile().getFileName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private File filename1;

    public File getFilename1() {
        return filename1;
    }

    public void setFilename1(File filename1) {
        this.filename1 = filename1;
    }
    
    
public void copyFile(String fileName, InputStream in) {
        try {
            String destination = "C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\";
            OutputStream out = new FileOutputStream(new File(destination + cooperativeName + "\\" + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }
public void download(){
    try {
        FacesContext context=FacesContext.getCurrentInstance();
        ExternalContext externalContext=context.getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseContentType(filename);
        externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"18264 transcript.pdf");
        FileInputStream inputStream=new FileInputStream(new File("C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\kuroba\\18264 transcript.pdf"));
    OutputStream outputStream=externalContext.getResponseOutputStream();
    byte[] buffer=new byte[1024];
    int length;
    while((length=inputStream.read(buffer))>0){
        outputStream.write(buffer,0,length);
    
    }
    inputStream.close();
    context.responseComplete();
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void downloadFile() {

    File file = new File("C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\"+cooperativeName);
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;"+name);  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    }  
}
   
    public void downloadCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public void updateRrequest() {
        try {
            cooperative.setApproved(true);
            cooperative.setPermited(false);
            cooperative.setApprovalDate(new Date());
            cooperative.setSco(sco);
            new CooperativeDao().Update(cooperative);

            pendingCooperative = new CooperativeDao().notApprovedCooperative();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cooperative has been well rejected", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approverequest(Cooperative cooperative) {
        try {
            this.cooperative = cooperative;
            this.cooperative.setApproved(true);
            this.cooperative.setPermited(true);
            this.cooperative.setSco(sco);
            cooperative.setApprovalDate(new Date());
            new CooperativeDao().Update(this.cooperative);

            pendingCooperative = new CooperativeDao().notApprovedCooperative();
            allcooperatives = new CooperativeDao().list();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cooperative has been well Approved", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadDco(Dco Dco) {
        this.dco = dco;
    }

    public void downloadSco(Sco sco) {
        this.sco = sco;
    }

    public List<Cooperative> cooperatives() {
        return new CooperativeDao().findAllCooperatives();
    }

    public List<Cooperative> pendingOnes() {
        return new CooperativeDao().notApprovedCooperative();
    }

    public List<Cooperative> getApprovedOnes() {
        return approvedOnes = new CooperativeDao().approveds();
    }

    public List<Cooperative> rejectedOnes() {
        return new CooperativeDao().rejecteds();
    }

    public String getNamepro() {
        return namepro;
    }

    public void setNamepro(String namepro) {
        this.namepro = namepro;
    }


    public void fillDistrict() {
        districts = new ArrayList<>();
        for (District disti : new DistrictDao().getAll("from District")) {
            if (disti.getProvince().getPoviname().equals(namepro)) {
                districts.add(disti);
            }
        }

    }

//  public void fillSectors() {
//        sectors = new ArrayList<>();
//        for (Sector dd : new SectorDao().getAll("from Sector")) {
//            if (dd.getDistrict().getDistname().equals(name)) {
//                sectors.add(dd);
//            }
//        }
//    }
    public String getCooperativeName() {
        return cooperativeName;
    }

    public void setCooperativeName(String cooperativeName) {
        this.cooperativeName = cooperativeName;
    }

    public void createFolder() {
        try {
            File cooperativeFolder = new File("C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\" + cooperativeName);
            if (!cooperativeFolder.exists()) {
                cooperativeFolder.mkdir();
            } else {
                File f = new File("C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\" + cooperativeName);
                File[] listOfFiles = f.listFiles();
                for (File c : listOfFiles) {
                    if (c.isFile()) {
                        allFileNames.add(c);
                    }
                }
                System.out.println(cooperativeFolder + " :exists!!!!");
            }
        } catch (Exception e) {
        }
    }

    public void addFileName() {
        cooperativeName = cooperative.getName();
        try {
            File f = new File("C:\\Users\\Niyigena Clemence\\Desktop\\projrctclemememoire\\web\\userfiles\\" + cooperativeName + "\\");
            File[] listOfFiles = f.listFiles();
            if (cooperativeName.length() > 0) {
                allFileNames = new ArrayList<>();
                for (File c : listOfFiles) {
                    if (c.isFile()) {
                        allFileNames.add(c);
                    }
                }
            } else {
                allFileNames = new ArrayList<>();
            }
        } catch (Exception e) {
        }
    }

    public List<File> getAllFileNames() {
        return allFileNames;
    }

    public void setAllFileNames(List<File> allFileNames) {
        this.allFileNames = allFileNames;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void loadi() {
        allFileNames = new ArrayList<>();
    }

   

    public String AdminLogin() {
        String location = "";
        try {
            register = new RegisterDao().findregisterbyUsername(username);
            if (register != null) {
                if (register.getStatus().equalsIgnoreCase("Active")) {
                    if (register.getUsername().equals(username)) {
                        if (register.getPassword().equalsIgnoreCase(password)) {
                            location = "AdminApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    } else {
                        if (sco.getPassword().equals(password)) {
                            location = "AdminApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    }
                } else {
                    FacesContext ct = FacesContext.getCurrentInstance();
                    FacesMessage msg = new FacesMessage("Your Account Is Blocked");
                    ct.addMessage(null, msg);
                }
            } else {
                FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("Wrong username and password ");
                ct.addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public String dcoLogin() {
        String location = "";
        try {
            dco = new Dcodao().findDcobyUsername(username);
            if (dco != null) {
                if (dco.getStatus().equalsIgnoreCase("Active")) {
                    if (dco.getUsername().equals(username)) {
                        if (dco.getPassword().equalsIgnoreCase(password)) {
                            location = "districtOfficerApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    } else {
                        if (sco.getPassword().equals(password)) {
                            location = "districtOfficerApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    }
                } else {
                    FacesContext ct = FacesContext.getCurrentInstance();
                    FacesMessage msg = new FacesMessage("Your Account Is Blocked");
                    ct.addMessage(null, msg);
                }
            } else {
                FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("Wrong username and password ");
                ct.addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public String scoLogin() {

        String location = "";
        try {
            sco = new Scodao().findScobyUsername(username);
            if (sco != null) {
                if (sco.getStatus().equalsIgnoreCase("Active")) {
                    if (sco.getUsername().equals(username)) {
                        if (sco.getPassword().equalsIgnoreCase(password)) {
                            location = "SectorApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    } else {
                        if (sco.getPassword().equals(password)) {
                            location = "SectorApprover";
                        } else {
                            FacesContext ct = FacesContext.getCurrentInstance();
                            FacesMessage msg = new FacesMessage("wrong username and password");
                            ct.addMessage(null, msg);
                        }
                    }
                } else {
                    FacesContext ct = FacesContext.getCurrentInstance();
                    FacesMessage msg = new FacesMessage("Your Account Is Blocked");
                    ct.addMessage(null, msg);
                }
            } else {
                FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("Wrong username and password ");
                ct.addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public String CooperativeleaderLogin() {
        String sms = "";
        try {
            Cooperativeleader stf = new CooperativeleaderDao().findCooperativeleader(username);

            if (stf != null) {
                cooperativeleader = stf;
                if (stf.getPassword().equals(password)) {
                    cooperatives = new CooperativeDao().pendingRequest(String.valueOf(cooperativeleader.getNationalId()));
                    approvedcoopera = new CooperativeDao().approvedCooperative(String.valueOf(cooperativeleader.getNationalId()));
                    canceledOnes = new CooperativeDao().rejectedCooperative(String.valueOf(cooperativeleader.getNationalId()));
                    sms = "Cooperativereg.xhtml";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password!!", ""));
                    sms = "Login";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password.", ""));
                sms = "Login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sms;
    }

    public String login() {
        String sms = "";
        try {
            if (access.length() > 0) {
                if (access.equalsIgnoreCase("Admin")) {
                    sms = AdminLogin();
                } else if (access.equalsIgnoreCase("Dco")) {
                    sms = dcoLogin();
                } else if (access.equalsIgnoreCase("Sco")) {
                    sms = scoLogin();
                } else if (access.equalsIgnoreCase("Cooperativeleader")) {
                    sms = CooperativeleaderLogin();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sms = "Login.xhtml";
        }
        return sms;
    }

    public String cancel(Register r) {
        this.register = r;
        if (r.getStatus().equalsIgnoreCase("ACTIVE")) {
            r.setStatus("Blocked");
            r.setAction("Activate");
            new RegisterDao().Update(r);
            li = new RegisterDao().get1();
        } else {
            r.setStatus("ACTIVE");
            r.setAction("Block");
            new RegisterDao().Update(r);
            li = new RegisterDao().get1();
        }

        return "adminlist";
    }

    public String stud() {

        String ans = " adminlist.xhtml?faces-redirect=true";
        return ans;
    }

    public String canceldco(Dco d) {
        this.dco = d;
        if (d.getStatus().equalsIgnoreCase("ACTIVE")) {
            d.setStatus("Blocked");
            d.setAction("Activate");
            new Dcodao().Update(d);
            list = new Dcodao().get2();
        } else {
            d.setStatus("ACTIVE");
            d.setAction("Block");
            new Dcodao().Update(d);
            list = new Dcodao().get2();
        }

        return "dcolist";
    }

    public String stud1() {

        String ans = " dcolist.xhtml?faces-redirect=true";
        return ans;
    }

    public String cancelsco(Sco sco) {
        this.sco = sco;
        if (sco.getStatus().equalsIgnoreCase("ACTIVE") && sco.getAccess().equalsIgnoreCase("Sco")) {
            sco.setStatus("Blocked");
            sco.setAction("Activate");
            new Scodao().Update(sco);
            lis = new Scodao().get3();
        } else {
            sco.setStatus("ACTIVE");
            sco.setAction("Block");
            new Scodao().Update(sco);
            lis = new Scodao().get3();
        }

        return "scolist";
    }

    public String stud2() {

        String ans = " scolist.xhtml?faces-redirect=true";
        return ans;
    }

   final String username1 = "niyirorakohati2020";
    final String password1 = "niyigena";
    UploadedFile filet;
    
public void sendEmail(){
    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username1, password1);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("niyirorakohati2020@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mail));
        message.setSubject("icyemezo");
        message.setText("RCA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "C:\\Users\\Niyigena Clemence\\Desktop\\bookclemence\\"+filet.getFileName()+"";
        String fileName = "icyemezo";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");
FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("well done ");
                ct.addMessage(null, msg);
    } catch (MessagingException e) {
        FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("Wrong  "+e.getMessage());
                ct.addMessage(null, msg);
        e.printStackTrace();
    }
  }

    public UploadedFile getFilet() {
        return filet;
    }

    public void setFilet(UploadedFile filet) {
        this.filet = filet;
    }

    

private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
