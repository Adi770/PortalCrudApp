package pl.lepa.crudapp.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lepa.crudapp.dao.ResetTokenRepository;
import pl.lepa.crudapp.dao.UserRepository;
import pl.lepa.crudapp.exceptions.TokenExpiredException;
import pl.lepa.crudapp.exceptions.TokenNotFoundException;
import pl.lepa.crudapp.exceptions.UserExistException;
import pl.lepa.crudapp.model.DTO.UserDTO;
import pl.lepa.crudapp.model.ResetToken;
import pl.lepa.crudapp.model.Role;
import pl.lepa.crudapp.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final String USER_DOESN_T_EXIST = "User doesn't exist";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResetTokenRepository resetTokenRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ResetTokenRepository resetTokenRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.resetTokenRepository = resetTokenRepository;
        createBasicUser();
    }

    public void createBasicUser() {

        if (!userRepository.findByUsername("admin").isPresent()) {
            User user = new User();

            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            user.setEmail("admin@admin.pl");

            userRepository.save(user);
        }
    }

    public User currentUser() {
        return userRepository.findByEmail("asd").orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
    }

    public User currentUserToken() {
        return (User) SecurityContextHolder.getContext().getAuthentication();
    }

    public Role currentRole() {
        return currentUser().getRole();
    }

    public List<Role> roleList() {
        return Arrays.asList(Role.values().clone());
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExistException("User with that name or email exist");
        user.setRole(Role.USER);
        return userRepository.save(user);

    }

    public User createUserByAdmin(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExistException("User with that name or email exist");
        return userRepository.save(user);

    }

    public User updateUser(UserDTO updateUser) {

        User user = userRepository.findByUsername(currentUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
        user.setEmail(updateUser.getEmail());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        return userRepository.save(user);

    }

    public User updateUserByAdmin(UserDTO updateUser) {
        User user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(updateUser, user);

        return userRepository.save(user);

    }

    public String createResetToken(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid email"));

        ResetToken resetToken = new ResetToken();

        String token = UUID.randomUUID().toString();
        resetToken.setToken(token);
        resetToken.setTimeToExpired(LocalDateTime.now().plusDays(1));
        resetToken.setUser(user);

        resetTokenRepository.save(resetToken);

        return token;
    }

    public User resetPassword(String token, String newPassword) {
        ResetToken resetToken = resetTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token is Invalid"));

        if (resetToken.getTimeToExpired().isAfter(LocalDateTime.now())) {
            throw new TokenExpiredException("Token expired");
        }
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        resetTokenRepository.delete(resetToken);

        return userRepository.save(user);

    }

}
