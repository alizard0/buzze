# Buzze
> a minimalist java web framework

Buzze provides a easy way to implement web-servers or microservices in java using tomcat as a servlet-container. In order to create a server, you only have to extend the *HTTPServer* class and, when you call the parent constructor, you can pass the desired http-port, the path and the package name where your servlet classes are.

Technically, the HTTPServer will automatically discover your servlet classes and register them in the tomcat embedded server and then deploy everything in a new thread. Therefore, if you create 5 classes which extend the HTTPServer, you will end up with 5 instances of tomcat running in 5 different threads. If you stop the master execution, the threads are automatically killed. 

In terms of writting a micro-service, it is pretty much the same. You only need to provide a different servlet package name as the HTTPServer only manages and runs 
. So the business rules that you write inside of these classes are not analysed by the HTTPServer.

Finally, the micro UI framework is basically a defined html node which contains the main attributes of a html tag, for instance: id, name, class, style and value. Therefore, you can create your own html-tags and improve the ui framework as much as you want.

The major advantages are:
* Be able to use the low-level java servlet object as is
* WebApp or WebService is running in a dedicated tomcat
* Don't have to setup any settings.xml file to add more servlets
* Engine auto discovers the servlets that you created and assign them into the specific tomcat-server
* The UI offers a easy way to create html pages without writting any dodgy template language
* It's easy to test the UI components as they are represented in a tree and you have access to the string output of each element


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

### Simple Token Authentication Mechanism
```java
public class GammaServer extends HTTPServer {
    public BetaServer() {
        // port, path, servlets-package 
        super(Optional.of("9999"), Optional.of("/s/api"), Optional.of("apis"));
    }
}

@WebServlet(name = "SecureApiServlet", urlPatterns = {"/s/api"})
public class SecureApiServlet extends HttpServlet {

    public SecureApiServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Datasource ds = new YourDatasourceImpl();
        SimpleTokenAuth auth = new SimpleTokenAuth(ds);
        String token = req.getHeader("X-AUTH-TOKEN");
        if(aux.isTokenValid(token)){
            IndexPage page = new IndexPage();
            page.setNavbar(yourNavbar);
            page.setBody(yourBody);
            return page.getHTML();
        } else {
            ErrorPage page = new ErrorPage();
            return page.getHTML();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Datasource ds = new YourDatasourceImpl();
        SimpleTokenAuth auth = new SimpleTokenAuth(ds);
        // get email and password from req
        String token = aux.login(email, password);
        if(token != null){
            IndexPage page = new IndexPage();
            page.setNavbar(yourNavbar);
            page.setBody(yourBody);
            return page.getHTML();
        } else {
            ErrorPage page = new ErrorPage();
            return page.getHTML();
        }
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

public Node getNavbar(){
    NavbarFactory factory = new NavbarFactory("MyFirstWebsite", 
    new Pair<>("Home", "#"), new Pair<>("About", "/about"), new Pair<>("Contacts", "/contacts"));
    return factory.getNavbar();
}

public Node panel(){
    PanelFactory factory = new PanelFactory(50,50);
    return factory.getPanel();
}
```

### Index Servlet/Page
```java
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

    public IndexServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IndexPage page = new IndexPage();
        page.setNavbar(yourNavbar);
        page.setBody(yourBody);
        return page.getHTML();
    }
}

public class IndexPage{
    private Node html;
    
    public IndexPage(){
        this.html = Bootstrap.html.getNewClass();
    }
    
    public void setNavbar(Node navbar){
        html.withNode(navbar);
    }
    
    public void setBody(Node body){
        html.withNode(body);
    }
    
    public String getHTML(){
        return html.generateHTML();
    }
}
```
