package com.zx.dao;

import com.zx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean login(User userInfo) {
        String sql = "select * from bs_user where username=? and password=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{userInfo.getUserName(), userInfo.getPassword()}, new BeanPropertyRowMapper<User>(User.class));
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
