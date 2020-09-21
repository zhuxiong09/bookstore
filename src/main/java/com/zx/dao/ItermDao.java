package com.zx.dao;

import com.zx.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItermDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creatIterms(List<Items> itemsList){
        Items item = new Items();
        String sql = "insert into bs_items values (default,?,?,?,?,?,?);";
        for(int i=0;i<itemsList.size();i++){
            item=itemsList.get(i);
            int oid=item.getOid();
            int bid=item.getBid();
            String creatdate=item.getCreatedate();
            int count =item.getCount();
            double price=item.getPrice();
            double total_price = item.getTotal_price();

            int rows=jdbcTemplate.update(sql,new Object[]{oid,bid,creatdate,count,price,bid,total_price});
        }
    }
}
