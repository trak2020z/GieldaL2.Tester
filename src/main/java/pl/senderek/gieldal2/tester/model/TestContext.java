package pl.senderek.gieldal2.tester.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TestContext {
    private Long clientId;
    private Integer activeClientsQuantity;
    private Date testStartTime;
    private String testType;
    private AtomicInteger reqNo;

    public TestContext(Long clientId, Integer activeClientsQuantity, String testType) {
        this.clientId = clientId;
        this.activeClientsQuantity = activeClientsQuantity;
        this.testType = testType;
        testStartTime = new Date();
        reqNo = new AtomicInteger(0);
    }

    public void nextRequest() {
        reqNo.incrementAndGet();
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

    public AtomicInteger getReqNo() {
        return reqNo;
    }

    public void setReqNo(AtomicInteger reqNo) {
        this.reqNo = reqNo;
    }
}
