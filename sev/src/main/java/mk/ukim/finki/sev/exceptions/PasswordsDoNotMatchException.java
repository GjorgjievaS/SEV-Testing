package mk.ukim.finki.sev.exceptions;

public class PasswordsDoNotMatchException extends Exception {
    public PasswordsDoNotMatchException() {
        super("Passwords do not match.");
    }
}
