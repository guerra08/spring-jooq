package com.example.springjooq.controller;

import com.example.springjooq.contract.CreateUserContract;
import com.example.springjooq.model.User;
import com.example.springjooq.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        var users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserContract contract) throws URISyntaxException {
        var user = userService.create(contract);
        return ResponseEntity.created(new URI("/user/" + user.getId())).body(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> search(@RequestParam Map<String, String> params) {
        var result = userService.search(params);
        return ResponseEntity.ok(result);
    }

}
