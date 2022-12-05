package nos.bzastrow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Application class representing a multi-threaded socket-based message-server
 *  The server stores messages sent by connecting clients and offers additional "/erase" and "/receive" commands.
 */
public class Server {
    
    /** The main-function of the server waiting for incoming connections and delegating new connections to separate threads.
     */
    public static void main(String[] args) {
        // The ID-counter that is being used to assign unique IDs to new connecting clients.
        int nextClientID = 0;
        // The synchronized managed array-list that every thread has access to, handling messages.
        MessageStore messages = new MessageStore();

        try(ServerSocket serv = new ServerSocket(2424)) {
            // Main-Loop of the application waiting for connections (blocking) and creating threads
            while(true) {
                Socket s = serv.accept();
                Thread t = new Thread(new SocketWorker(s, ++nextClientID, messages));
                t.start();
            }
        } catch(IOException e) {
            // Printing an error-message in case of a connection error.
            System.err.println("Connection Error: " + e.getMessage());
        }
    }
}
