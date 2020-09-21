package com.zx.dao;

import com.zx.model.Order;
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
public class OderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creatOrder(List<Order> oderList){
        Order list = new Order();
        String sql="insert into bs_order(image,itermName,count,totalPrice,userName,bookId,orderTime,price,orderId) values (?,?,?,?,?,?,?,?,?)";
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderDate = simpleDateFormat.format(currentTime);
        String orderId="BS"+orderDate;
        for(int i=0;i<oderList.size();i++){
            list=oderList.get(i);
            int bid=list.getBookId();
            String image=list.getImage();
            String createdate=list.getCreatedate();
            String bookname=list.getItermName();
            int count=list.getCount();
            double price =list.getPrice();
            double total_price=list.getTotalPrice();
            String username=list.getUsername();

            int rows=jdbcTemplate.update(sql,new Object[]{image,bookname,count,total_price,username,bid,createdate,price,orderId});

        }
    }

    public List<Order> searchOderList(String userName){
        String sql="select * from bs_order where userName= ? ";
        List<Order> oderList = jdbcTemplate.query(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int i) throws SQLException {
                Order list = new Order();
                list.setBookId(rs.getInt("bookId"));
                list.setImage(rs.getString("image"));
                list.setCreatedate(rs.getString("orderTime"));
                list.setItermName(rs.getString("itermName"));
                list.setPrice(rs.getDouble("price"));
                list.setCount(rs.getInt("count"));
                list.setTotalPrice(rs.getInt("totalPrice"));
                list.setUsername(rs.getString("userName"));
                list.setOrderId(rs.getString("orderId"));
                return list;
            }
        }, userName);

        return oderList;
    }
}
