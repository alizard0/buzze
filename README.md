# Buzze a minimalist java web framework

Buzze provides a easy way to implement web-servers or microservices in java using tomcat as a servlet-container. In order to create a server, you only have to extend the *HTTPServer* class and, when you call the parent constructor, you can pass the desired http-port, the path and the package name where your servlets classes are.

Technically, the HTTPServer will automatically discover your servlets classes and register them in the tomcat embedded server and then deploy everything in a new thread. Therefore, if you create 5 classes which extend the HTTPServer, you will end up with 5 instances of tomcat running in 5 different threads. If you stop the master execution, the threads are automatically killed. 

In terms of writting a micro-service, it is pretty much the same. You only need to provide a different servlet package name as the HTTPServer only manages and runs servlets. So the business rules that you write inside of these classes are not analysed by the HTTPServer.

Finally, the micro UI framework is basically a defined html node which contains the main attributes of a html tag, for instance: id, name, class, style and value. Therefore, you can create your own html-tags and improve the ui framework as much as you want.

## Examples
### Web Server
```java
public class AlphaServer extends HTTPServer {
  public AlphaServer() {
      // port, path, servlets-package 
      super(Optional.of("8080"), Optional.of("/r"), Optional.of("web"));
  }
}
```

### Micro-Services
```java
public class BetaServer extends HTTPServer {
    public BetaServer() {
        // port, path, servlets-package 
        super(Optional.of("8088"), Optional.of("/api"), Optional.of("api"));
    }
}
```


### User Interface
```java
public generateHTML() {
   Node html = HTMLNodes.HTML.getNewClass();
   Node head = HTMLNodes.HEAD.getNewClass();
   Node body = HTMLNodes.BODY.getNewClass();
   Node p = HTMLNodes.P.getNewClass();
   p.setValue("Hello World");
   body.withNode(p);
   html.withNode(head).withNode(body);
}
```
