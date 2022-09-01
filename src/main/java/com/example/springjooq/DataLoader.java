package com.example.springjooq;

import org.jooq.DSLContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.jooq.springjooq.db.h2.tables.Users.USERS;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(DSLContext dsl) {
        return args ->
            dsl.insertInto(USERS, USERS.NAME, USERS.EMAIL, USERS.BIRTH_DATE)
                .values("Bruno Guerra", "guerra@email.com", LocalDate.of(2000, 1, 8))
                .returning()
                .fetch();
    }

}
