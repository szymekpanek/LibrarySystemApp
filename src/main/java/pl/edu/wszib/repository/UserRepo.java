package pl.edu.wszib.repository;

import pl.edu.wszib.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    private final Map<String, User> users = new HashMap<>();

    public UserRepo() {
        this.users.put("admin",
                new User("admin", "d6a8541297bdddb570515a3d6960f50a", "ADMIN")); //admin123
        this.users.put("szymon",
                new User("szymon", "3fdc53825f7231312f4c3c1c58220893", "USER")); //szymon123
    }

    public User getByLogin(String login) {
        return this.users.get(login);
    }

    public User getRole(String role) {
        return this.users.get(role);
    }

}
