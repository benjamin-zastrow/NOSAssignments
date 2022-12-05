package nos.bzastrow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.mcpi.MonteCarloPi;
import com.mcpi.MonteCarloPiImpl;
import com.mcpi.Random;
import com.mcpi.RandomImpl;

/** RMI Server to provide Pi using the MonteCarlo method.  */
public class Server {
    /** The main method of the server application opening both the monteCarloPi-interface, as well as the internally used random-interface to the network. */
    public static void main(String args[]) {
        try {
            // Instantiate the remote objects.
            Random obj1 = new RandomImpl();
            MonteCarloPi obj2 = new MonteCarloPiImpl();
            // Create stubs from it. The second argument is the port. If zero
            // then an anonymous (ephemeral) port is chosen. To ease firewall
            // config we can choose a specific port.
            Random randStub = (Random) UnicastRemoteObject.exportObject(obj1, 21000);
            MonteCarloPi piStub = (MonteCarloPi) UnicastRemoteObject.exportObject(obj2, 21001);

            // Get the registry and register the stubs
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("randomRemote", randStub);
            registry.bind("monteCarloPiRemote", piStub);

            System.out.println("Server running");
        }
        catch (Exception e) {
            System.out.println("Exception " + e.toString());
            e.printStackTrace();
        }
    }
}
