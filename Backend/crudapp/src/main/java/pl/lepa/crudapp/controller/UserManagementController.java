package pl.lepa.crudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lepa.crudapp.model.DTO.UserDTO;
import pl.lepa.crudapp.model.Role;
import pl.lepa.crudapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AccountManagement/")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        userService.currentUser();
        return "test";
    }

    @GetMapping("/AllRole")
    public List<Role> getAllRole() {
        return userService.roleList();
    }

    @GetMapping("/role")
    public Role currentRole() {
        return userService.currentRole();
    }

    @PutMapping("/role")
    public void editRole(@RequestBody UserDTO userDTO) {


        userService.updateUser(userDTO);
    }



}
