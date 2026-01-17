package io.github.bakhomious.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> trigger(@RequestParam final TYPE type) {
        final var typeStr = type.toString().toLowerCase();
        return clientService.callServer(typeStr);
    }

    public enum TYPE {
        FAST,
        SLOW
    }

}
