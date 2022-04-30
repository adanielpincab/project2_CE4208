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
        /*
            @param Jobs job
            Create a new job in the database
        */
        persist(job);
        return job;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Jobs> findByKeywords(String keywords){
        /*
            @param String keywords
            Find a job inside the database, searching by keyword
        */
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByKeywords", Jobs.class);
            query.setParameter("keywords", keywords);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }
    
    
    public List<Jobs> findByStatus(String status){
            /*
                @param String status 
                Find jobs inside the database by status
            */
        
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.findByStatus", Jobs.class);
            
            query.setParameter("status", status);
            List<Jobs> jobs= null;
            
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {}
            return jobs;
    }

    
}
