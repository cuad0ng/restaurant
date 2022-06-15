package com.hcmute.controller.user;

import com.hcmute.dao.CommentDao;
import com.hcmute.dao.ProductDao;
import com.hcmute.model.CommentModel;
import com.hcmute.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @RequestMapping(value = {"/menu"}, method = RequestMethod.GET)
    public String getMenu(HttpServletRequest req){
        ProductDao dao = new ProductDao();
        List<ProductModel> list = dao.getAllProduct();
        req.setAttribute("listProducts", list);
        req.setAttribute("type", 1);
        return "menu";
    }

    @RequestMapping(value = {"/view-product/{id}"}, method = RequestMethod.GET)
    public String getProduct(HttpServletRequest req, @PathVariable int id){
        ProductDao dao = new ProductDao();
        CommentDao commentDao = new CommentDao();
        ProductModel productModel = dao.getProductById(id);
        List<CommentModel> listComment = commentDao.getALlCommentById(id);
        req.setAttribute("type", 2);
        req.setAttribute("product", productModel);
        req.setAttribute("comments", listComment);
        return "menu";
    }
}
