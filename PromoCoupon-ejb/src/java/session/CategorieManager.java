/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Categorie;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 *
 * @author John624
 */
@Stateless
@LocalBean
public class CategorieManager {
    @PersistenceContext(unitName = "PromoCoupon-ejbPU")
    private EntityManager em;
            
    public List<Categorie> getAllCategorie() {
        Query query = em.createNamedQuery("Categorie.findAll");
        return query.getResultList();
    }

    public Categorie update(Categorie categorie){
        return em.merge(categorie);
    }
    
    public void persist(Object object){
        em.persist(object);
    }
    
    public Integer nextId(){
        Query query = em.createNamedQuery("Categorie.maxId");
        Integer res = (Integer)query.getResultList().get(0);
        if(res==null)
            return new Integer(0);
        return res+1;
    }

    public boolean existeCat(String nomCateg) {
        Query query = em.createNamedQuery("Categorie.findByIdCateg");
        query.setParameter("nomCateg", nomCateg);
        return !query.getResultList().isEmpty();
    }

    public Categorie getCategorieByName(String selected) {
        Query query = em.createNamedQuery("Categorie.findByNomCateg");
        query.setParameter("nomCateg", selected);
        Categorie res = (Categorie)query.getSingleResult();
        return res;
    }
}
