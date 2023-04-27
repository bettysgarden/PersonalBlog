package service.Interface;

import entity.User;
import exception.BusinessException;
import exception.DaoException;

import java.util.List;

public interface UserServiceInterface {
    User loadUser(long id) throws BusinessException;

    List<User> loadUser() throws BusinessException;

    long addUser(User User) throws BusinessException;

    void deleteUser(int id, String email) throws BusinessException;
    User getUserByEmail(String email) throws BusinessException;
}
