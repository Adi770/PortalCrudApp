package pl.lepa.crudapp.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lepa.crudapp.dao.UserRepository;
import pl.lepa.crudapp.exceptions.UserExist;
import pl.lepa.crudapp.model.DTO.UserDTO;
import pl.lepa.crudapp.model.Role;
import pl.lepa.crudapp.model.User;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User currentUser() {
        return userRepository.findByEmail("asd").orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }

    public User currentUserToken() {

        return (User) SecurityContextHolder.getContext().getAuthentication();
        //return ;
    }

    public Role currentRole(Role role) {
        return currentUser().getRole();
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExist("User with that name or email exist");
        user.setRole(Role.USER);
        return userRepository.save(user);

    }

    public User createUserByAdmin(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExist("User with that name or email exist");
        return userRepository.save(user);

    }

    public User updateUser(UserDTO updateUser) {

        User user = userRepository.findByUsername(currentUser().getUsername()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        user.setEmail(updateUser.getEmail());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        return userRepository.save(user);

    }

    public User updateUserByAdmin(UserDTO updateUser) {
        userRepository.findById(updateUser.getId()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        User user = modelMapper.map(updateUser, User.class);
        return userRepository.save(user);

    }

    public User setRole(Role role) {
        User user = currentUser();
        user.setRole(role);
        return userRepository.save(user);

    }


    public void resetPassword() {


    }

}
