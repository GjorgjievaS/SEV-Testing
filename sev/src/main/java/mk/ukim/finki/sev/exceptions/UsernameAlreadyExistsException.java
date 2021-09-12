package mk.ukim.finki.sev.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super("Username: " + username + " already exists.");
    }
}
