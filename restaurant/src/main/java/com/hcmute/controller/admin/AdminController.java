package com.hcmute.controller.admin;

import com.hcmute.dao.InvoiceDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    public InvoiceDao invoiceDao = new InvoiceDao();
    @RequestMapping({ "/admin"})
    public ModelAndView Home() {
        ModelAndView mav = new ModelAndView("admin/home");
        mav.addObject("invoices_part", invoiceDao.selectPartInvoice());
        mav.addObject("total_sale_today", invoiceDao.getTotalSaleToday());
        mav.addObject("total_sale_all", invoiceDao.getTotalSaleAll());
        mav.addObject("statistic_product", invoiceDao.statisticProduct());
        mav.addObject("active1", "active");
        return mav;
    }
}
