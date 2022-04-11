package managedbeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import ejb.UserEJB;
import entity.Freelancer;
import entity.Provider;
import entity.User;
import jakarta.inject.Named;

@Named(value = "loginView")
@SessionScoped
public class LoginView implements Serializable {

	private static final long serialVersionUID = 3254181235309041386L;
        

	private static Logger log = Logger.getLogger(LoginView.class.getName());

	@Inject
	private UserEJB userEJB;

	private String email;
	private String password;

	private User user;
        private Provider provider;
        private Freelancer freelancer;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                this.generateAdmin(); //generates a default admin if required
		try {
			request.login(email, password);
		} catch (ServletException e) {
			e.printStackTrace();
                        log.log(Level.SEVERE, e.getMessage(), e);
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
			// clear the session
			((HttpSession) context.getExternalContext().getSession(false)).invalidate();
                        return "signin";
		}

		Principal principal = request.getUserPrincipal();
                
                this.user = userEJB.findUserById(principal.getName());
                
		log.info("Authentication done for user: " + principal.getName());

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
                
                sessionMap.put("User", user);


		if(request.isUserInRole("admins")) {
                        return "/admin/adminpage?faces-redirect=true";
                }
                else if(request.isUserInRole("providers")){
                        this.provider = userEJB.findProviderById(principal.getName());
                        return "/provider/privatepage?faces-redirect=true";
                }
                else if(request.isUserInRole("freelancers")){
                        this.freelancer = userEJB.findFreelancerById(principal.getName());
                        return "/freelancer/privatepage?faces-redirect=true";
                }
                else{
                        // clear the session
			((HttpSession) context.getExternalContext().getSession(false)).invalidate();
                        return "signin";
		}
	}

	public String logout() {
                FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			this.user = null;
			request.logout();
			// clear the session
                        HttpSession sess =(HttpSession)context.getExternalContext().getSession(false);
                        if(sess  != null) {
                            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
                        }
		} catch (ServletException e) {
			log.log(Level.SEVERE, "Failed to logout user!", e);
		}

		return "/signin?faces-redirect=true";
	}
        
        public void generateAdmin() {
            if(userEJB.findUserById(User.getDEF_ADMIN_EMAIL())==null){
                User user = new User(User.getDEF_ADMIN_EMAIL(), User.getDEF_PWD(), User.getDEF_NAME());
                userEJB.createUser(user, "3");
                log.info("Administrator created.");
            }
        }   
        
	public User getAuthenticatedUser() {
		return user;
	}
        
        public Provider getAuthenticatedProvider() {
		return provider;
	}
        
        public Freelancer getAuthenticatedFreelancer() {
		return freelancer;
	}
        
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
