package nos.bzastrow;

/**
 * Class representing a simple message consisting of a string and a sender-id
 */
public class Message {
    // SH: not javadoc
    // The text-message represented by the message
    private String text;
    // The id of the sender of the text-message
    private int senderID;
    
    // Constructor assigning values to the two fields
    public Message(String text, int senderID) {
        this.text = text;
        this.senderID = senderID;
    }

    /** Method to return the text contained in the message.
     * @return String text-message
     */
    public String getText() {
        return text;
    }

    /** Method to return the id of the sender of the text.
     * @return int id of the sender
     */
    public int getSenderID() {
        return senderID;
    }
}
