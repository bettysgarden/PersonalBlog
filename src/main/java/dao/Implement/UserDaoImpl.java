package dao.Implement;

import dao.Interface.UserDao;
import utils.ConnectionFactory;
import entity.User;
import exception.DaoException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    private final ConnectionFactory builder = new ConnectionFactory();
    protected Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    private static final String SELECT_EMAIL
            = "SELECT iduser, name, email FROM user_to_comment WHERE email = ?";
    private static final String SELECT
            = "SELECT iduser, name, email FROM user_to_comment WHERE iduser = ?";

    private static final String SELECT_ALL
            = "SELECT iduser, name, email FROM user_to_comment";

    private static final String INSERT
            = "INSERT INTO user_to_comment (name, email) VALUES (?,?)";

    private static final String DELETE
            = "DELETE FROM user_to_comment WHERE iduser = ? OR email = ?";

    @Override
    public Long addUser(User user) throws DaoException {
        long userId = -1L;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"iduser"})) {
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.executeUpdate();

            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                userId = gk.getLong("iduser");
            }
            DBUtils.Close(pst, gk);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return userId;
    }

    @Override
    public void deleteUser(long userId, String email) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {

            pst.setLong(1, userId);
            pst.setString(2, email);
            pst.executeUpdate();
            DBUtils.Close(pst);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> list;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL)) {
            ResultSet rs = pst.executeQuery();
            list = new ArrayList<>();
            User user;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setUserName(rs.getString(2));
                user.setEmail(rs.getString(3));
                list.add(user);
            }
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public User getUserById(long userId) throws DaoException {
        User user = new User();

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT)) {
            pst.setLong(1, userId);
            ResultSet rs = pst.executeQuery();
            user.setId(rs.getLong(1));
            user.setUserName(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setId(0);
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return user;

    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_EMAIL)) {
            pst.setString(1, email);
            User user = null;
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getLong("idUser"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            DBUtils.Close(pst, rs);
            return user;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }
}
