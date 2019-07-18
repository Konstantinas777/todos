package lt.bit.todos.security;

import java.util.List;
import lt.bit.todos.dao.UsersDAO;
import lt.bit.todos.db.Users;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetails implements UserDetailsService {

    private final static Log log = LogFactory.getLog(MyUserDetails.class);

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Users> users = usersDAO.findByName(username);
        if(users.size() > 0) {
            return users.get(0);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
