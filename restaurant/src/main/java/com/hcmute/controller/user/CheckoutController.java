package com.hcmute.controller.user;

import com.hcmute.dao.CartDao;
import com.hcmute.dao.InvoiceDao;
import com.hcmute.model.AccountModel;
import com.hcmute.model.CartModel;
import com.hcmute.model.InvoiceModel;
//import com.hcmute.utils.InvoicePDFGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CheckoutController {
    public CartDao cartDao = new CartDao();
    public InvoiceDao invoiceDao = new InvoiceDao();

    @RequestMapping(value = "/checkout/{userID}")
    public ModelAndView index(HttpSession session, @PathVariable int userID) {
        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("cart", cartDao.selectCart(userID));
        session.setAttribute("totalPrice", cartDao.totalPrice(userID));
        return mav;
    }

    @RequestMapping(value = "checkout-confirmed/{userID}")
    public String confirm(HttpSession session, @PathVariable int userID) {
        AccountModel account = (AccountModel) session.getAttribute("account");

        List<CartModel> cart = cartDao.selectCart(userID);
        int cartID = cartDao.getIdCart(userID);
        double totalCost = cartDao.totalPrice(userID);
        Date currentDate = new Date();

        InvoiceModel invoice = new InvoiceModel();
        invoice.setCusId(userID);
        invoice.setCartId(cartID);
        invoice.setTotalCost(totalCost);
        invoice.setCreateDate(currentDate);

        invoiceDao.createInvoice(invoice);
       // InvoicePDFGenerator pdfGenerator = new InvoicePDFGenerator(cart, account, invoice);
      //  pdfGenerator.writeData();

        cartDao.updateStatusCart(cartID);
        session.setAttribute("totalAmount", 0);

        return "checkout-success";
    }

}
