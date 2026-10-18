public class Login {
    private String username;
    private String password;
    private String cell;

    public boolean checkUserName(String u) {
        return u.contains("_") && u.length() <= 5;
    }
    public boolean checkPasswordComplexity(String p) {
        return p.length()>=8 && p.matches(".*[A-Z].*") && p.matches(".*[0-9].*") && p.matches(".*[^a-zA-Z0-9].*");
    }
    public boolean checkCellPhoneNumber(String c) {
        return c.matches("^\\+27\\d{9}$");
    }
    public String registerUser(String u, String p, String c) {
        if(!checkUserName(u)) return "Username is not correctly formatted";
        if(!checkPasswordComplexity(p)) return "Password is not correctly formatted";
        if(!checkCellPhoneNumber(c)) return "Cell phone number is incorrectly formatted";
        this.username=u; this.password=p; this.cell=c;
        return "User registered successfully";
    }
    public boolean loginUser(String u, String p) {
        return u.equals(username) && p.equals(password);
    }
    public String returnLoginStatus(boolean ok) {
        if(ok) return "Welcome " + username + " it is great to see you again.";
        else return "Username or password incorrect, please try again.";
    }
}