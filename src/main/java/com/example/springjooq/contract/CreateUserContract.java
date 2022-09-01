package com.example.springjooq.contract;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserContract(
    @NotEmpty @Length(min = 1, max = 255) String name,
    @NotEmpty @Email String email,
    @NotNull LocalDate birthDate
) {

}
