package pl.senderek.gieldal2.tester.dto;
/**
 * Używane przy autoryzacji użytkownika
 */
public class AuthDTO {
    /**
     * Nazwa użytkownika, podawana do żądania POST w metodzie {@link pl.senderek.gieldal2.tester.service.external.impl.StockApiImpl#postForAuthorization(AuthDTO)}
     */
    private String userName;
    /**
     * Hasło użytkownika, podawane do żądania POST w metodzie {@link pl.senderek.gieldal2.tester.service.external.impl.StockApiImpl#postForAuthorization(AuthDTO)}
     */
    private String password;
    /**
     * Status autoryzacji zwracany przez API
     */
    private Boolean success;
    /**
     * Token użytkownika zwracany przez API, w przypadku błędnych danych zwrócony token jest pusty
     */
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
