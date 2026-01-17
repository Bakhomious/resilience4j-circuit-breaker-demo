package io.github.bakhomious.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;


@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "slowService", fallbackMethod = "fallbackResponse")
    public ResponseEntity<String> callServer(String endpoint) {
        final var response = this.webClient.get()
            .uri("/server/" + endpoint)
            .retrieve()
            .bodyToMono(String.class)
            .timeout(Duration.ofSeconds(1))
            .block();

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> fallbackResponse(String endpoint, Throwable throwable) {
        final var message = "Fallback response due to timeout or error. Endpoint: %s, Error: %s".formatted(
            endpoint,
            throwable.getMessage()
        );

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
    }

}
