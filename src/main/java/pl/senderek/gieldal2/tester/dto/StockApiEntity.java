package pl.senderek.gieldal2.tester.dto;
/**
 * Opakowuje informacje zwracane z API przy każdym zapytaniu, zawarte w {@link StockApiResponse}, w obiekt zawierający także dane specyficzne dla danego zapytania.
 * @param <T> Typ danych zwracany przez zapytanie
 */
public class StockApiEntity<T> extends StockApiResponse {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
