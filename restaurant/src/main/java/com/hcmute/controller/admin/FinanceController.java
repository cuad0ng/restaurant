package com.hcmute.controller.admin;

import com.hcmute.dao.InvoiceDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/admin/quan-ly-tai-chinh")
public class FinanceController {
    public InvoiceDao invoiceDao;

    public FinanceController() {
        this.invoiceDao = new InvoiceDao();
    }
    @RequestMapping("/")
    public ModelAndView Finance() {
        ModelAndView mav = new ModelAndView("admin/finance");
        mav.addObject("statistic_product", invoiceDao.statisticProduct());
        mav.addObject("active3", "active");
        return mav;
    }
    @RequestMapping(value = "/doanh-thu-san-pham")
    public ModelAndView Statistic(
            @RequestParam(name = "keyword", required = false) String key,
            @RequestParam(name = "from_date", required = false) Date fromDate,
            @RequestParam(name = "to_date", required = false) Date toDate) {
        ModelAndView mav = new ModelAndView("admin/finance");
        mav.addObject("statistic_product_options", invoiceDao.statisticProductOptions(key, fromDate, toDate));
        mav.addObject("active3", "active");
        return mav;
    }
}
