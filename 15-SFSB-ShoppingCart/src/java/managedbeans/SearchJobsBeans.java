package managedbeans;

import ejb.JobEJB;
import ejb.UserEJB;
import entity.Jobs;
import entity.Provider;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import jakarta.inject.Named;
import jakarta.annotation.Resource;

@Named(value = "searchJobsBeans")
@SessionScoped

public class SearchJobsBeans implements Serializable {
    
    private static final long serialVersionUID = 4685823449195612778L;
    private static Logger log = Logger.getLogger(SearchJobsBeans.class.getName());
    
    @Inject
    private JobEJB jobEJB;
    private UserEJB userEJB;
    
    @Resource
    private jakarta.transaction.UserTransaction utx;
    private String keywords;
    private int jobsid;
    private String providerId;
    
    public List<Jobs> findbyKeyword(){
        List<Jobs> jobs = null;
        jobs = jobEJB.findByKeywords(keywords);
        return jobs;
    }
    
    
    public List<Jobs> findbyStatus(){
        List<Jobs> jobs = null;
        jobs =  jobEJB.findByStatus("open");
        return jobs;
    }
    
    public List<Jobs> findJobByProviderId() {
        Provider provider = null;
        List<Jobs> jobs = null;
        provider = userEJB.findProviderById(providerId); 
        jobs = userEJB.findJobsById(provider); 
        return jobs;
    }

    public void removeJobByProvider(Jobs jobs) {
        userEJB.removeJob(jobs);
    }
    
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    

    
}
