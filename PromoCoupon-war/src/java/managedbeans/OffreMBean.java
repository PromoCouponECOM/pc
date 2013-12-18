/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Offre;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.OffreManager;

/**
 *
 * @author John624
 */
@Named(value="offreMBean")
@SessionScoped
public class OffreMBean implements Serializable{
    private List<Offre> offres;
    @EJB
    private OffreManager offreManager;
    private Offre offre;
    private Offre selectedOffre;  
    /**
     * Creates a new instance of OffreMBean
     */
    
   
    public OffreMBean() {
        Offre offre = new Offre();
    }
    
    public List<Offre> getOffres(){
    if((offres == null) || (offres.isEmpty()))
            offres = offreManager.getAllOffre();  
        return offreManager.getAllOffre(); 
    }
    
    
    public String update(){
        System.out.println("###UPDATE###");  
        offre = offreManager.update(offre);  
        return "OffreList"; // will display the Offre list in a table 
    }
    
      /** 
     * Action handler - Called when a line in the table is clicked 
     * @param offre 
     * @return 
     */  
    public String showDetails(Offre offre) {  
        this.offre = offre;  
        return "OffreDetails"; // will display CustomerDetails.xml JSF page  
    } 
    
    /** 
     * Action handler - returns to the list of Offres in the table 
     */  
    public String list() {  
        System.out.println("###LIST###");  
        return "OffreList";  
    }
    
    
    public Offre getSelectedOffre() {  
        return selectedOffre;  
    }  
  
    public void setSelectedOffre(Offre selectedOffre) {  
        this.selectedOffre = selectedOffre;  
    } 
    
    public String calculerPourcentage(double prixActuel, double prixOrigin) {  
        double res = Math.round(100 - (prixActuel*100/prixOrigin));
        return "-"+res+"%";
    } 
    
     public String dateValidite(Date dateDebut, Date dateFin) { 
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateDebut);

        int jourDeb = cal.get(Calendar.DATE);
        String jourDebut;
        if (jourDeb<10) {
            jourDebut = "0"+jourDeb;
        }
        else { 
            jourDebut = ""+jourDeb;
        }
        int moisDeb = cal.get(Calendar.MONTH);
        String moisDebut;
        if (moisDeb<10) {
            moisDebut = "0"+jourDeb;
        }
        else { 
            moisDebut = ""+jourDeb;
        }
        int anneeDebut = cal.get(Calendar.YEAR);
        
        cal.setTime(dateFin);
        int jourF = cal.get(Calendar.DATE);
        String jourFin;
        if (jourF<10) {
            jourFin = "0"+jourDeb;
        }
        else {
            jourFin = ""+jourDeb;
        }
        int moisF = cal.get(Calendar.MONTH);
        String moisFin;
        if (moisF<10) {
            moisFin = "0"+jourDeb;
        }
        else { 
            moisFin = ""+jourDeb;
        }
        int anneeFin = cal.get(Calendar.YEAR);

        
        return "3";
    } 
}
