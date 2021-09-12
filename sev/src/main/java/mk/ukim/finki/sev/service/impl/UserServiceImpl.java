package mk.ukim.finki.sev.service.impl;

import mk.ukim.finki.sev.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.sev.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.sev.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.sev.model.Role;
import mk.ukim.finki.sev.model.User;
import mk.ukim.finki.sev.repository.UserRepository;
import mk.ukim.finki.sev.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String embg, String password, String repeatPassword, String firstName, String lastName, Role role, String email) throws InvalidUsernameOrPasswordException, PasswordsDoNotMatchException, UsernameAlreadyExistsException {
        if (embg==null || embg.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(embg).isPresent())
            throw new UsernameAlreadyExistsException(embg);
        User user = new User(embg,passwordEncoder.encode(password),firstName,lastName,role, email);
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new RuntimeException());
    }
}
