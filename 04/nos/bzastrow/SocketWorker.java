package nos.bzastrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class that handles Server-socket to client-socket communication
 * implements a runnable to be executed independently by separate threads
 */
public class SocketWorker implements Runnable {
    // The socket to be communicated with
    private Socket s;
    // The Client ID of the connected socket
    private int clientID;
    // The Java-equivalent of a "pointer" to the one MessageStore Object created by the Server-Mainthread
    private MessageStore globalMessageStore;

    // Basic constructor initialising the fields.
    public SocketWorker(Socket s, int id, MessageStore ms) {
        this.s = s;
        clientID = id;
        globalMessageStore = ms;
    }

    // Method being invoked by the Thread
    @Override
    public void run() {
        // SH: Do not forget to also close socket s! Since Java 9 you can just add s to the list of resources
        // Try-with-resource covering bidirectional communication with a buffered input and a non-buffered output
        try (PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                // Assigning the waiting client it's clientID before regular communication can begin
                out.println(clientID);
                System.out.println("Server> New Client connected (ID: " + clientID + ")");
                // Thread main-loop
                while(true) {
                    // Waiting (blocking) for new input by the client
                    String input = in.readLine();
                    // Checking for the "/exit" command, terminating the worker thread.
                    if(input.equals("/exit")) {
                        System.out.println("Server> Client ID " + clientID + " wants to abandon connection. Exiting!");
                        break;
                    }
                    // Checking for the "/receive" command, printing a status message and returning the output of the corresponding MessageStore method receive() to the client
                    if(input.equals("/receive")) {
                        System.out.println("Server> Client ID " + clientID + " has issued receive command!");
                        out.println(globalMessageStore.receive(clientID));
                    } 
                    // Checking for the "/erase" command, printing a status message and invoking the corresponding MessageStore method erase(), as well as sending a confirmation message to the client
                    else if(input.equals("/erase")) {
                        System.out.println("Server> Client ID " + clientID + " has issued erase command!");
                        globalMessageStore.erase(clientID);
                        out.println("Server> Done deleting messages!");
                    } 
                    // "regular text input" -> storing message and clientID in a new Message object in the managed MessageStore
                    else {
                        System.out.println(clientID + "> " + input);
                        globalMessageStore.pushMsg(new Message(input, clientID));
                    }
                }
        } catch(IOException e) {
            // Error message in case of I/O-Error
            System.err.println("I/O-Error handling socket, Thread-ID: " + Thread.currentThread().getId() + ", Error: " + e.getMessage());
        }   
    }
}
