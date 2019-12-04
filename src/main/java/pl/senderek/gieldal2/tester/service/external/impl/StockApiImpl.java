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
    @Value("${test.TEST_TYPE}")
    private String TEST_TYPE;
    @Value("${test.CLIENTS_QUANTITY}")
    private Integer CLIENTS_QUANTITY;
    @Value("${test.API_URL}/api/Auth")
    private String AUTH_API;
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

    <T> List<T> getList(TestContext context, String url, Class<T> responseType, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<List<T>>> response = getList(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<List<T>> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, CLIENTS_QUANTITY, TEST_TYPE, "GET", responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, responseType);
                return objectMapper.convertValue(body.getData(), collectionType);
            }
        }
        return new LinkedList<>();
    }

    <T> Optional<T> get(TestContext context, String url, Class<T> responseType, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<T>> response = get(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<T> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, CLIENTS_QUANTITY, TEST_TYPE, "GET", responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                JavaType javaType = objectMapper.getTypeFactory().constructType(responseType);
                return Optional.ofNullable(objectMapper.convertValue(body.getData(), javaType));
            }
        }
        return Optional.empty();
    }

    <T> void post(TestContext context, String url, T request, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = post(url, request, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "POST", requestTime);
    }

    <T> void put(TestContext context, String url, T request, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = put(url, request, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "PUT", requestTime);
    }

    void delete(TestContext context, String url, String token) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = delete(url, token);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, "DELETE", requestTime);
    }

    private void saveLog(TestContext context, ResponseEntity<StockApiResponse> response, String requestType, int requestTime) {
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiResponse body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, CLIENTS_QUANTITY, TEST_TYPE, requestType, "StockApiResponse", requestTime, body);
                repository.save(log);
            }
        }
    }

    private <T> ResponseEntity<StockApiEntity<List<T>>> getList(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<StockApiEntity<List<T>>>() {
        });
    }

    private <T> ResponseEntity<StockApiEntity<T>> get(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<StockApiEntity<T>>() {
        });
    }

    private <T> ResponseEntity<StockApiResponse> post(String url, T request, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<T> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, StockApiResponse.class);
    }

    private <T> ResponseEntity<StockApiResponse> put(String url, T request, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<T> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, StockApiResponse.class);
    }

    private ResponseEntity<StockApiResponse> delete(String url, String token) {
        if (token == null)
            throw new ServiceUnauthorizedException();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, StockApiResponse.class);
    }

    private ResponseEntity<AuthDTO> postForAuthorization(AuthDTO request) {
        HttpEntity<AuthDTO> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(AUTH_API, HttpMethod.POST, requestEntity, AuthDTO.class);
    }

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
