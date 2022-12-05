package nos.bzastrow;

import java.net.MalformedURLException;
import java.net.URL;

import com.timeserver.TimeServer;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;


/** An XML-RPC client to demonstrate the RPC procedures of a Time-Server. */
class TimeServerClient {
	/** The main-function of the client, attempting to connect to the server, creating a local proxy object and printing sample time-data. */
    public static void main(String args[]) {
        try {
            // The URL of the XML-RPC server
            String url = "http://127.0.0.1:8020/xmlrpc";
            if (args.length > 0 )
                url = args[0];

            // Create an XML-RPC client
            XmlRpcClient client = new XmlRpcClient();
            // Create config for client with URL to XML-RPC server, and set the config
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(url));
            config.setEnabledForExtensions(true);
            client.setConfig(config);

            // Create a factory that can create XML-RPC proxy objects for given
            // interfaces, like Time-Server.
            ClientFactory factory = new ClientFactory(client);
            // Create a Time-Server proxy object, which again implements the
            // Time-Server interface.
            TimeServer ts = (TimeServer) factory.newInstance(TimeServer.class);

            // Use the time-server proxy-object to output the nice-to-read gregorian calendar output.
            System.out.println("Gregorian time: " + ts.currentTimeGregorianPretty());
            System.out.println("System time: " + ts.currentTimeSystemMillis());
            
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
