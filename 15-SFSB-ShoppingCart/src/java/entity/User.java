package entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.email = :email")
})
@Table(name="users")
public class User implements Serializable {
        // Default admin login:
        private static String DEF_ADMIN_EMAIL= "admin@admin.com";
        private static String DEF_NAME= "admin";
        private static String DEF_PWD= "admin";
        
	private static final long serialVersionUID = -5892169641074303723L;
	
	@Id
        @Basic(optional = false)
	@Column(name="email", nullable=false, length=255)
	private String email;
	
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Column(name="name", nullable=false, length=30)
	private String name;
        
	public User(){}

	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        public static String getDEF_ADMIN_EMAIL() {
            return DEF_ADMIN_EMAIL;
        }

        public static void setDEF_ADMIN_EMAIL(String DEF_ADMIN_EMAIL) {
            User.DEF_ADMIN_EMAIL = DEF_ADMIN_EMAIL;
        }

        public static String getDEF_NAME() {
            return DEF_NAME;
        }

        public static void setDEF_NAME(String DEF_NAME) {
            User.DEF_NAME = DEF_NAME;
        }

        public static String getDEF_PWD() {
            return DEF_PWD;
        }

        public static void setDEF_PWD(String DEF_PWD) {
            User.DEF_PWD = DEF_PWD;
        } 
}
