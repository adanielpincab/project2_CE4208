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

/*
    A log that gets stored into the database
*/

@XmlRootElement
@Entity
@Table(name="Logs")
public class Log implements Serializable {
	private static final long serialVersionUID = -5892169641074303723L;
	
	@Id
        @Basic(optional = false)
	@Column(name="message", nullable=false, length=500)
	private String message;

        public Log() {
		this.message = "";
	}
	public Log(String message) {
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
}
