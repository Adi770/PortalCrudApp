package pl.lepa.crudapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lepa.crudapp.model.dto.UserAdminDTO;
import pl.lepa.crudapp.model.dto.UserDTO;
import pl.lepa.crudapp.model.user.RecoveryMessage;
import pl.lepa.crudapp.model.user.Role;
import pl.lepa.crudapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AccountManagement/")
@Api(tags = "User Management")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "This method register new user")
    @PostMapping("user/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser() {

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "This method creates users with additonal options available only for Admin")
    @PostMapping("admin/register")
    public ResponseEntity<String> registerNewUserByAdmin(@RequestBody UserAdminDTO user) {
        userService.createUserByAdmin(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "This method return all users role in application")
    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return userService.roleList();
    }

    @ApiOperation(value = "This method return current user role")
    @GetMapping("/role")
    public Role currentRole() {
        return userService.currentRole();
    }

    @ApiOperation(value = "This method sending password reset link")
    @PostMapping("/password")
    public ResponseEntity<String> resetPassword(@RequestBody RecoveryMessage recoveryMessage) {
        userService.createResetToken(recoveryMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "This method change password with reset token")
    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@RequestParam("token") String token, @RequestBody String password) {
        userService.resetPassword(token, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
