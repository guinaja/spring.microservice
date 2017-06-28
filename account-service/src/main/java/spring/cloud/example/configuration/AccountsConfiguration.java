package spring.cloud.example.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The accounts Spring configuration.
 *
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
@EntityScan("spring.cloud.example.repository")
@EnableJpaRepositories("spring.cloud.example.repository")
@PropertySource("classpath:db-config.properties")
@Slf4j
public class AccountsConfiguration {

    @Bean
    public DataSource dataSource() {
        log.info("dataSource() invoked");

        // Create an in-memory H2 relational database containing some demo
        // accounts.
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:dbfile/schema.sql")
                .addScript("classpath:dbfile/data.sql").build();

        log.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_ACCOUNT");
        log.info("System has " + accounts.size() + " accounts");

        // Populate with random balances
        Random rand = new Random();

        for (Map<String, Object> item : accounts) {
            String number = (String) item.get("number");
            BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
            jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
        }

        return dataSource;
    }
}
