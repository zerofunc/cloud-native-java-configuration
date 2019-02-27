package hj.configuration.configurationbasics.boot;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

//<1>
@EnableConfigurationProperties
@SpringBootApplication
public class Application {
	private final Log log = LogFactory.getLog(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Autowired
	public Application(ConfigurationProjectProperties cp) {
		log.info("configurationProjectProperties.projectName =" + cp.getProjectName());
	}
	
}

@Component
//<2>
@ConfigurationProperties("configuration")
class ConfigurationProjectProperties {
	private String projectName;//<3>
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}