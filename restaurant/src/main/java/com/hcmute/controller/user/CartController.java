package com.hcmute.controller.user;

import com.hcmute.dao.CartDao;
import com.hcmute.dao.ProductDao;
import com.hcmute.model.AccountModel;
import com.hcmute.model.CartModel;
import com.hcmute.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    public CartDao cartDao = new CartDao();
    public ProductDao productDao = new ProductDao();


    private int existProduct(int product_id, List<CartModel> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductId() == product_id) {
                return i;
            }

        }
        return -1;
    }

    @RequestMapping("/add/{product_id}")
    public ModelAndView Add(HttpSession session, @PathVariable int product_id) {
        ModelAndView mav = new ModelAndView("redirect:/view-product/" + product_id);
        AccountModel account = (AccountModel) session.getAttribute("account");
        if (account == null){
            return new ModelAndView("redirect:/login");
        }
        int customer_id = account.getCustomer_id();
        ProductModel product = productDao.getProductById(product_id);
        CartModel itemCart = new CartModel();
        itemCart.setProductId(product.getId());
        itemCart.setProductAmount(1);
        itemCart.setCusId(customer_id);
        itemCart.setImg(product.getImg());
        itemCart.setProduct_name(product.getName());
        itemCart.setProduct_price(product.getPrice());
        itemCart.setState(0);
        if (cartDao.existCart(customer_id))
        {
            List<CartModel> cart = cartDao.selectCart(customer_id);
            int i = existProduct(product_id, cart);
            if (i != -1) {
                cart.get(i).setProductAmount(cart.get(i).getProductAmount() + 1);
                cartDao.updateCart(cart.get(i));
            } else {
                int cart_id = cartDao.getIdCart(customer_id);
                itemCart.setId(cart_id);
                cartDao.insertCart(itemCart);
            }
        }
        else{
            cartDao.createCart(itemCart);
            int cart_id = cartDao.getIdCart(customer_id);
            itemCart.setId(cart_id);
            cartDao.insertCart(itemCart);
        }
        int totalAmount = cartDao.totalAmount(customer_id);
        session.setAttribute("totalAmount", totalAmount);
        return mav;
    }

    @RequestMapping("/view/{customer_id}")
    public ModelAndView Index(HttpSession session, @PathVariable int customer_id) {
        ModelAndView mav = new ModelAndView("cart");
        mav.addObject("cart", cartDao.selectCart(customer_id));
        session.setAttribute("totalPrice", cartDao.totalPrice(customer_id));
        return mav;
    }
    @RequestMapping("/edit/{product_id}/{product_amount}")
    public ModelAndView Edit(HttpSession session, @PathVariable int product_id, @PathVariable int product_amount) {

        AccountModel account = (AccountModel) session.getAttribute("account");
        int customer_id = account.getCustomer_id();
        if (product_amount < 1){
            return new ModelAndView("redirect:/cart/view/" + customer_id);
        }
        ModelAndView mav = new ModelAndView("redirect:/cart/view/" + customer_id);
        CartModel itemCart = new CartModel();
        itemCart.setId(cartDao.getIdCart(customer_id));
        itemCart.setProductId(product_id);
        itemCart.setProductAmount(product_amount);
        cartDao.updateCart(itemCart);

        session.setAttribute("totalAmount", cartDao.totalAmount(customer_id));
        session.setAttribute("totalPrice", cartDao.totalPrice(customer_id));
        return mav;
    }

    @RequestMapping("/delete/{product_id}")
    public ModelAndView Delete(HttpSession session, @PathVariable int product_id) {
        AccountModel account = (AccountModel) session.getAttribute("account");
        int customer_id = account.getCustomer_id();
        ModelAndView mav = new ModelAndView("redirect:/cart/view/" + customer_id);
        int cart_id = cartDao.getIdCart(customer_id);
        cartDao.deleteCart(cart_id, product_id);

        session.setAttribute("totalAmount", cartDao.totalAmount(customer_id));

        session.setAttribute("totalPrice", cartDao.totalPrice(customer_id));

        return mav;
    }
}
