package pl.lepa.crudapp.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lepa.crudapp.dao.UserRepository;
import pl.lepa.crudapp.model.User;
import pl.lepa.crudapp.model.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return new UserPrincipal(user);

    }


}
