package my.id.elianiva.usecase.interfaces;

import my.id.elianiva.core.models.User;
import my.id.elianiva.usecase.exceptions.UserAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserRepository {
    User getUserByUsername(String username) throws UserNotFoundException;

    List<User> getAllUsers();

    void addUser(User user) throws UserAlreadyExistsException;

    void editUser(String username, User newUser) throws UserNotFoundException;

    void deleteUser(String username) throws UserNotFoundException;
}
