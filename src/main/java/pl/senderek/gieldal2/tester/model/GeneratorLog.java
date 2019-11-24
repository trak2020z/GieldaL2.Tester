package pl.senderek.gieldal2.tester.model;

import pl.senderek.gieldal2.tester.dto.StockApiResponse;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Lukasz Wojtas
 */
@Entity
@Table(name = "generator_log")
public class GeneratorLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "active_clients_quantity")
    private Integer activeClientsQuantity;

    @Column(name = "test_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testStartTime;

    @Column(name = "test_type")
    private String testType;

    @Column(name = "req_no")
    private Integer reqNo;

    @Column(name = "req_type")
    private String reqType;

    @Column(name = "resp_type")
    private String respType;

    @Column(name = "req_time")
    private Integer reqTime;

    @Column(name = "backend_time")
    private Integer backendTime;

    @Column(name = "db_selects_quantity")
    private Integer dbSelectsQuantity;

    @Column(name = "db_selects_time")
    private Integer dbSelectsTime;

    @Column(name = "db_updates_quantity")
    private Integer dbUpdatesQuantity;

    @Column(name = "db_updates_time")
    private Integer dbUpdatesTime;

    @Column(name = "db_inserts_quantity")
    private Integer dbInsertsQuantity;

    @Column(name = "db_inserts_time")
    private Integer dbInsertsTime;

    @Column(name = "db_deletes_quantity")
    private Integer dbDeletesQuantity;

    @Column(name = "db_deletes_time")
    private Integer dbDeletesTime;

    public GeneratorLog() {

    }

    public GeneratorLog(TestContext context, Integer activeClientsQuantity, String testType, String reqType, String respType, Integer reqTime, StockApiResponse response) {
        this.clientId = context.getClientId();
        this.activeClientsQuantity = activeClientsQuantity;
        this.testStartTime = context.getTestStartTime();
        this.testType = testType;
        this.reqNo = context.getReqNo().get();
        this.reqType = reqType;
        this.respType = respType;
        this.reqTime = reqTime;
        this.backendTime = response.getBackendTime();
        this.dbSelectsQuantity = response.getSelectsCount();
        this.dbSelectsTime = response.getSelectsTime();
        this.dbUpdatesQuantity = response.getUpdatesCount();
        this.dbUpdatesTime = response.getUpdatesTime();
        this.dbInsertsQuantity = response.getInsertsCount();
        this.dbInsertsTime = response.getInsertsTime();
        this.dbDeletesQuantity = response.getDeletesCount();
        this.dbDeletesTime = response.getDeletesTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
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

    public Integer getDbInsertsQuantity() {
        return dbInsertsQuantity;
    }

    public void setDbInsertsQuantity(Integer dbInsertsQuantity) {
        this.dbInsertsQuantity = dbInsertsQuantity;
    }

    public Integer getDbInsertsTime() {
        return dbInsertsTime;
    }

    public void setDbInsertsTime(Integer dbInsertsTime) {
        this.dbInsertsTime = dbInsertsTime;
    }

    public Integer getDbDeletesQuantity() {
        return dbDeletesQuantity;
    }

    public void setDbDeletesQuantity(Integer dbDeletesQuantity) {
        this.dbDeletesQuantity = dbDeletesQuantity;
    }

    public Integer getDbDeletesTime() {
        return dbDeletesTime;
    }

    public void setDbDeletesTime(Integer dbDeletesTime) {
        this.dbDeletesTime = dbDeletesTime;
    }
}
