package uz.company.employeemanagementsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import uz.company.employeemanagementsystem.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@Slf4j
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EmployeeManagementSystemApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        log.info("""
                        \n----------------------------------------------------------
                        \tApplication name :{}
                        \tSwagger URL: {}
                        \tJava version: {}
                        \tTimezone: {}
                        \tProfile(s): \t{}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("application.serverUrl") + "/swagger-ui/index.html",
                env.getProperty("java.version"),
                env.getProperty("user.timezone"),
                env.getActiveProfiles());
    }
}
