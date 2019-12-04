package pl.senderek.gieldal2.tester.service.external.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pl.senderek.gieldal2.tester.dto.AuthDTO;
import pl.senderek.gieldal2.tester.dto.ContextDTO;
import pl.senderek.gieldal2.tester.dto.StockApiEntity;
import pl.senderek.gieldal2.tester.dto.StockApiResponse;
import pl.senderek.gieldal2.tester.exception.InvalidCredentialsException;
import pl.senderek.gieldal2.tester.exception.ServiceUnauthorizedException;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.repository.GeneratorLogRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class StockApiImpl {
    /**
     * Informacja o typie testu pobierana z application.properties
     */
    @Value("${test.TEST_TYPE}")
    public String TEST_TYPE;
    /**
     * Informacja o ilości klientów pobierana z application.properties
     */
    @Value("${test.CLIENTS_QUANTITY}")
    public String CLIENTS_QUANTITY;
    /**
     * Link do autentykacji w API
     */
    @Value("${test.API_URL}/api/Auth")
    private String AUTH_API;
    /**
     * Link do informacji o zalogowanym użytkowniku zwracanych z API
     */
    @Value("${test.API_URL}/api/Context")
    private String CONTEXT_API;

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private GeneratorLogRepository repository;

    @Autowired
    private void setRepository(GeneratorLogRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    private void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Generyczna metoda wywołująca żądanie typu GET , za pomocą metody {@link #getList(String, String)}.
     * Jeśli żądanie wykona się pomyślnie to następuje zapisanie wymaganych informacje w bazie danych przy pomocy metody {@link pl.senderek.gieldal2.tester.repository.GeneratorLogRepository#save(Object)}
     * @param context Informacja o wykonywanym teście
     * @param url Adres wywoływanego żądania
     * @param responseType Typ zwracany przez żadanie z API
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ zwracanej przez metodę listy
     * @return Zwraca listę obiektów podanego typu, wyłuskaną ze zwracanego obiektu z API przy pomocy metody {@link StockApiEntity#getData()}
     */
    <T> List<T> getList(TestContext context, String url, Class<T> responseType, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<List<T>>> response = getList(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<List<T>> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, new Integer(CLIENTS_QUANTITY), TEST_TYPE, "GET", responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, responseType);
                return objectMapper.convertValue(body.getData(), collectionType);
            }
        }
        return new LinkedList<>();
    }
    /**
     * Generyczna metoda wywołująca żądanie typu GET za pomocą metody {@link #get(String, String)}.
     * Jeśli żądanie wykona się pomyślnie to następuje zapisanie wymaganych informacje w bazie danych przy pomocy metody {@link pl.senderek.gieldal2.tester.repository.GeneratorLogRepository#save(Object)}
     * @param context Informacja o wykonywanym teście
     * @param url Adres wywoływanego żądania
     * @param responseType Typ zwracany przez żadanie z API
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ zwracanego przez metode obiektu
     * @return Zwraca obiekt podanego typu, wyłuskany z obiektu zwracanego przez API przy pomocy metody {@link StockApiEntity#getData()} lub {@code null} jeśli szukany obiekt nie istnieje
     */
    <T> Optional<T> get(TestContext context, String url, Class<T> responseType, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<T>> response = get(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<T> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, new Integer(CLIENTS_QUANTITY), TEST_TYPE, "GET", responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                JavaType javaType = objectMapper.getTypeFactory().constructType(responseType);
                return Optional.ofNullable(objectMapper.convertValue(body.getData(), javaType));
            }
        }
        return Optional.empty();
    }
    /**
     * Generyczna metoda wywołująca żądanie typu POST za pomocą metody {@link #post(String, Object, String)}.
     * Zapisuje wymagane informacje w bazie danych przy pomocy metody {@link #saveLog(TestContext, ResponseEntity, String, int)}
     * @param context Informacja o wykonywanym teście
     * @param url Adres wywoływanego żądania
     * @param request Obiekt typu {@code <T>} dołączany do żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ obiektu przekazywanego do metody POST
     */
    <T> void post(TestContext context, String url, T request, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = post(url, request, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "POST", requestTime);
    }
    /**
     * Generyczna metoda wywołująca żądanie typu PUT za pomocą metody {@link #put(String, Object, String)}.
     * Zapisuje wymagane informacje w bazie danych przy pomocy metody {@link #saveLog(TestContext, ResponseEntity, String, int)}
     * @param context Informacja o wykonywanym teście
     * @param url Adres wywoływanego żądania
     * @param request Obiekt typu {@code <T>} dołączany do żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ obiektu przekazywanego do metody PUT
     */
    <T> void put(TestContext context, String url, T request, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = put(url, request, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "PUT", requestTime);
    }
    /**
     * Generyczna metoda wywołująca żądanie typu DELETE za pomocą metody {@link #delete(String, String)}.
     * Zapisuje wymagane informacje w bazie danych przy pomocy metody {@link #saveLog(TestContext, ResponseEntity, String, int)}
     * @param context Informacja o wykonywanym teście
     * @param url adres wywoływanego żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     */
    void delete(TestContext context, String url, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = delete(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "DELETE", requestTime);
    }
    /**
     * Zapisuje w bazie danych informacje o wykonanym żądaniu, używana dla żądań nie zwracających informacji w polu {@link pl.senderek.gieldal2.tester.dto.StockApiEntity#data}
     * @param context Informacja o wykonywanym teście
     * @param response dane zwrócone z API
     * @param requestType typ żądania
     * @param requestTime czas wykonania żądania
     */

    private void saveLog(TestContext context, ResponseEntity<StockApiResponse> response, String requestType, int requestTime) {
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiResponse body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, new Integer(CLIENTS_QUANTITY), TEST_TYPE, requestType, "StockApiResponse", requestTime, body);
                repository.save(log);
        }
        }
    }

    /**
     * Metoda wywołująca właściwe żądanie GET, gdy w zwróconym z API obiekcie pole {@link pl.senderek.gieldal2.tester.dto.StockApiEntity#data} jest listą obiektów typu {@code <T>}
     * @param url Adres wywoływanego żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ listy zwracanej przez API w polu {@link pl.senderek.gieldal2.tester.dto.StockApiEntity#data}
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała
     */
    private <T> ResponseEntity<StockApiEntity<List<T>>> getList(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<StockApiEntity<List<T>>>() {
        });
    }
    /**
     * Metoda wywołująca właściwe żądanie GET, gdy w zwróconym z API obiekcie pole {@link pl.senderek.gieldal2.tester.dto.StockApiEntity#data} jest obiektem typu {@code <T>}
     * @param url Adres wywoływanego żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ zwracany przez API w polu {@link pl.senderek.gieldal2.tester.dto.StockApiEntity#data}
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała
     */
    private <T> ResponseEntity<StockApiEntity<T>> get(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<StockApiEntity<T>>() {
        });
    }

    /**
     * Metoda wywołująca właściwe żądanie POST
     * @param url Adres wywoływanego żądania
     * @param request Obiekt typu {@code <T>} dołączany do żądania POST
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ obiektu dołączanego do żądania
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała
     */
    private <T> ResponseEntity<StockApiResponse> post(String url, T request, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<T> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, StockApiResponse.class);
    }

    /**
     * Metoda wywołująca właściwe żądanie PUT
     * @param url Adres wywoływanego żądania
     * @param request Obiekt typu {@code <T>} dołączany do żądania PUT
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @param <T> Typ obiektu dołączanego do żądania
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała
     */
    private <T> ResponseEntity<StockApiResponse> put(String url, T request, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<T> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, StockApiResponse.class);
    }

    /**
     * Metoda wywołująca właściwe żądanie DELETE
     * @param url Adres wywoływanego żądania
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała
     */
    private ResponseEntity<StockApiResponse> delete(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, StockApiResponse.class);
    }

    /**
     * Metoda wywołująca właściwe żądanie POST, służące autoryzacji użytkownika i uzyskania jego tokenu
     * @param request Obiekt typu {@link pl.senderek.gieldal2.tester.dto.AuthDTO} zawierający nazwę użytkownika i hasło, dołączany do żądania
     * @return Zwraca odpowiedź HTTP złożoną z statusu, nagłówka oraz ciała. W ciele znajduje się nowy obiekt typu {@link pl.senderek.gieldal2.tester.dto.AuthDTO}
     * zawierający status oraz token, jeśli autoryzacja przebiegła pomyślnie.
     */
    private ResponseEntity<AuthDTO> postForAuthorization(AuthDTO request) {
        HttpEntity<AuthDTO> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(AUTH_API, HttpMethod.POST, requestEntity, AuthDTO.class);
    }

    /**
     * Metoda autoryzyjąca użykownika przez wywołanie funkcji {@link #postForAuthorization(AuthDTO)}
     * @param user Autoryzowany użytkownik
     * @return Metoda zwraca token użytkownika jeśli jego dane są poprawne. W innym przypadku następuje zgłoszenie wyjątku {@link pl.senderek.gieldal2.tester.exception.InvalidCredentialsException}
     */
    public String authenticateUser(User user) {
        AuthDTO authRequest = new AuthDTO();
        authRequest.setUserName(user.getLogin());
        authRequest.setPassword(user.getPassword());
        AuthDTO authResponse = postForAuthorization(authRequest).getBody();
        if (authResponse != null && authResponse.getSuccess())
            return authResponse.getToken();
        else
            throw new InvalidCredentialsException(authRequest);
    }

    /**
     * Metoda wywołuje żądanie GET na adresie {@link #CONTEXT_API}
     * @param token Token zalogowanego użytkownika, uzyskany za pomocą metody {@link #authenticateUser(User)}
     * @return Zwraca obiekt typu {@link pl.senderek.gieldal2.tester.model.User} z uzupełnionymi informacjami o ofertach kupna, sprzedaży oraz posiadanych udziałach.
     * Informacje te uzyskane są przez wywołanie żądania
     */
    public User getUserContext(String token) {
        ResponseEntity<StockApiEntity<ContextDTO>> response = get(CONTEXT_API, token);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<ContextDTO> body = response.getBody();
            if (body != null) {
                JavaType javaType = objectMapper.getTypeFactory().constructType(ContextDTO.class);
                ContextDTO context = objectMapper.convertValue(body.getData(), javaType);
                if (context != null && context.getUser() != null) {
                    User user = context.getUser();
                    user.setShares(context.getShares());
                    user.setBuyOffers(context.getBuyOffers());
                    user.setSellOffers(context.getSellOffers());
                    return user;
                }
            }
        }
        return null;
    }
}
