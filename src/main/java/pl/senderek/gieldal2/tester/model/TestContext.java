package pl.senderek.gieldal2.tester.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TestContext {
    private Long clientId;
    private Date testStartTime;
    private AtomicInteger reqNo;

    public TestContext(Long clientId) {
        this.clientId = clientId;
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

    public Date getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(Date testStartTime) {
        this.testStartTime = testStartTime;
    }

    public AtomicInteger getReqNo() {
        return reqNo;
    }

    public void setReqNo(AtomicInteger reqNo) {
        this.reqNo = reqNo;
    }
}
