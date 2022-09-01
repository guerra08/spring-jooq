package com.example.springjooq.unit;

import com.example.springjooq.query.search.UserSearch;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSearchTests {

    @Test
    void buildSearchCondition_shouldReturnEmptyCondition() {

        Map<String, String> params = Map.of();

        var result = UserSearch.buildSearchCondition(params);

        assertThat(result).isEqualTo(DSL.noCondition());

    }

    @Test
    void buildSearchCondition_shouldReturnPopulatedCondition() {

        var params = Map.of(
            "name", "Bruno"
        );

        var result = UserSearch.buildSearchCondition(params);

        assertThat(result).isNotEqualTo(DSL.noCondition());

    }

}
