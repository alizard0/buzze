package engine;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HTTPManager {

    private static List<HTTPServer> servers = new ArrayList<>();

    private static void lookForServers() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("server");
        Set<Class<? extends HTTPServer>> classes = reflections.getSubTypesOf(HTTPServer.class);
        for (Class<? extends HTTPServer> server : classes) {
            Class auxClass = Class.forName(server.getName());
            servers.add((HTTPServer) auxClass.newInstance());
        }
    }

    private static void initServers() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        System.out.println("Buzze starting execution ...");
        lookForServers();
        System.out.println("Found " + servers.size() + " http servers.");
        System.out.println("Buzze is launching the servers.");
        for (HTTPServer server : servers) {
            new Thread(server).start();
        }
        waitThreads();
    }

    private static void waitThreads() {
        try {
            for (int i = 0; i < servers.size(); i++)
                servers.get(i).join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted. " + e.getMessage());
        }
        System.out.println("Buzze stopping.");
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        initServers();
    }
}
