import javax.swing.*;

public class QuickChat {
    static int totalSent = 0;
    public static void main(String[] args) {
        Login login = new Login();
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        String regUser = JOptionPane.showInputDialog("Register - Enter username:");
        String regPass = JOptionPane.showInputDialog("Register - Enter password:");
        String regCell = JOptionPane.showInputDialog("Register - Enter cell (+27...):");
        String regResult = login.registerUser(regUser, regPass, regCell);
        JOptionPane.showMessageDialog(null, regResult);
        String loginUser = JOptionPane.showInputDialog("Login - Enter username:");
        String loginPass = JOptionPane.showInputDialog("Login - Enter password:");
        boolean loggedIn = login.loginUser(loginUser, loginPass);
        JOptionPane.showMessageDialog(null, login.returnLoginStatus(loggedIn));
        if(!loggedIn) return;
        while(true) {
            String choice = JOptionPane.showInputDialog("Menu:\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            if(choice==null || choice.equals("3")) {
                JOptionPane.showMessageDialog(null, "Total messages sent: " + totalSent);
                break;
            }
            if(choice.equals("1")) {
                String numStr = JOptionPane.showInputDialog("How many messages you want to send?");
                int num = Integer.parseInt(numStr);
                for(int i=1;i<=num;i++) {
                    String recipient = JOptionPane.showInputDialog("Message " + i + " - Enter recipient cell:");
                    String text = JOptionPane.showInputDialog("Message " + i + " - Enter message (max 250):");
                    Message msg = new Message(i, recipient, text);
                    String cellCheck = msg.checkRecipientCell();
                    JOptionPane.showMessageDialog(null, cellCheck);
                    if(cellCheck.contains("incorrectly")) { i--; continue; }
                    String lenCheck = msg.checkMessageLength();
                    JOptionPane.showMessageDialog(null, lenCheck);
                    if(lenCheck.contains("exceeds")) { i--; continue; }
                    JOptionPane.showMessageDialog(null, "ID: " + msg.getMessageID() + "\nHash: " + msg.getMessageHash() + "\nRecipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessage());
                    String optStr = JOptionPane.showInputDialog("1) Send\n2) Disregard\n3) Store");
                    int opt = Integer.parseInt(optStr);
                    String sentResult = msg.SentMessage(opt);
                    if(sentResult.equals("Message successfully sent.")) totalSent++;
                    JOptionPane.showMessageDialog(null, sentResult);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Coming Soon");
            }
        }
    }
}