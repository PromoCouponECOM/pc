/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Categorie;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.CategorieManager;

/**
 *
 * @author John624
 */
@Named(value="categorieMBean")
@SessionScoped
public class CategorieMBean implements Serializable{
    @EJB
    private CategorieManager categorieManager;
    private List<Categorie> categories;
    private Categorie cat;
    
    public CategorieMBean() {
        cat = new Categorie();
    }
    
    public  List<Categorie> getCategories(){
        if((categories == null)|| (categories.isEmpty())){
           refresh();
        }
        return categories;
    }

    private void refresh() {
         if((categories == null) || (categories.isEmpty()))
            categories = categorieManager.getAllCategorie();
    }

    /** 
     * returns details of a customer. Useful for displaying in a form a customer's details 
     * @return 
     */  
    public Categorie getDetails() {  
        return cat;  
    }  
  
    /** 
     * Action handler - Called when a line in the table is clicked 
     * @param utilisateur 
     * @return 
     */  
    public String showDetails(Categorie cat) {  
        this.cat = cat;  
        return "CategorieDetails"; // will display CustomerDetails.xml JSF page  
    }  
  
    /** 
     * Action handler - update the customer model in the database. 
     * called when one press the update button in the form 
     * @return 
     */  
    public String update() {  
        System.out.println("###UPDATE###");  
        cat = categorieManager.update(cat);  
        return "CategorieList"; // will display the customer list in a table  
    }  
  
    /** 
     * Action handler - returns to the list of customers in the table 
     */  
    public String list() {  
        System.out.println("###LIST###");  
        return "CategorieList";  
    }  
    
}
