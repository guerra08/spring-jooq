package com.example.springjooq.service;

import com.example.springjooq.contract.CreateUserContract;
import com.example.springjooq.exception.InternalOperationException;
import com.example.springjooq.model.User;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.springjooq.query.search.UserSearch.buildSearchCondition;
import static org.jooq.springjooq.db.h2.tables.Users.USERS;

@Service
public class DefaultUserService implements UserService {

    private final DSLContext dsl;

    public DefaultUserService(DSLContext dslContext) {
        this.dsl = dslContext;
    }

    @Override
    public List<User> getAll() {
        return dsl.select().from(USERS).fetch().into(User.class);
    }

    @Override
    public List<User> search(Map<String, String> params) {
        var condition = buildSearchCondition(params);
        return dsl.select().from(USERS).where(condition).fetch().into(User.class);
    }

    @Override
    public User create(CreateUserContract contract) {
        return dsl.insertInto(USERS, USERS.NAME, USERS.EMAIL, USERS.BIRTH_DATE)
            .values(contract.name(), contract.email(), contract.birthDate())
            .returningResult(USERS.ID)
            .fetchOptional()
            .map(record -> new User(record.get(USERS.ID), contract.name(), contract.email(), contract.birthDate()))
            .orElseThrow(InternalOperationException::new);
    }
}
