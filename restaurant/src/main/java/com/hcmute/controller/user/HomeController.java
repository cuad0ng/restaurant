package com.hcmute.controller.user;

import com.hcmute.dao.ProductDao;
import com.hcmute.model.AccountModel;
import com.hcmute.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomeController {

    @RequestMapping(value= {"/","/trang-chu"})
    public String index(ModelMap model){
        model.addAttribute("message", "Spring MVC XML Config Example");
        return "home";
    }
    @RequestMapping(value = {"/", "/trang-chu"}, method = RequestMethod.GET)
    public String get8New(HttpServletRequest req){
        ProductDao dao = new ProductDao();
        List<ProductModel> list = dao.get8NewestProducts();
        req.setAttribute("list8NewestProducts", list);
        req.setAttribute("type", 1);
        return "home";
    }

}