package spring.cloud.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        String appPort = System.getProperty("server.port");
        if (appPort == null) {
            appPort = "Default Port";
        }
        log.debug("Start Account Service port [{}]", appPort);
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
