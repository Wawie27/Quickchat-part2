import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testValidRecipient() {
        Message m = new Message(1, "+27718693002", "Hi Mike");
        assertTrue(m.checkRecipientCell().contains("captured"));
    }
    @Test
    public void testInvalidRecipient() {
        Message m = new Message(1, "08575975889", "Hi Mike");
        assertTrue(m.checkRecipientCell().contains("incorrectly"));
    }
    @Test
    public void testLongMessageFails() {
        String longMsg = "a".repeat(300);
        Message m = new Message(1, "+27718693002", longMsg);
        assertTrue(m.checkMessageLength().contains("exceeds"));
    }
    @Test
    public void testShortMessagePasses() {
        Message m = new Message(1, "+27718693002", "Hi Keegan, did you receive the payment?");
        assertTrue(m.checkMessageLength().contains("Ready"));
    }
    @Test
    public void testMessageIDLength() {
        Message m = new Message(1, "+27718693002", "Test");
        assertEquals(10, m.getMessageID().length());
    }
    @Test
    public void testMessageHashFormat() {
        Message m = new Message(1, "+27718693002", "Hi Mike");
        String hash = m.getMessageHash();
        assertTrue(hash.contains(":1:"));
    }
    @Test
    public void testSentMessageSend() {
        Message m = new Message(1, "+27718693002", "Hi");
        assertEquals("Message successfully sent.", m.SentMessage(1));
    }
    @Test
    public void testSentMessageDisregard() {
        Message m = new Message(1, "+27718693002", "Hi");
        assertEquals("Press 0 to delete message.", m.SentMessage(2));
    }
    @Test
    public void testSentMessageStored() {
        Message m = new Message(1, "+27718693002", "Hi");
        assertEquals("Message successfully stored.", m.SentMessage(3));
    }
 @Test
    void testSearchRecipient() {
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you?"));
    }
        @Test
    void testSearchMessageID() {
        String result = Message.searchByMessageID("0838884567");
        assertTrue(result.contains("0838884567") || result.contains("Recipient"));
    } 
    @Test
    void testLongestMessage() {
        Message m = new Message(1, "+27718693002", "Hi");
        m.SentMessage(3);
        String result = Message.displayLongestStoredMessage();
        assertNotNull(result);
    }
    @Test
    void testDisplaySenderAndRecipient() {
        Message.populateTestData();
        String result = Message.displaySenderAndRecipient();
        assertNotNull(result);
        assertTrue(result.contains("Developer") || result.contains("Recipient") || result.contains("083"));
    }
    @Test
    void testPopulateTestData() {
        Message.populateTestData();
        String result = Message.displaySenderAndRecipient();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }
    @Test
    void testLongestAfterPopulate() {
        Message.populateTestData();
        String result = Message.displayLongestStoredMessage();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }  
}