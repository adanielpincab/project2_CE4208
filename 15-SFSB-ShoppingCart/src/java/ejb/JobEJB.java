
package ejb;

import entity.Jobs;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class JobEJB {
    @PersistenceContext(unitName = "FormBasedAuthPU")
    private EntityManager em;
    
    public Jobs createJob(Jobs job){
        
        persist(job);
        return job;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Jobs> findByKeywords(String keywords){
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByKeywords", Jobs.class);
            query.setParameter("keywords", keywords);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return jobs;
    }
    
    
    public List<Jobs> findByStatus(String status){
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByStatus", Jobs.class);
            
            query.setParameter("status", status);
            List<Jobs> jobs= null;
            
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return jobs;
    }

    
}
