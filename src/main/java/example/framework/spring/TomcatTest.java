package example.framework.spring;

import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * @author lr
 * @date 2021/4/8
 */
public class TomcatTest {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("/tomcat");
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        configureEngine(tomcat.getEngine());

        prepareContext(tomcat.getHost());

        tomcat.start();
    }

    static void customizeConnector(Connector connector) {
        int port = 8111;
        connector.setPort(port);

        connector.setProperty("server", "test server");

    }

    static void configureEngine(Engine engine) {

    }

    static void prepareContext(Host host) {

    }
}
