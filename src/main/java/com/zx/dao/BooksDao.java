package com.zx.dao;

import com.zx.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BooksDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Books> findBookList(String keywords,int page,int pageSize){
        String sql="select * from bs_books where bookname like ? limit ?,?";
        List<Books> booksList = jdbcTemplate.query(sql, new RowMapper<Books>() {
            @Override
            public Books mapRow(ResultSet rs, int i) throws SQLException {
                Books book=new Books();
                book.setBid(rs.getInt("bid"));
                book.setBookname(rs.getString("bookname"));
                book.setPrice(rs.getDouble("b_price"));
                book.setImage(rs.getString("image"));
                book.setStock(rs.getInt("stock"));
                return book;
            }
        }, ("%"+ keywords +"%"),  (page-1)*pageSize, pageSize);

        return booksList;
    }



    public int getMaxPage(String keywords,int pageSize){
        String sql="select ceiling (count(*)*1.0/?) from bs_books where bookname like ?";

            int maxPage = jdbcTemplate.queryForObject(sql,  new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int maxPage) throws SQLException {

                    return rs.getInt(1);
                }
            }, pageSize, ("%"+ keywords +"%"));

        return maxPage;
    }




    public Books findBooks(int bookId){
        String sql="select * from bs_books where bid= ? ";
        final Books book = new Books();
        jdbcTemplate.queryForObject(sql, new Object[]{bookId}, new RowMapper() {

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                book.setBid(rs.getInt("bid"));
                book.setBookname(rs.getString("bookname"));
                book.setStock(rs.getInt("stock"));
                book.setImage(rs.getString("image"));
                book.setPrice(rs.getDouble("b_price"));
                return book;
            }
        });
        return book;
    }
}
