package com.hcmute.controller.user;

import com.hcmute.dao.BookingTableDao;
import com.hcmute.dao.CustomerDao;
import com.hcmute.dao.TableDao;
import com.hcmute.model.AccountModel;
import com.hcmute.model.BookingTableModel;
import com.hcmute.model.CustomerModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class BookTableController {
    BookingTableDao bookingTableDao = new BookingTableDao();
    TableDao tableDao = new TableDao();
    CustomerDao customerDao = new CustomerDao();
    @RequestMapping("/book-table")
    public ModelAndView Index(HttpSession session) {
        AccountModel account = (AccountModel) session.getAttribute("account");
        if (account == null) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("book-table");
        mav.addObject("tables",tableDao.getListTable("SELECT * FROM dining_table WHERE status = 'active'"));
        return mav;
    }

    @RequestMapping(value = "/book-table/book/{customer_id}", method = RequestMethod.GET)
    public String BookTable(@PathVariable int customer_id,
                            HttpServletRequest request) throws ParseException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int table_id = Integer.parseInt(request.getParameter("table_id"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long ms = sdf.parse(time).getTime();
        Time timeArrival = new Time(ms);
        Date dateArrival = Date.valueOf(date);
        CustomerModel customer = new CustomerModel();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setId(customer_id);
        customerDao.updateNamePhone(customer);
        BookingTableModel bookTable = new BookingTableModel(customer_id, table_id, dateArrival, timeArrival);
        if (bookingTableDao.insert(bookTable)){
            tableDao.updateStatus(table_id,"deactivate");
        }
        return "redirect:/book-table";
    }
}
