package unlp.info.chatbot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import unlp.info.chatbot.configuration.ComponentConfiguration;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static java.lang.System.exit;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /** Context path of the application. */
  private static final String APP_PATH = "/*";

  public static void main(String[] args) throws Exception {
    LOGGER.info("[APP] Starting Chatbot - Environment Configuration: {}", "LOCAL");

    Server server = new Server(9290);

    AnnotationConfigWebApplicationContext spring = new AnnotationConfigWebApplicationContext();
    spring.register(ComponentConfiguration.class);

    ServletContextHandler servletHandler =
        new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    servletHandler.setContextPath("/chatbot");

    DispatcherServlet servlet = new DispatcherServlet(spring);
    servlet.setDispatchOptionsRequest(true);
    servletHandler.addServlet(new ServletHolder(servlet), APP_PATH);

    addGZipHandler(servletHandler);

    FilterHolder encodingFilter =
        servletHandler.addFilter(
            CharacterEncodingFilter.class, APP_PATH, EnumSet.of(DispatcherType.REQUEST));
    encodingFilter.setInitParameter("encoding", "UTF-8");
    encodingFilter.setInitParameter("forceEncoding", "true");

    server.setHandler(servletHandler);
    server.setStopAtShutdown(true);

    try {
      server.start();
      server.join();
    } catch (Exception exception) {
      LOGGER.error("[APP] Error starting the application " + exception.getMessage());
      exit(1);
    }
  }

  private static void addGZipHandler(ServletContextHandler servletHandler) {
    LOGGER.info("Loading gzip {} handler", servletHandler.getDisplayName());
    GzipHandler gzipHandler = new GzipHandler();
    gzipHandler.setMinGzipSize(1024);
    gzipHandler.setIncludedMethods("GET,POST,PATCH,PUT,DELETE");
    gzipHandler.setIncludedMimeTypes("application/json", "text/json");
    gzipHandler.setHandler(servletHandler);
  }

}
