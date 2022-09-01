package com.example.springjooq.service;

import com.example.springjooq.contract.CreateUserContract;
import com.example.springjooq.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAll();
    List<User> search(Map<String, String> params);
    @Transactional
    User create(CreateUserContract contract);

}
