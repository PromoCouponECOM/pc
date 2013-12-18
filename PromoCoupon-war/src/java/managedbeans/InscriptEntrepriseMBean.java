/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Adresse;
import entities.Entreprise;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.AdresseManager;
import session.EntrepriseManager;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author John624
 */

@Named(value = "inscripEntrepriseMBean")
@RequestScoped
public class InscriptEntrepriseMBean implements Serializable {

    private Map<String, String> settings;
    @EJB
    private AdresseManager adrM;
    @EJB
    private EntrepriseManager entM;
    
    private Adresse adr;
    private Entreprise entrp;
            
    public InscriptEntrepriseMBean() {
        settings= new HashMap<String,String>();
        adr = new Adresse();
        entrp = new Entreprise();
    }
    
    
    public void init (){
        adrM = new AdresseManager();
        entM = new EntrepriseManager();
    }
    
    
    public String save(){
        
        //update db adresse
        adr.setIdAdresse(adrM.nextId());
        adr.setNumEtRue(settings.get("rue"));
        adr.setComple(settings.get("compl"));
        adr.setVille(settings.get("ville"));
        adr.setPays(settings.get("pays"));
        adr.setCodePostale(settings.get("code"));
        adr.setDateModif(new Date());
        adrM.update(adr);
        
        entrp.setIdE(entM.nextId());
        entrp.setMailE(settings.get("mailE"));
        entrp.setNomE(settings.get("nomE"));
        entrp.setPassE(settings.get("passE"));
        entrp.setResuE(settings.get("resuE"));
        entrp.setTelE(settings.get("telE"));
        entrp.setConsulE(settings.get("consulE"));
        entrp.setRib(settings.get("rib"));
        entrp.setSiret(settings.get("siret"));
        entrp.setAdrE(adr);
        entrp.setDateModif(new Date());
        entrp.setValidation(new Integer(0));
        entM.update(entrp);
        
        return "EntrepriseList";
    }
    
     public Map<String, String> getSettings() {
        return settings;
    }
        public String exist() {
        System.out.println("coucou");
        if (save().equals("signup")) {
            System.out.println("exsits");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email existe!"));
            return "signup_Entreprise";
        } else {
            System.out.println("not exsits");
            return "EntrepriseList";
        }
    }
}
