package engine;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.reflections.Reflections;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

public class HTTPServer extends Thread {

    private Optional<String> contextPath;
    private Optional<String> port;
    private Optional<String> servletsPackage;
    private Map<String, HttpServlet> servlets = new HashMap<>();

    public HTTPServer() {
        this.port = Optional.ofNullable(System.getenv("PORT"));
    }

    public HTTPServer(final Optional<String> port, final Optional<String> contextPath, final Optional<String> servletsPackage) {
        this.port = port;
        this.contextPath = contextPath;
        this.servletsPackage = servletsPackage;
    }

    private void publishServlet(String servletName, HttpServlet servletObject) {
        servlets.put(servletName, servletObject);
    }

    private void lookForServlets() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(servletsPackage.orElse("endpoint"));
        Set<Class<? extends HttpServlet>> classes = reflections.getSubTypesOf(HttpServlet.class);
        for (Class<? extends HttpServlet> servlet : classes) {
            Class auxClass = Class.forName(servlet.getName());
            publishServlet(servlet.getName(), (HttpServlet) auxClass.newInstance());
        }
    }

    private void runTomcat() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Context ctx = tomcat.addContext(contextPath.orElse("/r"), new File(".").getAbsolutePath());

        tomcat.setPort(Integer.valueOf(port.orElse("8080")));
        for (String servletName : servlets.keySet()) {
            tomcat.addServlet(ctx, servletName, servlets.get(servletName));
            for (String path : servlets.get(servletName).getClass().getAnnotation(WebServlet.class).urlPatterns()) {
                ctx.addServletMapping(path, servletName);
            }
        }

        tomcat.start();
        tomcat.getServer().await();
    }

    @Override
    public void run() {
        try {
            log(Level.INFO, this.getId() + " initialising servlets.");
            lookForServlets();
            log(Level.INFO, this.servlets.size() + " servlets initialised.");
            log(Level.INFO, "Starting Tomcat.");
            runTomcat();
        } catch (Exception e) {
            log(Level.SEVERE, "Tomcat failed. Server will not be initialised.");
        }
    }

    public void log(Level level, String log) {
        System.out.println(level.toString() + " " + log);
    }
}