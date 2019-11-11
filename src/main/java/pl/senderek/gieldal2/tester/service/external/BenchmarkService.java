package pl.senderek.gieldal2.tester.service.external;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.senderek.gieldal2.tester.config.TestConfig;

import java.util.List;

public abstract class BenchmarkService {

    protected static String STOCK_API = TestConfig.API_URL;

    private static RestTemplate restTemplate = new RestTemplate();

    protected <T> ResponseEntity<List<T>> getForEntityList(String url, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<java.util.List<T>>(){});
    }

    protected <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }

    protected <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType) {
        return restTemplate.postForEntity(url, request, responseType);
    }

    protected <T, U> ResponseEntity<T> putForEntity(String url, U request, Class<T> responseType) {
        HttpEntity<U> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType);
    }

    protected <T, U> ResponseEntity<T> deleteForEntity(String url, U request, Class<T> responseType) {
        HttpEntity<U> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);
    }

    protected ResponseEntity get(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    protected <T> ResponseEntity post(String url, T request) {
        HttpEntity<T> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    protected <T> ResponseEntity put(String url, T request) {
        HttpEntity<T> requestEntity = new HttpEntity<>(request);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    protected <T> ResponseEntity delete(String url) {
        return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }
}
