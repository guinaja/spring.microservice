package spring.cloud.example.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by prayoon-pc on 6/28/2017.
 */
@Getter
@Setter
public class SpringConfig {
    @Value("${server.port}")
    private String appConfigPort;

}
