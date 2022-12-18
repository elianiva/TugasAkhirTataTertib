package my.id.elianiva.usecase;

import my.id.elianiva.core.models.User;
import my.id.elianiva.usecase.exceptions.InvalidCredentialException;
import my.id.elianiva.usecase.exceptions.UserAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;
import my.id.elianiva.usecase.interfaces.IUserRepository;

import java.util.List;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws UserNotFoundException, InvalidCredentialException {
        User user = userRepository.getUserByUsername(username);
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialException();
        }
        return user;
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void addUser(String username, String password) throws UserAlreadyExistsException {
        userRepository.addUser(new User(username, password));
    }

    public void deleteUser(String username) throws UserNotFoundException {
        userRepository.deleteUser(username);
    }

    public void editUser(String username, String newUsername, String newPassword) throws UserNotFoundException {
        User user = userRepository.getUserByUsername(username);
        user.update(newUsername, newPassword);
        userRepository.editUser(username, user);
    }
}
