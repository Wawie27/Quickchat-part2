import java.util.*;
import java.io.*;

public class Message {
    private String messageID;
    private int numMessages;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static List<Message> sentMessagesList = new ArrayList<>();
    private static int totalMessages = 0;

    public Message(int num, String recipient, String text) {
        this.numMessages = num;
        this.recipient = recipient;
        this.messageText = text;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++) sb.append(r.nextInt(10));
        return sb.toString();
    }

  public String checkRecipientCell() {
    if (recipient == null || recipient.length() < 10 || recipient.length() > 13 || !recipient.matches("^\\+27\\d{9}$")) {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    return "Cell phone number successfully captured.";
}  

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z]", "");
        String last = words[words.length-1].replaceAll("[^a-zA-Z]", "");
        return (messageID.substring(0,2) + ":" + numMessages + ":" + first + last).toUpperCase();
    }

    public String checkMessageLength() {
        if(messageText.length() > 250) {
            return "Message exceeds 250 characters by " + (messageText.length()-250) + ", please reduce the size.";
        } else {
            return "Message Ready to send.";
        }
    }

    public String SentMessage(int option) {
        if(option==1) {
            sentMessagesList.add(this);
            totalMessages++;
            storeMessage();
            return "Message successfully sent.";
        } else if(option==2) {
            return "Press 0 to delete message.";
        } else {
            storeMessage();
            return "Message successfully stored.";
        }
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            String json = String.format("{\"id\":\"%s\",\"hash\":\"%s\",\"recipient\":\"%s\",\"message\":\"%s\"},\n",
                    messageID, messageHash, recipient, messageText.replace("\"","'"));
            writer.write(json);
            writer.close();
        } catch(Exception e) {}
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return messageText; }
}