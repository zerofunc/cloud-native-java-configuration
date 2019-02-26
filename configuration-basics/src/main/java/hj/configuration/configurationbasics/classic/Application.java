package hj.configuration.configurationbasics.classic;

import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classic.xml");
	}
	
	public void setConfigurationProjectName(String pn) {
		LogFactory.getLog(getClass()).info("the configuration project anme is "+ pn);
	}
}
