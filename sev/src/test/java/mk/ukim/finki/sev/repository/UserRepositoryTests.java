package mk.ukim.finki.sev.repository;

import mk.ukim.finki.sev.model.Role;
import mk.ukim.finki.sev.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        User user1 = new User(
                "test1",
                "Test1",
                "Test1",
                "testtest1",
                Role.ROLE_VOTER,
                "test@test.com");

        User user2 = new User(
                "test2",
                "Test2",
                "Test2",
                "testtest2",
                Role.ROLE_VOTER,
                "test@test.com");

        User user3 = new User(
                "test3",
                "Test3",
                "Test3",
                "testtest3",
                Role.ROLE_VOTER,
                "test@test.com");

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);

        List<User> candidates = this.userRepository.findAll();

        Assert.assertEquals(3, candidates.size());
    }

    @Test
    public void testFindById() {
        User user1 = new User(
                "test1",
                "Test1",
                "Test1",
                "testtest1",
                Role.ROLE_VOTER,
                "test@test.com");

        User user2 = new User(
                "test2",
                "Test2",
                "Test2",
                "testtest2",
                Role.ROLE_VOTER,
                "test@test.com");

        User user3 = new User(
                "test3",
                "Test3",
                "Test3",
                "testtest3",
                Role.ROLE_VOTER,
                "test@test.com");

        String user1Id = this.userRepository.save(user1).getUsername();
        this.userRepository.save(user2);
        this.userRepository.save(user3);

        User user1FromDB = this.userRepository.findById(user1Id).get();

        Assert.assertNotNull(user1FromDB);

        Assert.assertEquals(user1.getUsername(), user1FromDB.getUsername());
        Assert.assertEquals(user1.getFirstName(), user1FromDB.getFirstName());
        Assert.assertEquals(user1.getLastName(), user1FromDB.getLastName());
        Assert.assertEquals(user1.getHasVoted(), user1FromDB.getHasVoted());
        Assert.assertEquals(user1.getEmail(), user1FromDB.getEmail());
    }

    @Test
    public void testDelete() {
        User user1 = new User(
                "test1",
                "Test1",
                "Test1",
                "testtest1",
                Role.ROLE_VOTER,
                "test@test.com");

        User user2 = new User(
                "test2",
                "Test2",
                "Test2",
                "testtest2",
                Role.ROLE_VOTER,
                "test@test.com");

        User user3 = new User(
                "test3",
                "Test3",
                "Test3",
                "testtest3",
                Role.ROLE_VOTER,
                "test@test.com");

        String user1Id = this.userRepository.save(user1).getUsername();
        this.userRepository.save(user2);
        this.userRepository.save(user3);

        this.userRepository.deleteById(user1Id);

        List<User> users = this.userRepository.findAll();

        Assert.assertEquals(2, users.size());

    }

    @Test
    public void testDeleteAll() {
        User user1 = new User(
                "test1",
                "Test1",
                "Test1",
                "testtest1",
                Role.ROLE_VOTER,
                "test@test.com");

        User user2 = new User(
                "test2",
                "Test2",
                "Test2",
                "testtest2",
                Role.ROLE_VOTER,
                "test@test.com");

        User user3 = new User(
                "test3",
                "Test3",
                "Test3",
                "testtest3",
                Role.ROLE_VOTER,
                "test@test.com");

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);

        this.userRepository.deleteAll();

        List<User> users = this.userRepository.findAll();

        Assert.assertEquals(0, users.size());

    }

    @Test
    public void testDeleteInBatch() {
        User user1 = new User(
                "test1",
                "Test1",
                "Test1",
                "testtest1",
                Role.ROLE_VOTER,
                "test@test.com");

        User user2 = new User(
                "test2",
                "Test2",
                "Test2",
                "testtest2",
                Role.ROLE_VOTER,
                "test@test.com");

        User user3 = new User(
                "test3",
                "Test3",
                "Test3",
                "testtest3",
                Role.ROLE_VOTER,
                "test@test.com");

        this.userRepository.save(user1);
        User user2DB = this.userRepository.save(user2);
        User user3DB = this.userRepository.save(user3);

        List<User> usersToDelete = new ArrayList<>();

        usersToDelete.add(user2DB);
        usersToDelete.add(user3DB);

        this.userRepository.deleteInBatch(usersToDelete);

        List<User> candidates = this.userRepository.findAll();

        Assert.assertEquals(1, candidates.size());

    }

}
