# Resilience4j Circuit Breaker Example

This is a simple example demonstrating how to use Resilience4j's Circuit Breaker in a Java application.

## Client

The client application makes HTTP requests to a server and uses Resilience4j's Circuit Breaker to handle failures gracefully.

### Running

```shell
mvn clean install spring-boot:run
```

The client application will start on `http://localhost:8080`.

### Endpoints

- `GET /client/test?type={type}`: Makes a request to the server. The `type` parameter can be:
  - `FAST`: Simulates a fast response from the server.
  - `SLOW`: Simulates a slow response from the server.

## Server

The server application simulates different response times based on the request type.

### Running

```shell
mvn clean install spring-boot:run
```

The server application will start on `http://localhost:8081`.

## Tester

A simple tester script is provided to test the client-server interaction.

### Running

```shell
./tester.sh
```

## Watcher

A watcher script is provided to monitor the circuit breaker state.

### Running

```shell
./watcher.sh
```

