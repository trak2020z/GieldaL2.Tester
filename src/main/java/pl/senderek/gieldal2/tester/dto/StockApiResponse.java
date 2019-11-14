package pl.senderek.gieldal2.tester.dto;

public class StockApiResponse {
    private Integer backendTime;
    private Integer selectsTime;
    private Integer selectsCount;
    private Integer updatesTime;
    private Integer updatesCount;

    public Integer getBackendTime() {
        return backendTime;
    }

    public void setBackendTime(Integer backendTime) {
        this.backendTime = backendTime;
    }

    public Integer getSelectsTime() {
        return selectsTime;
    }

    public void setSelectsTime(Integer selectsTime) {
        this.selectsTime = selectsTime;
    }

    public Integer getSelectsCount() {
        return selectsCount;
    }

    public void setSelectsCount(Integer selectsCount) {
        this.selectsCount = selectsCount;
    }

    public Integer getUpdatesTime() {
        return updatesTime;
    }

    public void setUpdatesTime(Integer updatesTime) {
        this.updatesTime = updatesTime;
    }

    public Integer getUpdatesCount() {
        return updatesCount;
    }

    public void setUpdatesCount(Integer updatesCount) {
        this.updatesCount = updatesCount;
    }
}
