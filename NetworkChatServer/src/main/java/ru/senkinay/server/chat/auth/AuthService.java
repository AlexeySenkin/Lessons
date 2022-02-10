package ru.senkinay.server.chat.auth;

import java.util.HashSet;
import java.util.Set;
import java.sql.*;


public class AuthService {
//    public static final String PATH_DB = "jdbc:sqlite:/Volumes/Macintosh HD — том D/JAVA/Lessons/Lessons/NetworkChatServer/src/main/java/ru/senkinay/server/chat/auth/networkchat.db";
    public static final String PATH_DB = "jdbc:sqlite:networkchat.db";
    private Set<User> USERS;
    private Connection connection;
    private Statement statement;

    public AuthService() {
        loadUserList();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(PATH_DB);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database");
            e.printStackTrace();
        }
    }

    public void disconnect(){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Failed to disconnect from database");
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to disconnect from database");
                e.printStackTrace();
            }
        }
    }

    public void loadUserList() {
        Set<User> usersDB = new HashSet<>();
        connect();
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users;")) {
            while (resultSet.next()) {
               usersDB.add( new User(resultSet.getString("login").trim(),
                                        resultSet.getString("password").trim(),
                                        resultSet.getString("user_name").trim()));

            }
            this.USERS = usersDB;
            disconnect();
        } catch (SQLException e) {
            System.err.println("Failed to select from database");
            e.printStackTrace();
        }
    }

    public String getUserNameByLoginAndPassword(String login, String password) {
        User requireUser = new User(login,password);
        for (User user : USERS) {
            if (requireUser.equals(user)) {
                return user.getUserName();
            }
        }
        return null;
    }

}
