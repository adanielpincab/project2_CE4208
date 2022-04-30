package managedbeans;

import java.io.Serializable;
import java.util.logging.Logger;
import jakarta.faces.application.FacesMessage;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.inject.Inject;
import ejb.UserEJB;
import entity.User;
import jakarta.inject.Named;

@Named(value = "registerView")
@SessionScoped
public class RegisterView implements Serializable {

	private static final long serialVersionUID = 1685823449195612778L;

	private static Logger log = Logger.getLogger(RegisterView.class.getName());

	@Inject
	private UserEJB userEJB;

	private String name;
	private String email;
        private String userType;
	private String password;
	private String confirmPassword;

	public void validatePassword(ComponentSystemEvent event) {
                /**
                 * Checks password validity.
                 */
		FacesContext facesContext = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("password");
		String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString();

		// Let required="true" do its job.
		if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		}

		if (!password.equals(confirmPassword)) {
			FacesMessage msg = new FacesMessage("Confirm password does not match password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(passwordId, msg);
			facesContext.renderResponse();
		}

		if (userEJB.findUserById(email) != null) {
			FacesMessage msg = new FacesMessage("User with this e-mail already exists");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(passwordId, msg);
			facesContext.renderResponse();
		}

	}

	public String register() {
                /**
                 * Registers a user into the database.
                 */
		User user = new User(email, password, name);
		userEJB.createUser(user, userType);
		log.info("New user created with e-mail: " + email + " and name: " + name);
                userEJB.logMessage("NEW USER CREATED: <" + email + ">");
		return "regdone";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
}
