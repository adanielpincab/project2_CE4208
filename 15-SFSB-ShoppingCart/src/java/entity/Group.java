package entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="user_groups")
@XmlRootElement
public class Group implements Serializable {

	private static final long serialVersionUID = 1528447384986169065L;

        public static final String PROVIDER_GROUP = "providers";
        public static final String FREELANCER_GROUP = "freelancers";
        public static final String ADMIN_GROUP = "admins";
	
	@Id
        @Basic(optional = false)
	@Column(name="email", nullable=false, length=255)
	private String email;
	
	@Column(name="groupname", nullable=false, length=32)
	private String groupname;
	
	public Group() {}

	public Group(String email, String groupname) {
		this.email = email;
		this.groupname = groupname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
