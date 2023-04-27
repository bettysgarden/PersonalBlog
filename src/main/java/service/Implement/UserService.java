package service.Implement;

import dao.Implement.UserDaoImpl;
import dao.Interface.UserDao;
import entity.User;
import exception.BusinessException;
import exception.DaoException;
import service.Interface.UserServiceInterface;

import java.util.List;
import java.util.logging.Logger;

public class UserService implements UserServiceInterface {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }


    @Override
    public User loadUser(long id) throws BusinessException {
        try {
            return userDao.getUserById(id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }
    @Override
    public List<User> loadUser() throws BusinessException {
        try {
            return userDao.getAllUsers();
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public long addUser(User User) throws BusinessException {
        try {
            return userDao.addUser(User);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }
    @Override
    public void deleteUser(int id, String email) throws BusinessException {
        try {
            userDao.deleteUser(id, email);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public User getUserByEmail(String email) throws BusinessException {
        try {
            return userDao.getUserByEmail(email);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

}
