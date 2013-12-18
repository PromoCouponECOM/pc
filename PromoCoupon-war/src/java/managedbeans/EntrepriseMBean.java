/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Entreprise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.EntrepriseManager;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author John624
 */
@Named(value="entrepriseMBean")
@RequestScoped
public class EntrepriseMBean implements Serializable {

    private List<Entreprise> entreprises;
    private Entreprise entreprise;
    @EJB
    private EntrepriseManager entrepriseManager;

    /**
     * Creates a new instance of EntrepriseMBean
     */
    public EntrepriseMBean() {
        Entreprise entreprise = new Entreprise();
    }

    public List<Entreprise> getEntreprises() {
        if (entreprises == null || entreprises.isEmpty()) {
            entreprises = entrepriseManager.getAllEntreprise();
        }
        return entreprises;
    }
    
    public List<String> getNameEntrep(){
        List<String> res = new ArrayList<String>();
        for(Entreprise e : getEntreprises()){
            res.add(e.getNomE());
        }
        return res;
    }

    public Entreprise getDetails() {
        return entreprise;
    }

    /**
     * Action handler - Called when a line in the table is clicked
     *
     * @param utilisateur
     * @return
     */
    public String showDetails(Entreprise entreprise) {
        this.entreprise = entreprise;
        return "EntrepriseDetails"; // will display CustomerDetails.xml JSF page  
    }

    /**
     * Action handler - update the customer model in the database. called when
     * one press the update button in the form
     *
     * @return
     */
    public String update() {
        System.out.println("###UPDATE###");
        entreprise = entrepriseManager.update(entreprise);
        return "EntrepriseList"; // will display the customer list in a table  
    }

    /**
     * Action handler - returns to the list of customers in the table
     */
    public String list() {
        System.out.println("###LIST###");
        return "EntrepriseList";
    }
}
