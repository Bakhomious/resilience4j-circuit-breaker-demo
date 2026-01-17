package io.github.bakhomious.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping("/fast")
    public String fast() {
        return "Server is running fast!";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(5000);
        return "Server is running slow!";
    }

}
