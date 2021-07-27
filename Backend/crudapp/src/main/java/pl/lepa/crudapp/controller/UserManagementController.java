package pl.lepa.crudapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lepa.crudapp.model.DTO.UserDTO;
import pl.lepa.crudapp.model.Role;
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


    @GetMapping("/test")
    public String test(){
        userService.currentUser();
        return "test";
    }

    @ApiOperation(value="This method return all role in application")
    @GetMapping("/AllRole")
    public List<Role> getAllRole() {
        return userService.roleList();
    }

    @ApiOperation(value = "Return current role user")
    @GetMapping("/role")
    public Role currentRole() {
        return userService.currentRole();
    }

    @ApiOperation(value = "Change current role user")
    @PutMapping("/role")
    public void editRole(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
    }



}
