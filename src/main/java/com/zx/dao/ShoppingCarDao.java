package com.zx.dao;

import com.zx.model.Order;
import com.zx.model.ShoppingCar;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ShoppingCarDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creatShoppingCar(List<ShoppingCar> shoppingList){
        ShoppingCar shoppingCar = new ShoppingCar();

        String sql="insert into bs_shopping_car(book_id,createdate,count,price,username) values (?,?,?,?,?)";
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = simpleDateFormat.format(currentTime);

        for(int i=0;i<shoppingList.size();i++){
            shoppingCar=shoppingList.get(i);

            int bookId=shoppingCar.getBookId();
            String createdate=shoppingCar.getCreatDate();
            int count=shoppingCar.getCount();
            double price =shoppingCar.getPrice();
            String userName=shoppingCar.getUserName();

            int rows=jdbcTemplate.update(sql,new Object[]{bookId,createdate,count,price,userName});

        }
    }

    public List<ShoppingCar> searchShoppingCar(String userName){
        String sql="select * from bs_shopping_car where username= ? ";
        List<ShoppingCar> shoppingList = jdbcTemplate.query(sql, new RowMapper<ShoppingCar>() {
            @Override
            public ShoppingCar mapRow(ResultSet rs, int i) throws SQLException {
                ShoppingCar shoppingCar = new ShoppingCar();

                shoppingCar.setBookId(rs.getInt("book_id"));
                shoppingCar.setCount(rs.getInt("count"));
                shoppingCar.setCreatDate(rs.getString("createdate"));
                shoppingCar.setPrice(rs.getDouble("price"));
                shoppingCar.setUserName(rs.getString("username"));

                return shoppingCar;
            }
        }, userName);

        return shoppingList;
    }


    public void updateShoppingCar(int bookId,int num,String username){

        String sql="update bs_shopping_car set count= ? where book_id= ? and username= ?";

        int rows=jdbcTemplate.update(sql,new Object[]{num,bookId,username});
    }


    public void deleteShoppingCar(int bookId,String username){

        String sql="delete from bs_shopping_car where book_id= ? and username= ?";

        int rows=jdbcTemplate.update(sql,new Object[]{bookId,username});
    }

}
