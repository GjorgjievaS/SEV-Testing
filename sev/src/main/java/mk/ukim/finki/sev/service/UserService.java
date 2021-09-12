package mk.ukim.finki.sev.service;

import mk.ukim.finki.sev.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.sev.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.sev.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.sev.model.Role;
import mk.ukim.finki.sev.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String embg, String password, String repeatPassword, String firstName, String lastName, Role role, String email) throws InvalidUsernameOrPasswordException, PasswordsDoNotMatchException, UsernameAlreadyExistsException;
}
