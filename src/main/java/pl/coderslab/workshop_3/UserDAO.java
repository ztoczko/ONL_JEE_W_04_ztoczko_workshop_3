package pl.coderslab.workshop_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
    private static final String CREATE = "INSERT INTO users VALUES (null, ?, ?, ?);";
    private static final String READ_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String READ_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_ALL_MATCHING = "SELECT * FROM users WHERE email LIKE CONCAT('%', ?, '%') OR username LIKE CONCAT('%', ?, '%')";
    private static final String FIND_ALL_OFFSET = "SELECT * FROM users LIMIT ?, ?";
    private static final String FIND_ALL_MATCHING_OFFSET = "SELECT * FROM users WHERE email LIKE CONCAT('%', ?, '%') OR username LIKE CONCAT('%', ?, '%') LIMIT ?, ?";
    private static final String COUNT = "SELECT COUNT(*) FROM users";
    private static final String COUNT_MATCHING = "SELECT COUNT(*) FROM users WHERE email LIKE CONCAT('%', ?, '%') OR username LIKE CONCAT('%', ?, '%')";
    private static final String READ_ADMIN = "SELECT * FROM admins WHERE username = ?";
    private static final String UPDATE_ADMIN = "UPDATE admins SET email = ?, username = ?, password = ? WHERE username = ?";
    private static DBUtil dBUtil = new DBUtil("mysql://remotemysql.com:3306", "TraDU2ybRx", "q9FIzRp9bp", "TraDU2ybRx");


    public static void create(User user) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            } else {
                user.setId(-1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            user.setId(-1);
        }
    }

    public static User read(int id) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(READ_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User read(String email) {

        User user = null;

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(READ_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                rs.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User readAdmin(String name) {

        User user = null;

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(READ_ADMIN)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(-1, rs.getString(1), rs.getString(2), rs.getString(3));
                rs.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateAdmin(User user) {
        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ADMIN)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(User user) {
        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> findAll() {

        List<User> userList = new ArrayList<>();

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll(String search) {

        List<User> userList = new ArrayList<>();

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_MATCHING)) {

            ps.setString(1, search);
            ps.setString(2, search);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll(int offset, int limit) {

        List<User> userList = new ArrayList<>();

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_OFFSET)) {

            ps.setInt(1, offset);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll(String search, int offset, int limit) {

        List<User> userList = new ArrayList<>();

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_MATCHING_OFFSET)) {

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setInt(3, offset);
            ps.setInt(4, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            rs.close();
//            System.out.println(userList);
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCount() {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(COUNT);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getCount(String search) {

        try (Connection conn = dBUtil.connect();
             PreparedStatement ps = conn.prepareStatement(COUNT_MATCHING)) {
            ps.setString(1, search);
            ps.setString(2, search);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
