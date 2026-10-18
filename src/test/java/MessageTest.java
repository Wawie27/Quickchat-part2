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
}