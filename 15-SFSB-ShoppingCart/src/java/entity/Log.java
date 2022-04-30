package entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement
@Entity
@Table(name="Logs")
public class Log implements Serializable {
        /*
            A log that gets stored into the database,
            contains a messgae and a timestamp.
        */

	private static final long serialVersionUID = -5892169641074303723L;
	
	@Id
        @Basic(optional = false)
        @Column(name="timestamp", nullable=false, length=100)
	private String timestamp;
	@Column(name="message", nullable=false, length=500)
	private String message;

        public Log() {
            this.timestamp = getTimestamp();	
            this.message = "";
	}
	public Log(String message) {
                this.timestamp = getTimestamp();
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
        
        private String getTimestamp() {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return timestamp.toString();// 2021-03-24 16:34:26.6665
        }
}