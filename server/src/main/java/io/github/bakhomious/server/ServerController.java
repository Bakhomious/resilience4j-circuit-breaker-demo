package io.github.bakhomious.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;


@RestController
@RequestMapping("/server")
public class ServerController {

    static final Logger LOG = LoggerFactory.getLogger(ServerController.class);

    @Value("${slow.time}")
    private Duration waitTime;

    @GetMapping("/fast")
    public String fast() {
        return "Server is running fast!";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        LOG.info("Waiting for {} ms", waitTime.toMillis());
        Thread.sleep(waitTime);
        return "Server is running slow!";
    }

}
