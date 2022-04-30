package entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findByTitle", query = "SELECT j FROM Jobs j WHERE j.title = :title"),
    @NamedQuery(name = "Jobs.findByKeywords", query = "SELECT j FROM Jobs j WHERE j.keywords = :keywords"),
    @NamedQuery(name = "Jobs.findByStatus", query = "SELECT j FROM Jobs j WHERE j.status = :status"),
    @NamedQuery(name = "Jobs.getJobsID", query = "SELECT MAX(j.jobsId) from Jobs j"),
    @NamedQuery(name = "Jobs.getProviderID", query = "SELECT j FROM Jobs j WHERE j.providerId = :providerId")})

public class Jobs implements Serializable {

    @Size(max = 255)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "FREELANCER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Freelancer freelancerId;
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Provider providerId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer jobsId;

    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "KEYWORDS")
    private String keywords;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "PAYMENT")
    private Double payment;



    public Jobs() {
    }

    public Jobs(String title, String keywords, String description, Double payment,
                String status, Provider provider) {
        /**
         * Job class
         * @param String title: job title
         * @param String keywords: job kwyword
         * @param String description: job description 
         * @param Double payment: job payment (eur)
         * @param String status: job status
         * @param Provider provider: job provider
        */
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        this.payment = payment;
        this.status = status;
        this.providerId = provider;
    }

    public Integer getJobsId() {
        return jobsId;
    }

    public void setJobsId(Integer jobsId) {
        this.jobsId = jobsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobsId != null ? jobsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.jobsId == null && other.jobsId != null) || (this.jobsId != null && !this.jobsId.equals(other.jobsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Jobs[ jobsId=" + jobsId + " ]";
    }

    public void setTitle(Jobs title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Freelancer getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Freelancer freelancerId) {
        this.freelancerId = freelancerId;
    }
    
}
