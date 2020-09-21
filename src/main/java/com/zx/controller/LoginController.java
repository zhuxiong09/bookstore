package com.zx.controller;

import com.zx.dao.*;
import com.zx.model.Books;
import com.zx.model.Order;
import com.zx.model.ShoppingCar;
import com.zx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BooksDao booksDao;

    @Autowired
    private ItermDao itermDao;

    @Autowired
    private OderDao oderDao;

    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Autowired
    private RegisterDao registerDao;

    //.......................登陆界面...........................
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    //............................获取首页............................
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginIn(String userName, String passWord, HttpSession httpSession) {
        System.out.printf("Usernam:" + userName + ", Password: " + passWord);
        httpSession.setAttribute("userName",userName);
        ModelAndView modelAndView;

        user.setUserName(userName);
        user.setPassword(passWord);

        boolean result = userDao.login(user);
        if (result) {
            //获取图书列表
            int page = 1;
            String keywords = "";
            int pageSize = 3;
            int maxPage = 0;
            maxPage = booksDao.getMaxPage(keywords, pageSize);

            if (page > maxPage) {
                page = maxPage;
            }
            if (page < 1) {
                page = 1;
            }


            List<Books> bookList = booksDao.findBookList(keywords, page, pageSize);
            Map<String, Object> model = new HashMap<>();
            model.put("bookList", bookList);
            model.put("b_maxPage", maxPage);
            model.put("b_page", page);
            model.put("keywords", "");
            modelAndView = new ModelAndView("index", model);
            return modelAndView;
        } else {
            //System.out.println("<script>alert(‘用户名或密码有误，登录失败！跳转回登录界面.’)");
            modelAndView = new ModelAndView("login");
            return modelAndView;
        }
    }

    //............................获取首页.................................
    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView;
        int page = 1;
        String keywords = "";
        int pageSize = 3;
        int maxPage = 0;
        maxPage = booksDao.getMaxPage(keywords, pageSize);
        if (page > pageSize) {
            page = pageSize;
        }
        if (page < 1) {
            page = 1;
        }
        List<Books> bookList = booksDao.findBookList(keywords, page, pageSize);

        Map<String, Object> model = new HashMap<>();
        model.put("bookList", bookList);
        model.put("b_maxPage", maxPage);
        model.put("b_page", page);
        model.put("keywords", "");
        modelAndView = new ModelAndView("index", model);
        return modelAndView;
    }

    //。。。。。。。。。。。。。。。.注册界面。。。。。。。。。。。。。。。。
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }


    //..............................进行注册...........................
    @RequestMapping(value = "/register", method =RequestMethod.POST)
    public ModelAndView loginIn(String userName, String passWord,String rePassWord, String email) {

        ModelAndView modelAndView;

        System.out.printf("Usernam:" + userName + ", Password: " + passWord+", email: " + email);

        User user1=new User();
        user1.setUserName(userName);
        user1.setPassword(passWord);
        user1.setEmail(email);

        boolean rrs = registerDao.testUserName(user1);
        if(rrs){
            boolean rs = registerDao.addUser(user1);
            if(rs){
                modelAndView = new ModelAndView("register_result");
                return modelAndView;


            }else {
                modelAndView = new ModelAndView("register_result2");
                return modelAndView;
            }
        }else {
            modelAndView = new ModelAndView("register_result1");
            return modelAndView;
        }
    }



    //...................................查找与翻页....................................
    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView search(@RequestParam(value = "b_page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords) {

        ModelAndView modelAndView;
        int pageSize = 3;
        int maxPage = 0;
        maxPage = booksDao.getMaxPage(keywords, pageSize);
        if (page > maxPage) {
            page = maxPage;
        }
        if (page < 1) {
            page = 1;
        }
        List<Books> bookList = booksDao.findBookList(keywords, page, pageSize);

        Map<String, Object> model = new HashMap<>();
        model.put("bookList", bookList);
        model.put("b_maxPage", maxPage);
        model.put("b_page", page);
        model.put("keywords", "");
        modelAndView = new ModelAndView("index", model);
        return modelAndView;
    }

    //.................................加入购物车.........................................
    @RequestMapping(value = "/shopping", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView putInShoppingCar(@RequestParam(value = "bookId",required = false) int[] bookIds,
                                         @RequestParam(value = "price",required = false) Double price,HttpSession httpSession) {

        //DB
        List<ShoppingCar> shoppingCarList = new ArrayList<>();

        String username =(String) httpSession.getAttribute("userName");
        if (bookIds != null) {
            for (int bookId : bookIds) {
                ShoppingCar shoppingCar=new ShoppingCar();
                shoppingCar.setUserName(username);
                shoppingCar.setPrice(price);

                Date currentTime = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String orderDate = simpleDateFormat.format(currentTime);

                shoppingCar.setCreatDate(orderDate);
                shoppingCar.setCount(1);
                shoppingCar.setBookId(bookId);

                shoppingCarList.add(shoppingCar);
            }
            shoppingCarDao.creatShoppingCar(shoppingCarList);
        }

        List<ShoppingCar> searchResult = shoppingCarDao.searchShoppingCar(username);
        List<Books> shoppingList = new ArrayList<Books>();
        HashMap<Integer, Integer> shoppingMap = new HashMap<Integer, Integer>();
        for(int i=0;i<searchResult.size();i++){

            ShoppingCar car = searchResult.get(i);
            Books book = booksDao.findBooks(car.getBookId());
            shoppingList.add(book);
            shoppingMap.put(car.getBookId(), car.getCount());
        }

        httpSession.setAttribute("shoppingList",shoppingList);
        httpSession.setAttribute("shoppingMap",shoppingMap);

        // view
        ModelAndView modelAndView;

        Map<String, Object> model = new HashMap<>();
        model.put("shoppingList", shoppingList);
        model.put("shoppingMap", shoppingMap);
        modelAndView = new ModelAndView("shopping", model);
        return modelAndView;
    }

     //..........................修改订单数量................................
    @RequestMapping(value = "/updateShoppingCar", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateShoppingCar(@RequestParam(value = "bookId") int bookId,
                                          @RequestParam(value = "curNums") int num,HttpSession httpSession) {

        System.out.println("bookId = " + bookId + ", nums = " + num );
        String username =(String) httpSession.getAttribute("userName");
        shoppingCarDao.updateShoppingCar(bookId,num,username);

        List<ShoppingCar> searchResult = shoppingCarDao.searchShoppingCar(username);
        List<Books> shoppingList = new ArrayList<Books>();
        HashMap<Integer, Integer> shoppingMap = new HashMap<Integer, Integer>();
        for(int i=0;i<searchResult.size();i++){

            ShoppingCar car = searchResult.get(i);
            Books book = booksDao.findBooks(car.getBookId());
            shoppingList.add(book);
            shoppingMap.put(car.getBookId(), car.getCount());
        }
        // view
        ModelAndView modelAndView;

        httpSession.setAttribute("shoppingList",shoppingList);
        httpSession.setAttribute("shoppingMap",shoppingMap);

        Map<String, Object> model = new HashMap<>();
        model.put("shoppingList", shoppingList);
        model.put("shoppingMap", shoppingMap);
        modelAndView = new ModelAndView("shopping", model);
        return modelAndView;

    }

    //.............................删除订单................................
    @RequestMapping(value = "/deleteShoppingCar", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteShoppingCar(@RequestParam(value = "bookId") int bookId,
                                         HttpSession httpSession) {

        String username =(String) httpSession.getAttribute("userName");
        shoppingCarDao.deleteShoppingCar(bookId,username);

        List<ShoppingCar> searchResult = shoppingCarDao.searchShoppingCar(username);
        List<Books> shoppingList = new ArrayList<Books>();
        HashMap<Integer, Integer> shoppingMap = new HashMap<Integer, Integer>();
        for(int i=0;i<searchResult.size();i++){

            ShoppingCar car = searchResult.get(i);
            Books book = booksDao.findBooks(car.getBookId());
            shoppingList.add(book);
            shoppingMap.put(car.getBookId(), car.getCount());
        }
        // view
        ModelAndView modelAndView;

        httpSession.setAttribute("shoppingList",shoppingList);
        httpSession.setAttribute("shoppingMap",shoppingMap);

        Map<String, Object> model = new HashMap<>();
        model.put("shoppingList", shoppingList);
        model.put("shoppingMap", shoppingMap);
        modelAndView = new ModelAndView("shopping", model);
        return modelAndView;

    }
//..............................加入订单列表...................................................
    @RequestMapping(value = "/shopping-result", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView shoppingResult(HttpSession httpSession) {

        ModelAndView modelAndView;
        List<Books> shoppingList = (List<Books>)httpSession.getAttribute("shoppingList");
        HashMap<Integer, Integer> shoppingMap = (HashMap<Integer, Integer>)httpSession.getAttribute("shoppingMap");

        String userName= user.getUserName();
        List<Order> oderList = new ArrayList<Order>();
        for (Books book : shoppingList) {
            Order list = new Order();
            list.setBookId(book.getBid());
            list.setImage(book.getImage());
            list.setItermName(book.getBookname());
            list.setUsername(userName);
            list.setCount(shoppingMap.get(book.getBid()));
            list.setPrice(book.getPrice());
            list.setTotalPrice(shoppingMap.get(book.getBid()) * book.getPrice());
            Date currentTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            list.setCreatedate(simpleDateFormat.format(currentTime));

            oderList.add(list);
        }
        oderDao.creatOrder(oderList);


        modelAndView = new ModelAndView("shopping-result");
        return modelAndView;

    }


    //。。。。。。。。。。。。。。。。。。返回订单列表。。。。。。。。。。。。。。。。。。。。
    @RequestMapping(value = "/orderlist", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView oderList() {
        ModelAndView modelAndView;

        String userName= user.getUserName();
        List<Order> shoppingOderList = oderDao.searchOderList(userName);

        Map<String, Object> model = new HashMap<>();
        model.put("shoppingOderList", shoppingOderList);

        modelAndView = new ModelAndView("orderlist",model);
        return modelAndView;
    }


    //...............................注销功能........................................
    @RequestMapping(value = "/Cancellation", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView Cancellation(HttpSession httpSession) {
        ModelAndView modelAndView;

        httpSession.getAttribute("userName");
        httpSession.removeAttribute("userName");

        modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}
