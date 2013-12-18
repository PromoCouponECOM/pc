/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Offre;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John624
 */
@Stateless
@LocalBean
public class OffreManager {
    @PersistenceContext(unitName = "PromoCoupon-ejbPU")
    private EntityManager em;

    public List<Offre> getAllOffre() {
        Query query= em.createNamedQuery("Offre.findAll");   
        return query.getResultList();
    }
    
    public String getTitreOffre(int idOffre){
        Query query= em.createNamedQuery("Offre.findByIdO");
        query.setParameter("IdO", idOffre);
        
        List<Offre> tmp = query.getResultList();
        if(tmp!=null && !tmp.isEmpty())
            return tmp.get(0).getTitle();
        return null;
    }
    
     public String getTitreEtPrixById(int idOffre){
        Query query= em.createNamedQuery("Offre.findByIdO");
        query.setParameter("IdO", idOffre);
        
        List<Offre> tmp = query.getResultList();
        if(tmp!=null && !tmp.isEmpty())
            return tmp.get(0).getTitle()+"|"+tmp.get(0).getPrixActuel();
        return null;
    }

    public Offre update(Offre offre) {
        return em.merge(offre);
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long nextId(){
        Query query = em.createNamedQuery("Offre.maxId");
        Long res = (Long)query.getResultList().get(0);
        if(res==null)
            return new Long(0);
        return res+1;
    }
    
    public List<Offre> getSpecialOffres (String typeOffre){
        Query query = em.createQuery("SELECT o FROM Offre o join Ctegorie c on o.categorie=c.idCateg WHERE c.nomCateg =:"+typeOffre);
        return query.getResultList();
    }
    
}
