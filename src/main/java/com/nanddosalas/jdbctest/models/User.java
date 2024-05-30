package com.nanddosalas.jdbctest.models;

import lombok.Data;

@Data
public class User {

    private int id;
    private String fullName;
    private String email;
    private String avatar;
    private String about;
    private String role;
    private String joinedDate;
    private String password;
    private String googleId;
    private String githubId;

}
