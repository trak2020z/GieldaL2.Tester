package pl.senderek.gieldal2.tester.service.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.senderek.gieldal2.tester.config.TestConfig;
import pl.senderek.gieldal2.tester.dto.StockApiEntity;
import pl.senderek.gieldal2.tester.dto.StockApiResponse;
import pl.senderek.gieldal2.tester.model.GeneratorLog;
import pl.senderek.gieldal2.tester.model.TestContext;
import pl.senderek.gieldal2.tester.repository.GeneratorLogRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class StockApi {

    private GeneratorLogRepository repository;
    static final String BASE_STOCK_API = TestConfig.API_URL;
    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private void setRepository(GeneratorLogRepository repository) {
        this.repository = repository;
    }

    <T> List<T> getList(TestContext context, String url, Class<T> responseType) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<List<T>>> response = getList(url);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<List<T>> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, null, responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                return body.getData();
            }
        }
        return new LinkedList<>();
    }

    <T> Optional<T> get(TestContext context, String url, Class<T> responseType) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiEntity<T>> response = get(url);
        int requestTime = (int) (System.currentTimeMillis() - start);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiEntity<T> body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, null, responseType.getSimpleName(), requestTime, body);
                repository.save(log);
                return Optional.ofNullable(body.getData());
            }
        }
        return Optional.empty();
    }

    <T> void post(TestContext context, String url, T request) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = post(url, request);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, request.getClass().getSimpleName(), requestTime);
    }

    <T> void put(TestContext context, String url, T request) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = put(url, request);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, request.getClass().getSimpleName(), requestTime);
    }

    void delete(TestContext context, String url) {
        context.nextRequest();
        long start = System.currentTimeMillis();
        ResponseEntity<StockApiResponse> response = delete(url);
        int requestTime = (int) (System.currentTimeMillis() - start);
        saveLog(context, response, null, requestTime);
    }

    private void saveLog(TestContext context, ResponseEntity<StockApiResponse> response, String requestType, int requestTime) {
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            StockApiResponse body = response.getBody();
            if (body != null) {
                GeneratorLog log = new GeneratorLog(context, requestType, null, requestTime, body);
                repository.save(log);
            }
        }
    }

    private <T> ResponseEntity<StockApiEntity<List<T>>> getList(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<StockApiEntity<List<T>>>() {
        });
    }

    private <T> ResponseEntity<StockApiEntity<T>> get(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<StockApiEntity<T>>() {
        });
    }

    private <T> ResponseEntity<StockApiResponse> post(String url, T request) {
        HttpEntity<T> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, StockApiResponse.class);
    }

    private <T> ResponseEntity<StockApiResponse> put(String url, T request) {
        HttpEntity<T> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, StockApiResponse.class);
    }

    private ResponseEntity<StockApiResponse> delete(String url) {
        return restTemplate.exchange(url, HttpMethod.DELETE, null, StockApiResponse.class);
    }
}
