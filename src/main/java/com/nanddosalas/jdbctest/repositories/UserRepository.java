package com.nanddosalas.jdbctest.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nanddosalas.jdbctest.models.User;
import com.nanddosalas.jdbctest.repositories.mappers.UserMapper;

import lombok.Data;

@Repository
@Data
public class UserRepository {

    private UserMapper mapper;
    private JdbcTemplate template;
    // private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(UserMapper mapper, JdbcTemplate template) {
        this.mapper = mapper;
        this.template = template;
    }

    public List<User> findAll() {
        String sql = "select * from users;";

        List<User> users = template.query(sql, mapper);

        return users;
    }

    public User findById(int userId) {
        // alternative method
        // String sql = "select * from users where id = :id;";
        // SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", userId);
        // return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, mapper);
        String sql = "select * from users where id = ?;";

        return template.queryForObject(sql, mapper, userId);
    }

    public int count() {
        String sql = "select count(*) from users;";

        return template.queryForObject(sql, Integer.class);
    }

    public int insert(User user) {
        String sql =
                "insert into users (full_name, email, avatar, about, server_role, encrypted_password) values (?, ?, ?, ?, ?, ?);";

        return template.update(sql, user.getFullName(), user.getEmail(), user.getAvatar(),
                user.getAbout(), user.getRole(), user.getPassword());
    }

    public int delete(int userId) {
        String sql = "delete from users where id = ?;";

        return template.update(sql, 5);
    }

    public int update(User user) {
        String sql =
                "update users set full_name = ?, email = ?, avatar = ?, about = ?, server_role = ?, encrypted_password = ? where id = ?;";

        return template.update(sql, user.getFullName(), user.getEmail(), user.getAvatar(),
                user.getAbout(), user.getRole(), user.getPassword(), user.getId());
    }
}
