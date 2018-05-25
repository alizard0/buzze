# Buzze a minimalist java web framework

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc egestas suscipit tempus. Curabitur blandit eu ligula eu ultrices. Vestibulum suscipit tellus sed mi porta porttitor. Cras justo magna, faucibus porttitor quam non, commodo luctus elit. Maecenas arcu eros, facilisis non mauris sagittis, gravida sagittis nisi. Vestibulum consectetur facilisis condimentum. Nunc dignissim gravida justo, eget molestie dui tincidunt nec. Suspendisse vehicula urna nec eros faucibus, eu vulputate sem dignissim. Etiam pretium purus eget ligula facilisis, eu tincidunt ante malesuada. Ut id egestas erat. Sed pulvinar, lorem at fringilla fermentum, massa arcu ultrices sapien, fermentum vestibulum orci risus sit amet justo. Aenean bibendum laoreet neque, quis fermentum eros elementum at.

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
