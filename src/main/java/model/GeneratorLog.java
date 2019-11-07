package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Lukasz Wojtas
 */
@Entity
@Table(name = "generator_log")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "GeneratorLog.findAll", query = "SELECT g FROM GeneratorLog g"),
        @NamedQuery(name = "GeneratorLog.findById", query = "SELECT g FROM GeneratorLog g WHERE g.id = :id")})
public class GeneratorLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "client_id")
    private Integer clientId;
    @Basic(optional = false)
    @Column(name = "active_clients_quantity")
    private Integer activeClientsQuantity;
    @Basic(optional = false)
    @Column(name = "test_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testStartTime;
    @Basic(optional = false)
    @Column(name = "test_type")
    private String testType;
    @Basic(optional = false)
    @Column(name = "req_no")
    private Integer reqNo;
    @Basic(optional = false)
    @Column(name = "req_type")
    private String reqType;
    @Basic(optional = false)
    @Column(name = "resp_type")
    private String respType;
    @Basic(optional = false)
    @Column(name = "req_time")
    private Integer reqTime;
    @Basic(optional = false)
    @Column(name = "backend_time")
    private Integer backendTime;
    @Basic(optional = false)
    @Column(name = "db_selects_quantity")
    private Integer dbSelectsQuantity;
    @Basic(optional = false)
    @Column(name = "db_selects_time")
    private Integer dbSelectsTime;
    @Basic(optional = false)
    @Column(name = "db_updates_quantity")
    private Integer dbUpdatesQuantity;
    @Basic(optional = false)
    @Column(name = "db_updates_time")
    private Integer dbUpdatesTime;

    public GeneratorLog() {
    }

    public GeneratorLog(Integer id) {
        this.id = id;
    }

    public GeneratorLog(Integer id, Integer clientId, Integer activeClientsQuantity, Date testStartTime, String testType, Integer reqNo, String reqType, String respType, Integer reqTime, Integer backendTime, Integer dbSelectsQuantity, Integer dbSelectsTime, Integer dbUpdatesQuantity, Integer dbUpdatesTime) {
        this.id = id;
        this.clientId = clientId;
        this.activeClientsQuantity = activeClientsQuantity;
        this.testStartTime = testStartTime;
        this.testType = testType;
        this.reqNo = reqNo;
        this.reqType = reqType;
        this.respType = respType;
        this.reqTime = reqTime;
        this.backendTime = backendTime;
        this.dbSelectsQuantity = dbSelectsQuantity;
        this.dbSelectsTime = dbSelectsTime;
        this.dbUpdatesQuantity = dbUpdatesQuantity;
        this.dbUpdatesTime = dbUpdatesTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getActiveClientsQuantity() {
        return activeClientsQuantity;
    }

    public void setActiveClientsQuantity(Integer activeClientsQuantity) {
        this.activeClientsQuantity = activeClientsQuantity;
    }

    public Date getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(Date testStartTime) {
        this.testStartTime = testStartTime;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getReqNo() {
        return reqNo;
    }

    public void setReqNo(Integer reqNo) {
        this.reqNo = reqNo;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public Integer getReqTime() {
        return reqTime;
    }

    public void setReqTime(Integer reqTime) {
        this.reqTime = reqTime;
    }

    public Integer getBackendTime() {
        return backendTime;
    }

    public void setBackendTime(Integer backendTime) {
        this.backendTime = backendTime;
    }

    public Integer getDbSelectsQuantity() {
        return dbSelectsQuantity;
    }

    public void setDbSelectsQuantity(Integer dbSelectsQuantity) {
        this.dbSelectsQuantity = dbSelectsQuantity;
    }

    public Integer getDbSelectsTime() {
        return dbSelectsTime;
    }

    public void setDbSelectsTime(Integer dbSelectsTime) {
        this.dbSelectsTime = dbSelectsTime;
    }

    public Integer getDbUpdatesQuantity() {
        return dbUpdatesQuantity;
    }

    public void setDbUpdatesQuantity(Integer dbUpdatesQuantity) {
        this.dbUpdatesQuantity = dbUpdatesQuantity;
    }

    public Integer getDbUpdatesTime() {
        return dbUpdatesTime;
    }

    public void setDbUpdatesTime(Integer dbUpdatesTime) {
        this.dbUpdatesTime = dbUpdatesTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneratorLog)) {
            return false;
        }
        GeneratorLog other = (GeneratorLog) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "GeneratorLog[ id=" + id + " ]";
    }

}
