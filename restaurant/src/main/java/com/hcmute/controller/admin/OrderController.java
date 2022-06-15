package com.hcmute.controller.admin;

import com.hcmute.dao.InvoiceDao;
import com.hcmute.model.InvoiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/admin/quan-ly-don-hang")
public class OrderController {
    public InvoiceDao invoiceDao;

    public OrderController() {
        this.invoiceDao = new InvoiceDao();
    }
    @RequestMapping("/")
    public ModelAndView Invoice() {
        ModelAndView mav = new ModelAndView("admin/order");
        mav.addObject("invoices", invoiceDao.selectAllInvoice());
        mav.addObject("active2", "active");
        return mav;
    }
    @RequestMapping(value = "/view")
    public ModelAndView Retrieve(@RequestParam(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/order-edit");
        mav.addObject("invoice", invoiceDao.selectInvoiceByID(id));
        mav.addObject("active2", "active");
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String Delete(@PathVariable int id) {
        invoiceDao.deleteInvoice(id);
        return "redirect:/admin/quan-ly-don-hang/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String Update(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "cust_id") int cust_id,
            @RequestParam(name = "emp_id") int emp_id,
            @RequestParam(name = "cart_id") int cart_id,
            @RequestParam(name = "total_cost") int total_cost,
            @RequestParam(name = "create_date") Date create_date) {
        InvoiceModel invoice = new InvoiceModel(id, cust_id, emp_id, cart_id, total_cost, create_date);
        invoiceDao.updateInvoice(invoice);
        return "redirect:/admin/quan-ly-don-hang/";
    }
}
