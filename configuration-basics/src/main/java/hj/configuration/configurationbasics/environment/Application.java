package hj.configuration.configurationbasics.environment;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


@Configuration
//<1>
@PropertySource("some.properties")
public class Application {
    private final Log log = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class);
    }

    //<2>
    @Bean
    static PropertySourcesPlaceholderConfigurer pspc() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    //<3>
    @Value("${configuration.projectName}")
    private String fieldValue;

    //<4>
    @Autowired
    Application(@Value("${configuration.projectName}") String pn) {
        log.info("Application constructor: " + pn);
    }

    //<5>
    @Value("${configuration.projectName}")
    void setProjectName(String projectName) {
        log.info("setProjectName: " + projectName);
    }

    //<6>
    @Autowired
    void setEnvironment(Environment env) {
        log.info("setEnvironment:" + env.getProperty("configuration.projectName"));
    }

    //<7>
    @Bean
    InitializingBean both(Environment env,
                          @Value("${configuration.projectName}") String projectName) {
        return () -> {
            log.info("@Bean with both dependencies (projectName):" + projectName);
            log.info("@Bean with both dependencies (env):" + env.getProperty("configuration.projectName"));
        };
    }

    @PostConstruct
    void afterProperties() {
        log.info("fieldValue:" + this.fieldValue);
    }
}
