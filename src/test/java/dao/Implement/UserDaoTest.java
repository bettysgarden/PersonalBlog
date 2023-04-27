package dao.Implement;

import dao.Interface.UserDao;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {
    @Test
    @BeforeAll
    public static void testAddUser() {
        UserDao dao = new UserDaoImpl();
        User user = new User("beatrix", "qwerty@mail.ru");
        try {
            List<User> before = dao.getAllUsers();
            Long addedUser = dao.addUser(user);
            System.out.println(addedUser);
            List<User> after = dao.getAllUsers();
//            dao.deleteUser(addedUser, "");
            assertEquals(before.size() + 1, after.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUser() {

        UserDao dao = new UserDaoImpl();
        try {
            List<User> allUser = dao.getAllUsers();
            if (allUser.size() > 0) {

                for (User at : allUser) {
                    System.out.println(at.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @AfterAll
    public static void testDeleteUser() throws SQLException, IOException {
        UserDao dao = new UserDaoImpl();
        try {
            User user = new User("beatrix", "asdfghjkl@mail.ru");
            long addedUser = dao.addUser(user);
            List<User> before = dao.getAllUsers();
            dao.deleteUser(addedUser, "");
            List<User> after = dao.getAllUsers();
            assertEquals(before.size() - 1, after.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
