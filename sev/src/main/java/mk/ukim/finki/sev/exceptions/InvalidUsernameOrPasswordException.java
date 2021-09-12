package mk.ukim.finki.sev.exceptions;

public class InvalidUsernameOrPasswordException extends Exception {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password.");
    }
}
