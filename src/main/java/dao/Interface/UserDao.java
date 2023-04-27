package dao.Interface;

//import db.ConnectionBuilder;
//import db.ConnectionBuilderFactory;
import entity.User;
import exception.DaoException;

import java.util.List;

public interface UserDao {

    Long addUser(User user) throws DaoException;

    void deleteUser(long userId, String email) throws DaoException;

    List<User> getAllUsers() throws DaoException;

    User getUserById(long userId) throws DaoException;

    User getUserByEmail(String email) throws DaoException;
}
