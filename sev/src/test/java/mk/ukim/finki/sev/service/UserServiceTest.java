package mk.ukim.finki.sev.service;

import mk.ukim.finki.sev.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.sev.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.sev.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.sev.model.Role;
import mk.ukim.finki.sev.model.User;
import mk.ukim.finki.sev.repository.UserRepository;
import mk.ukim.finki.sev.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "name", "lastName", "password", Role.ROLE_VOTER, "test@test.com");
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        User user = this.service.register(
                "username",
                "password",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");

        Mockito.verify(this.service).register(
                "username",
                "password",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name does not mach", "name", user.getFirstName());
        Assert.assertEquals("role does not mach", Role.ROLE_VOTER, user.getRole());
        Assert.assertEquals("lastName does not mach", "lastName", user.getLastName());
        Assert.assertEquals("password does not mach", "password", user.getPassword());
        Assert.assertEquals("username does not mach", "username", user.getUsername());
    }


    @Test
    public void testNullUsername() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(
                        null,
                        "password",
                        "password",
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                null,
                "password",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }

    @Test
    public void testEmptyUsername() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(
                        username,
                        "password",
                        "password",
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                username,
                "password",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }


    @Test
    public void testEmptyPassword() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(
                        username,
                        password,
                        "password",
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                username,
                password,
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }

    @Test
    public void testNullPassword() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(
                        username,
                        password,
                        "password",
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                username,
                password,
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }


    @Test
    public void testPasswordMismatch() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(
                        username,
                        password,
                        confirmPassword,
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                username,
                password,
                confirmPassword,
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }


    @Test
    public void testDuplicateUsername() throws InvalidUsernameOrPasswordException, UsernameAlreadyExistsException, PasswordsDoNotMatchException {
        User user = new User(
                "username",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(
                        username,
                        "password",
                        "password",
                        "name",
                        "lastName",
                        Role.ROLE_VOTER,
                        "test@test.com"));

        Mockito.verify(this.service).register(
                username,
                "password",
                "password",
                "name",
                "lastName",
                Role.ROLE_VOTER,
                "test@test.com");
    }

}
