import java.util.*;

class UserManager {
    private Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password, String fullName, String email, boolean isAdmin) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password, fullName, email, isAdmin));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}
