package pl.edu.wszib.authorization;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.model.User;
import pl.edu.wszib.repository.UserRepo;


public class Authenticator {

    private final UserRepo userRepository = new UserRepo();

    private final String SEED = "u4a{X~&b#W3hbJ6;z3?27UT-H2GHl";
    public static User loggedUser = null;

    public void authenticate(User user) {
        User userFromDB = userRepository.getByLogin(user.getLogin());
        if(userFromDB != null &&
                userFromDB.getPassword().equals(DigestUtils.md5Hex(user.getPassword()+SEED))) {
            loggedUser = userFromDB;
        }
    }



    public boolean isAdmin(Runnable adminOperation) {
        if (loggedUser != null && "ADMIN".equals(loggedUser.getRole())) {
            adminOperation.run();
            return true;
        } else {
            System.out.println("You don't have permission to perform this operation.");
            return false;
        }
    }

}
