package com.zx.dao;

import com.zx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RegisterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean testUserName(User user){
        String sql = "select * from bs_user where username = ?";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user2=new User();
                user2.setEmail(rs.getString("email"));
                user2.setPassword(rs.getString("password"));
                user2.setUserName(rs.getString("username"));
                return user2;
            }

        },user.getUserName());

        if(userList.size() == 0){
            return true;
        }
        return false;
    }

    public boolean addUser(User user){
        String sql="insert into bs_user (username,password,email) values (?,?,?)";
        int rows=jdbcTemplate.update(sql,new Object[]{user.getUserName(),user.getPassword(),user.getEmail()});
        if(rows>0){
            return true;
        }
        return false;
    }
}
