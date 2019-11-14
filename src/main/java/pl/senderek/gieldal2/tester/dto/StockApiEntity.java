package pl.senderek.gieldal2.tester.dto;

public class StockApiEntity<T> extends StockApiResponse {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
