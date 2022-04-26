/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import ejb.JobEJB;
import ejb.UserEJB;
import entity.Jobs;
import entity.Provider;
import entity.Freelancer;
        
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;
import jakarta.inject.Named;
import java.util.logging.Level;
import jakarta.annotation.Resource;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
    //IN HERE IS FINDJOBS function
    
    
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
        //jobs = userEJB.findJobsById(provider.getId());   
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
