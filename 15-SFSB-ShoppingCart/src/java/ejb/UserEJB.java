package ejb;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entity.Log;
import entity.Jobs;
import entity.Group;
import entity.Provider;
import entity.User;
import entity.Freelancer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import utils.AuthenticationUtils;


@Stateless
public class UserEJB {
	
    @PersistenceContext(unitName="FormBasedAuthPU")
    private EntityManager em;
    private String Message;
    private Group groupEntity;
    private Provider provider;
    private Freelancer freelancer;
    private Jobs jobs;
    
    public User createUser(User user, String userType) {
            /**
             * Creates a new user, persists it in the database
            * @param User user
            * @param String userType
            * @return User user
            */   
            try {
                    user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
            } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
            }

            Group group = new Group();
            group.setEmail(user.getEmail());
            switch(userType){
                    case "1":
                        Provider provider = new Provider(user.getName(), user.getEmail());
                        persist(provider);
                        group.setGroupname(Group.PROVIDER_GROUP);
                        break;
                    case "2":
                        Freelancer freelancer = new Freelancer(user.getName(), user.getEmail());
                        persist(freelancer);
                        group.setGroupname(Group.FREELANCER_GROUP);
                        break;
                    default:
                        group.setGroupname(Group.ADMIN_GROUP);
                        break;
            }

            persist(user);
            persist(group);

            return user;
    }
   
    public User findUserById(String id) {
            /**
             * Looks for a user, searching by email (id)
            * @param id
            * @return User user
            */
            TypedQuery<User> query = em.createNamedQuery("findUserById", User.class);
            query.setParameter("email", id);
            User user = null;
            try {
                    user = query.getSingleResult();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return user;
    }
   
    public Provider findProviderById(String id) {
            /**
             * Looks for a provider by id
            * @param String id
            * @return Provider provider
            */
            TypedQuery<Provider> query = em.createNamedQuery("Provider.findByEmail", Provider.class);
            query.setParameter("email", id);
            Provider provider = null;
            try {
                    provider = query.getSingleResult();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return provider;
    }  
    
    public Freelancer findFreelancerById(String id) {
            /**
             * Looks for a freelancer by id
             * @Param String id 
             */
            TypedQuery<Freelancer> query = em.createNamedQuery("Freelancer.findByEmail", Freelancer.class);
            query.setParameter("email", id);
            Freelancer freelancer = null;
            try {
                    freelancer = query.getSingleResult();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return freelancer;
    }
    public List<Jobs> findJobsById(Provider id) {
            /**
             * Looks for a job by id
             * @param Provider id
             */
            TypedQuery<Jobs> query = em.createNamedQuery("Jobs.getProviderID", Jobs.class);
            query.setParameter("providerId", id);
            List<Jobs> jobs = null;
            try {
                    jobs = query.getResultList();
            } catch (Exception e) {
                    // getSingleResult throws NoResultException in case there is no user in DB
                    // ignore exception and return NULL for user instead
            }
            return jobs;
    }  

    private static Map<String,Object> value;
    static{
      value = new LinkedHashMap<String,Object>();
      value.put("job provider", "1"); //label, value
      value.put("freelancer", "2");
    }

    public Map<String,Object> getUserTypeValue() {
      return value;
    }
    
    public void persist(Object object) {
        /**
        * persists an object into the database
        * @param object object 
        */
        em.persist(object);
    }
    
    public void logMessage(String message) {
        /**
         *  Logs a message into the database
         *  @param String message
         */
        Log newLog = new Log(message);
        persist(newLog);
    }
    
    public void remove(User user) {
        /**
         * Remove a user from the database
         * @param User user
         */
        Message ="";
        FacesContext context = FacesContext.getCurrentInstance();
        if(user!=null){
            if ("admin@admin.com".equals(user.getEmail())) {
                Message = "You have no right to delete Administrator.";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Message, null)); 
            }
            else {
                groupEntity = em.find(Group.class,user.getEmail());
                if("providers".equals(groupEntity.getGroupname())) {
                    provider = findProviderById(user.getEmail());
                    em.remove(em.merge(provider));
                }
                else {
                    freelancer = findFreelancerById(user.getEmail());
                    em.remove(em.merge(freelancer));
                }
                em.remove(em.merge(groupEntity));
                em.find(User.class, user.getEmail());
                em.remove(em.merge(user)); 
                Message = "User: " + user.getEmail() + " has been removed from database.";  
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Message, null));
            }  
        }
    }

    public void removeJob(Jobs jobs) {
        /**
         * Remove a job from the database
         * @param Jobs jobs
         */
        Message = "";
        FacesContext context = FacesContext.getCurrentInstance();
        if(jobs!=null){
            this.jobs = em.find(Jobs.class,jobs.getJobsId());
            em.remove(em.merge(this.jobs));
            Message = "JobsId: " + jobs.getJobsId() + " has been removed from database.";  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Message, null));
        }
        else {
            Message = "Job description does not exist in the database";  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Message, null));
        
        }
    }
    
    
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }
    
    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }
    
}
