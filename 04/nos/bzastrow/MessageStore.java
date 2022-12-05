package nos.bzastrow;

import java.util.ArrayList;

/**
 * A class offering a managed and synchronised ArrayList containing messages, that is to be accessed by multiple threads.
 */
public class MessageStore {
    // The ArrayList containing the messages
    private ArrayList<Message> contents = new ArrayList<Message>();

    
    /** Method to safely remove all messages in store that have been sent by a given id.
     * @param id the id of which to remove all sent messages
     */
    public synchronized void erase(int id) {
        for(int i = 0; i < contents.size(); ++i) {
            if(contents.get(i).getSenderID() == id) {
                System.out.println("Removed " + contents.get(i).getText());
                contents.remove(i--);
            }
        }
    }
    
    
    /** Method to safely access all messages sent by everyone but the given id
     * @param id the id of the client that is supposed to be filtered out
     * @return String concatinated one-line string containing the returned messages and IDs.
     */
    public synchronized String receive(int id) {
        String retval = new String();
        for(int i = 0; i < contents.size(); ++i) {
            if(contents.get(i).getSenderID() != id) {
                retval = retval.concat(contents.get(i).getSenderID() + ":" + contents.get(i).getText() + " >> ");
            }
        }
        if(retval.isBlank()) {
            retval = "Server> No messages from other clients stored!";
        }
        return retval;
    }

    
    /** Method to safely push-back one message into the managed ArrayList
     * @param msg the message object to be added to the ArrayList
     */
    public synchronized void pushMsg(Message msg) {
        contents.add(msg);
    }
}
