package edu.karazin.shop.web;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.OrderList;
import edu.karazin.shop.service.BookStoreService;
import edu.karazin.shop.service.BucketService;
import edu.karazin.shop.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private BucketService bucketService;

    @Autowired
    private BookStoreService bookStoreService;

    private edu.karazin.shop.model.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof UserDetails) {
            UserDetails sessionUser = (UserDetails) myUser;
            return userService.getUserByName(sessionUser.getUsername());
        } else return null;
    }

    @GetMapping
    public String getOrderPage(Model model) {

        edu.karazin.shop.model.User currentUser = getUser();
        List<BookList> books = bucketService.getOrderByUserId(currentUser.getId()).getBookLists();
        OrderList order = bucketService.getOrderByUserId(currentUser.getId());
        model.addAttribute("products", books);

        System.out.println(new TotalPrice(order.getTotalPrice()).getPrice());

        model.addAttribute("totalPrice", new TotalPrice(order.getTotalPrice()));

        return "bookstore-order";
    }

    @GetMapping(value = "{id}")
    public String addProduct(@PathVariable(name = "id") Long id){
        edu.karazin.shop.model.User user = getUser();
        bucketService.addBookToOrder(user, bookStoreService.getBookById(id));
        return "redirect:/order";
    }

    @GetMapping(value = "remove/{id}")
    public String removeProductFromBasket(@PathVariable(name = "id") Long id){
        return "redirect:/order";
    }


}
