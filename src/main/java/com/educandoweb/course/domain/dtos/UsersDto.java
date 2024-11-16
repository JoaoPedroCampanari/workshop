package com.educandoweb.course.domain.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @Column(length = 100)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String password;

}
