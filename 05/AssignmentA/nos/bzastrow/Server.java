package nos.bzastrow;

import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.*;


/** An XML-RPC server that provides a Time-Server. */
public class Server {
    /** The XML-RPC webserver's TCP port. */
    private static final int port = 8020;

	/** The main-function creating the server, applying the configuration and waiting for incoming connections. */
    public static void main(String[] args) throws Exception {
        // Create webserver and obtain its XML-RPC server.
        WebServer webServer = new WebServer(port);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);

        // Load property file that contains the mapping of interface to
        // implementation.
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        Thread th = Thread.currentThread();
        phm.load(th.getContextClassLoader(), "TimeServer.properties");
        xmlRpcServer.setHandlerMapping(phm);

        webServer.start();
    }
}
