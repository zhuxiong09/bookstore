package com.zx.jdbcTest;

import com.zx.config.LoginConfig;
import com.zx.dao.*;
import com.zx.model.Books;
import com.zx.model.Order;
import com.zx.model.ShoppingCar;
import com.zx.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LoginConfig.class})
@WebAppConfiguration
public class JdbcTest {

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private User user;

    @Autowired
    private ItermDao itermDao;

    @Autowired
    private OderDao oderDao;

    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Autowired
    private RegisterDao registerDao;

    @Test
    public void testShoppingCarDao(){
        List<ShoppingCar> shoppingList = new ArrayList<>();
        ShoppingCar shoppingCar1=new ShoppingCar();
        shoppingCar1.setUserName("zx");
        shoppingCar1.setPrice(10);

        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = simpleDateFormat.format(currentTime);
        shoppingCar1.setCreatDate(orderDate);
        shoppingCar1.setCount(1);
        shoppingCar1.setBookId(2);

        shoppingList.add(shoppingCar1);
        shoppingCarDao.creatShoppingCar(shoppingList);

    }

    @Test
    public void testShoppingCarDao1(){
        List<ShoppingCar> shoppingList = shoppingCarDao.searchShoppingCar("zx");
        System.out.println(shoppingList.size());
    }

    @Test
    public void testShoppingCarDao2(){
         shoppingCarDao.updateShoppingCar(2,2,"zhu");

    }

    @Test
    public void testShoppingCarDao3(){
        shoppingCarDao.deleteShoppingCar(3,"zhu");

    }

    @Test
    public void testregisterDao(){
        User user1=new User();
        user1.setUserName("zhu");
        user1.setPassword("11111");
        user1.setEmail("11222");
        boolean t= registerDao.testUserName(user1);
        System.out.println(t);
    }
    @Test
    public void testregisterDao1(){
        User user1=new User();
        user1.setUserName("zhu11");
        user1.setPassword("11111");
        user1.setEmail("11222");
        registerDao.addUser(user1);

    }

    @Test
    public void test(){
        user.setUserName("zhu");
        user.setPassword("12345");
        userDao.login(user);
        Assert.assertEquals(true, userDao.login(user));
    }

    @Test
    public void testOderDao(){
        //image,itermName,count,totalPrice,userName,bookId,orderTime,price,orderId)
        List<Order> oderList = new ArrayList<>();
        Order order = new Order();
        order.setImage("123");
        order.setBookId(1);
        order.setItermName("zhu");
        order.setCount(2);
        order.setTotalPrice(22);
        order.setUsername("zzzz");
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = simpleDateFormat.format(currentTime);
        order.setCreatedate(date);
        order.setPrice(11);
        oderList.add(order);
        oderDao.creatOrder(oderList);
    }

    @Test
    public void testOderDao1(){
        List<Order> oderList = oderDao.searchOderList("zzzz");
        System.out.println(oderList.size());
    }



    @Test
    public void testFibdBookList(){
        List<Books> result =  booksDao.findBookList("天",1,3);
        System.out.println(result.size());
    }

    @Test
    public void testGetMaxPag(){
        int maxPage = booksDao.getMaxPage("",3);
        System.out.println(maxPage);

    }

    @Test
    public void testFindBooks(){
        Books book = booksDao.findBooks(2);
        System.out.println(book.getBookname());
        System.out.println(booksDao.findBooks(2));
    }


    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            int i ,j ,k;
            List list = new ArrayList();
            for(i=0;i<nums.length;i++){
                for(j=i+1;j<nums.length;j++){
                    for(k=j+1;k<nums.length;k++){
                        if(nums[i]+nums[j]+nums[k] == 0){
                            if(nums[i] <= nums[j]){
                                int a;
                                a=nums[i];
                                nums[i]=nums[j];
                                nums[j]=a;
                            };
                            if(nums[i] <= nums[k]){
                                int a;
                                a=nums[i];
                                nums[i]=nums[k];
                                nums[k]=a;
                            }
                            if(nums[j] <= nums[k]){
                                int a;
                                a=nums[j];
                                nums[j]=nums[k];
                                nums[k]=a;
                            }
                            int[] n= new int[]{nums[i],nums[j],nums[k]};
                            if(list.contains(n) == false){
                                list.add(n);
                            }
                        }
                    }
                }
            }
            return list;
        }
    }




    public int lengthOfLongestSubstring(String s) {
        char[] a =s.toCharArray();
        List<Character> list = new ArrayList<>();
        int b = 0;
        if(a.length == 0){
            return 0;
        } else {
            int mun = 1;
            for (int i = 0;i<a.length;i++){
                for(int j = i;j<a.length;j++){
                    b++;
                    if(list.contains(a[j]) == false){
                        list.add(a[j]);
                    }else {
                        if(list.size() >= mun){
                            mun = list.size();
                        }
                        list.clear();
                        break;
                    }
                }

            }
            System.out.println(b);
            return mun;
        }

    }

    @Test
    public void testLengthOfLongestSubstring(){
       int a = lengthOfLongestSubstring("12314562228");
        System.out.println(a);
    }

}
