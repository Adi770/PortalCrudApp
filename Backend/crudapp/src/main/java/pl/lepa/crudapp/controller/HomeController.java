package pl.lepa.crudapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lepa.crudapp.model.User;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/test")
    public String test(){

        return "test";
    }

}
