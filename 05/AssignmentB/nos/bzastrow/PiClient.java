package nos.bzastrow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.mcpi.MonteCarloPi;
import com.mcpi.Random;

/** An RMI client demo of a remote PiClient object that uses the MonteCarlo method to compute pi. */
class PiClient {
    /** The main-function connecting to the rmiregistry, obtaining the remote objects and invoking their methods with different arguments */
    public static void main(String args[]) {
        // Host is either default on TCP port localhost:1099 or given by
        // args[0].
        String host = null;
        if (args.length >= 1)
            host = args[0];

        try {
            // Connect to the rmiregistry and look up the monteCarloPi
            Registry registry = LocateRegistry.getRegistry(host);
            MonteCarloPi stub = (MonteCarloPi) registry.lookup("monteCarloPiRemote");
            Random randStub = (Random) registry.lookup("randomRemote");

            // Some test calls on the remote random implementation
            System.out.println("Random number between 0 and 1: " + randStub.uniform());
            System.out.println("Random number between 0 and 1: " + randStub.uniform());
            System.out.println("Random number between 0 and 1: " + randStub.uniform());
            System.out.println();

            // Some calls on the PiGenerator
            System.out.println("Pi is " + stub.compute(10) + " with N = 10");
            System.out.println("Pi is " + stub.compute(100) + " with N = 100");
            System.out.println("Pi is " + stub.compute(1000) + " with N = 1000");
            System.out.println("Pi is " + stub.compute(20000) + " with N = 20000");
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
