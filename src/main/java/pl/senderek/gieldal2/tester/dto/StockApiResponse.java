package pl.senderek.gieldal2.tester.dto;
/**
 *  Przedstawia informacje zwracane przy każdym zapytaniu z API
 */
public class StockApiResponse {
    private Integer backendTime;

    private Integer selectsTime;
    private Integer selectsCount;
    private Integer updatesTime;
    private Integer updatesCount;
    private Integer insertsTime;
    private Integer insertsCount;
    private Integer deletesTime;
    private Integer deletesCount;

    public Integer getInsertsTime() { return insertsTime; }

    public void setInsertsTime(Integer insertsTime) { this.insertsTime = insertsTime; }

    public Integer getInsertsCount() { return insertsCount; }

    public void setInsertsCount(Integer insertsCount) { this.insertsCount = insertsCount; }

    public Integer getDeletesTime() { return deletesTime; }

    public void setDeletesTime(Integer deletesTime) { this.deletesTime = deletesTime; }

    public Integer getDeletesCount() { return deletesCount; }

    public void setDeletesCount(Integer deletesCount) { this.deletesCount = deletesCount; }

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
