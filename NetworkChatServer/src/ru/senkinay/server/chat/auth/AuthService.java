package ru.senkinay.server.chat.auth;

import java.util.Set;

public class AuthService {

    private static Set<User> USERS = Set.of(
      new User("login1","pass1","username1"),
      new User("login2","pass2","username2"),
      new User("login3","pass3","username3"),
      new User("login4","pass4","username4")
    );



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
