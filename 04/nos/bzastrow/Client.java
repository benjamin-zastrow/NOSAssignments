package nos.bzastrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class representing an executable and independent client-application that interacts with the corresponding server-application
 * The app offers chat functionality, as well as basic commands.
 */
public class Client {
    
    /** 
     * The main-function of the client trying to connect to the server and forwarding user messages to it.
     */
    public static void main(String[] args) {
        // The clientID that is assigned by the server and which is being used to identify message-origins
        int clientID;
        // A temporary input field containing the last user input on stdin
        String input;
        // Try-with-resource covering the client-socket, bidirectional socket communication, as well as console-input streaming.
        try(Socket s = new Socket("localhost", 2424);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))) {
        
            // Waiting for the assignment of a client-id by the server
            System.out.println("Message> Connection established! Waiting for ID");
            clientID = Integer.parseInt(in.readLine());
            System.out.println("Message> ID received. Your ID is " + clientID + ". Ready!");
            // Main-loop
            while(true) {
                // Making console input look a little nicer.
                System.out.print("Client> ");
                // Reading user input
                input = cin.readLine();

                /* Checking if the user input has the form of a command (I decided to go against the instruction and implement a separate 
                 * command syntax since a chat-like application most heavily depends on the unrestricted use of natural words, including erase and receive).
                 * In addition to that, having a definitive command structure like that, one can now implement new server-side commands without having to alter the client.
                 * Otherwise the client would have to have multi-threading or async-io, which I deemed unnecessary in this respect.
                 */
                if(input.charAt(0) == '/') {
                    // Implementation of client-side command "exit" that leaves the application
                    if(input.equals("/exit")) {
                        out.println(input);
                        break;
                    } 
                    // Since it is now already clear that a command has been entered, the system can wait for an answer by the server with blocking
                    else {
                        out.println(input);
                        System.out.println(in.readLine());        
                    }
                } 
                // In case the user input does not have the form of a command, it is only sent to the server without waiting for an answer.
                else {
                    // SH: Why not factor out this line from all three cases
                    // here and above? Makes it easier to read and makes the logic easier
                    out.println(input);
                }
            }
        } catch(IOException e) {
            // Printing an error-message
            System.err.println("Connection Error: " + e.getMessage());
        }
    }
}
