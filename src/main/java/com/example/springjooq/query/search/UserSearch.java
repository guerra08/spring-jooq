package com.example.springjooq.query.search;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import java.time.LocalDate;
import java.util.Map;

import static org.jooq.springjooq.db.h2.tables.Users.USERS;

public class UserSearch {

    private enum SearchKeys {
        NAME("name"),
        EMAIL("email"),
        BIRTH_RANGE_START("birthRangeStart"),
        BIRTH_RANGE_END("birthRangeEnd");

        private final String value;

        SearchKeys(String value) {
            this.value = value;
        }
    }

    private UserSearch() {}

    public static Condition buildSearchCondition(Map<String, String> params) {
        var condition = DSL.noCondition();
        if(params.containsKey(SearchKeys.NAME.value)) {
            var name = params.get(SearchKeys.NAME.value);
            condition = condition.and(USERS.NAME.likeIgnoreCase("%" + name + "%"));
        }
        if(params.containsKey(SearchKeys.EMAIL.value)) {
            var email = params.get(SearchKeys.EMAIL.value);
            condition = condition.and(USERS.EMAIL.likeIgnoreCase("%" + email + "%"));
        }
        if(params.containsKey(SearchKeys.BIRTH_RANGE_START.value) && params.containsKey(SearchKeys.BIRTH_RANGE_END.value)) {
            var startDate = LocalDate.parse(params.get(SearchKeys.BIRTH_RANGE_START.value));
            var endDate = LocalDate.parse(params.get(SearchKeys.BIRTH_RANGE_END.value));
            condition = condition.and(USERS.BIRTH_DATE.greaterOrEqual(startDate)).and(USERS.BIRTH_DATE.lessOrEqual(endDate));
        }
        return condition;
    }

}
