package com.nanddosalas.jdbctest.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.nanddosalas.jdbctest.models.User;
import com.nanddosalas.jdbctest.repositories.mappers.UserMapper;

@Repository
public class UserRepository {

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private UserMapper mapper;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public UserMapper getMapper() {
        return mapper;
    }

    @Autowired
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<User> findAll() {
        String sql = "select * from users;";

        List<User> users = template.query(sql, mapper);

        return users;
    }

    public User findById(int userId) {
        String sql = "select * from users where id = :id;";

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", userId);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, mapper);
    }

    public int count() {
        String sql = "select count(*) from users;";

        return template.queryForObject(sql, Integer.class);
    }

    public int insert(User user) {
        String sql = "insert into users (full_name, email, avatar, about, server_role, encrypted_password) values (?, ?, ?, ?, ?, ?);";

        return template.update(
                sql,
                user.getFullName(),
                user.getEmail(),
                user.getAvatar(),
                user.getAbout(),
                user.getRole(),
                user.getPassword());
    }

    public int delete(int userId) {
        String sql = "delete from users where id = ?;";

        return template.update(sql, 5);
    }
}
