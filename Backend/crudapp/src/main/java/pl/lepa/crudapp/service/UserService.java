package pl.lepa.crudapp.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lepa.crudapp.dao.ResetTokenRepository;
import pl.lepa.crudapp.dao.UserRepository;
import pl.lepa.crudapp.exceptions.TokenExpiredException;
import pl.lepa.crudapp.exceptions.TokenNotFoundException;
import pl.lepa.crudapp.exceptions.UserExistException;
import pl.lepa.crudapp.model.RecoveryMessage;
import pl.lepa.crudapp.model.dto.UserDTO;
import pl.lepa.crudapp.model.ResetToken;
import pl.lepa.crudapp.model.Role;
import pl.lepa.crudapp.model.User;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final String USER_DOESN_T_EXIST = "User doesn't exist";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ResetTokenRepository resetTokenRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       ResetTokenRepository resetTokenRepository,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.resetTokenRepository = resetTokenRepository;
        this.emailService = emailService;
        createBasicUser();
    }

    public void createBasicUser() {
        if (!userRepository.findByUsername("user").isPresent()) {
            User user = new User();

            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole(Role.USER);
            user.setEmail("user@user.pl");

            userRepository.save(user);
        }
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
        return userRepository.findByUsername(currentUsername()).orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
    }

    public String currentUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Role currentRole() {
        return currentUser().getRole();
    }

    public List<Role> roleList() {
        return Arrays.asList(Role.values().clone());
    }

    public void createUser(UserDTO userDTO) {
        Optional<User> user=userRepository.findByUsernameOrEmail(userDTO.getUsername(),userDTO.getEmail());
        if (user.isPresent())
            throw new UserExistException("User with that name or email exist");
        User newUser=modelMapper.map(userDTO,User.class);
        newUser.setRole(Role.USER);
        userRepository.save(newUser);
    }

    public void createUserByAdmin(User newUser) {
        Optional<User> user=userRepository.findByUsernameOrEmail(newUser.getUsername(),newUser.getEmail());
        if (user.isPresent())
            throw new UserExistException("User with that name or email exist");
        userRepository.save(newUser);

    }

    public User updateUser(UserDTO updateUser) {

        User user = userRepository.findByUsername(currentUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
        user.setEmail(updateUser.getEmail());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        return userRepository.save(user);

    }

    public void changePassword(String newPassword){
        User user=currentUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void changeEmail(String newEmail){
        User user=currentUser();
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public User updateUserByAdmin(UserDTO updateUser) {
        User user = userRepository.findById(updateUser.getId())
                .orElseThrow(() -> new UsernameNotFoundException(USER_DOESN_T_EXIST));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(updateUser, user);

        return userRepository.save(user);

    }

    @Transactional
    public void createResetToken(RecoveryMessage message) {

        User user = userRepository.findByEmail(message.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException("Invalid email"));

        ResetToken resetToken = new ResetToken();

        String token = UUID.randomUUID().toString();
        resetToken.setToken(token);
        resetToken.setTimeToExpired(LocalDateTime.now().plusDays(1));
        resetToken.setUser(user);

        resetTokenRepository.save(resetToken);


        try {
            emailService.sendJavaMail(message,resetToken.getToken());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        ResetToken resetToken = resetTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token is Invalid"));

        if (resetToken.getTimeToExpired().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Token expired");
        }
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        resetTokenRepository.delete(resetToken);

        userRepository.save(user);

    }

}
