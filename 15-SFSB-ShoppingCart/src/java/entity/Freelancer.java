package entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "FREELANCER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freelancer.findAll", query = "SELECT f FROM Freelancer f"),
    @NamedQuery(name = "Freelancer.findById", query = "SELECT f FROM Freelancer f WHERE f.id = :id"),
    @NamedQuery(name = "Freelancer.findByName", query = "SELECT f FROM Freelancer f WHERE f.name = :name"),
    @NamedQuery(name = "Freelancer.findByEmail", query = "SELECT f FROM Freelancer f WHERE f.email = :email")})
public class Freelancer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;

    public Freelancer() {
    }

    public Freelancer(String name, String email) {
        this.name=name;
        this.email=email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Freelancer)) {
            return false;
        }
        Freelancer other = (Freelancer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Freelancer[ id=" + id + " ]";
    }
    
}
