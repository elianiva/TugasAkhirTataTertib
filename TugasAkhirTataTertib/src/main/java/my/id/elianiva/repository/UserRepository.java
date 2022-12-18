package my.id.elianiva.repository;

import my.id.elianiva.core.models.User;
import my.id.elianiva.usecase.exceptions.UserAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;
import my.id.elianiva.usecase.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<User>();

    public UserRepository() {
        users.add(new User("bernapas", "password"));
        users.add(new User("manusia", "asdf"));
        users.add(new User("something", "bruh"));
        users.add(new User("admin", "admin123"));
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().sorted(Comparator.comparing(User::getUsername)).toList();
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        String newUsername = user.getUsername();
        User existingUser = users.stream().filter(u -> u.getUsername().equals(newUsername)).findFirst().orElse(null);
        if (existingUser != null) {
            throw new UserAlreadyExistsException(newUsername);
        }
        users.add(user);
    }

    @Override
    public void editUser(String username, User newUser) throws UserNotFoundException {
        User existingUser = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException(username);
        }
        int oldUserIndex = users.indexOf(existingUser);
        users.set(oldUserIndex, newUser);
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException {
        User existingUser = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException(username);
        }
        users.remove(existingUser);
    }
}
