/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import ejb.UserEJB;
import entity.User;
import entity.Provider;
import entity.Jobs;
import java.util.List;
import java.util.logging.Logger;
import jakarta.annotation.Resource;
import jakarta.inject.Named;

@Named(value = "removeView")
@SessionScoped
public class RemoveView implements Serializable {
    
    private static final long serialVersionUID = 4685823449195612778L;
    
    private static Logger log = Logger.getLogger(RemoveView.class.getName());

    @Inject
    private UserEJB userEJB;
    
    @Resource
    private jakarta.transaction.UserTransaction utx;
    

    private String email;

    
    public void removeUser(User user) {
        userEJB.remove(user); //Call userEJB class to use the defined entity manager.
                              //No need to create a new entity manager instance here.
    }
    
    public void removeJobDescriptions(Jobs jobs) {
        userEJB.removeJob(jobs);
    }
    

    public User findUserByEmail() {
        User user= null;
        user = userEJB.findUserById(email);
        return user;
    }
    
    public List<Jobs> findJobDescriptionByEmail() {
        Provider provider = null;
        List<Jobs> jobs = null;
        provider = userEJB.findProviderById(email);   
        jobs = userEJB.findJobsById(provider); 
        return jobs;
    }
    
    public void accept(User user, Jobs jobs) {
        userEJB.logMessage("User " + user.getEmail() + " accepted job with ID: " + jobs.getJobsId().toString() );
    }
    public void revoke(User user, Jobs jobs) {
        userEJB.logMessage("User " + user.getEmail() + " revoked job with ID: " + jobs.getJobsId().toString() );
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }   
}
