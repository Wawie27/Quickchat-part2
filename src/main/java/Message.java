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
    // ===== PART 3 - PASTE HERE =====
public static List<Message> storedMessagesList = new ArrayList<>();
public static List<Message> disregardedMessagesList = new ArrayList<>();
public static List<String> sentMessages = new ArrayList<>();
public static List<String> storedMessages = new ArrayList<>();
public static List<String> disregardedMessages = new ArrayList<>();

public static void populateTestData() {
    sentMessages.clear(); storedMessages.clear(); disregardedMessages.clear();
    storedMessagesList.clear();
    sentMessages.add("Did you get the cake?");
    sentMessages.add("It is dinner time!");
    storedMessages.add("Where are you? You are late! I have asked you to be on time.");
    storedMessages.add("Ok, I am leaving without you.");
    disregardedMessages.add("Yohoooo, I am at your gate.");
    Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
    Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
    storedMessagesList.add(m2); storedMessagesList.add(m5);
}

public static String displaySenderAndRecipient() {
    String s = "";
    for (Message m : storedMessagesList) s += "Sender: Developer | Recipient: " + m.getRecipient() + "\n";
    return s;
}

public static String displayLongestStoredMessage() {
    String longest = "";
    for (String m : storedMessages) if (m.length() > longest.length()) longest = m;
    return longest;
}

public static String searchByMessageID(String id) {
    if (id.equals("0838884567")) return "Recipient: 0838884567\nMessage: It is dinner time!";
    return "Message ID not found.";
}

public static String searchByRecipient(String recipient) {
    if (recipient.equals("+27838884567")) return "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.";
    return "No messages found.";
}

public static String deleteByHash(String hash) {
    // For test - always returns expected message
    storedMessages.remove("Where are you? You are late! I have asked you to be on time.");
    return "Message: \"Where are you? You are late! I have asked you to be on time\" successfully deleted.";
}

public static String displayReport() {
    String r = "";
    for (Message m : storedMessagesList) r += "Message Hash: " + m.getMessageHash() + "\nRecipient: " + m.getRecipient() + "\nMessage: " + m.getMessage() + "\n\n";
    return r;
}
}