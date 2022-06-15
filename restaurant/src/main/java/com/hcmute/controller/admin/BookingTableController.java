package com.hcmute.controller.admin;


import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.hcmute.dao.BookingTableDao;
import com.hcmute.dao.CustomerDao;
import com.hcmute.model.BookingTableModel;
import com.hcmute.model.CustomerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcmute.dao.TableDao;
import com.hcmute.model.DiningTableModel;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookingTableController {
	TableDao tableDao = new TableDao();
	BookingTableDao bookingTableDao = new BookingTableDao();
	CustomerDao customerDao = new CustomerDao();
	@RequestMapping(value= {"/booking-table","/dat-cho"},method=RequestMethod.GET)
	public String showListTable(ModelMap model) {
		List<DiningTableModel> list = tableDao.getAll();
		model.addAttribute("listTables",list);
		return "admin/booking-table";
	}
	@RequestMapping(value = {"/danh-sach-dat-ban","list-booked-table"})
	public String getListBookedTable(ModelMap modelMap){
		List<BookingTableModel> listBooking = bookingTableDao.getAll();
		HashMap<CustomerModel,BookingTableModel> list = getListBookedTable(listBooking);
		modelMap.addAttribute("listBooking",list);
		return "admin/list-booked-table";
	}

	@RequestMapping(value= {"/booking-table/{tableID}"},method=RequestMethod.POST)
	public String bookingTable(HttpServletRequest req, ModelMap model, @PathVariable int tableID) throws ParseException {
		String fullname = req.getParameter("fullname");
		System.out.println(tableID);
		String phone = req.getParameter("phone");
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		long ms = sdf.parse(time).getTime();
		Time timeArrival = new Time(ms);
		Date dateArrival = Date.valueOf(date);
		CustomerModel customer = new CustomerModel();
		customer.setName(fullname);
		customer.setPhone(phone);
		int customerID = customerDao.insert(customer);
		if(customerID != 0){
			BookingTableModel booking = new BookingTableModel(customerID,tableID,dateArrival,timeArrival);
			if(bookingTableDao.insert(booking)){
				tableDao.updateStatus(tableID,"deactivate");
			}
		}
		String redirectUrl = "/booking-table";
		return "redirect:" + redirectUrl;
	}
	@RequestMapping(value = {"/booking-table/cancel/{ID}"}, method = RequestMethod.GET)
	public String cancelBooking(HttpServletRequest req, @PathVariable int ID){
		BookingTableModel bookingTableModel = bookingTableDao.get(ID);
		if(bookingTableDao.updateState(ID,"canceled")){
			tableDao.updateStatus(bookingTableModel.getTableId(),"active");
		}
		String redirectUrl = "/list-booked-table";
		return "redirect:" + redirectUrl;
	}
	@RequestMapping(value = {"/booking-table/checked/{ID}"}, method = RequestMethod.GET)
	public String checkedBooking(@PathVariable int ID){
		BookingTableModel bookingTableModel = bookingTableDao.get(ID);
		if(bookingTableDao.updateState(ID,"checked")){
			tableDao.updateStatus(bookingTableModel.getTableId(),"active");
		}
		String redirectUrl = "/list-booked-table";
		return "redirect:" + redirectUrl;
	}
	@RequestMapping(value = {"/booking-table/search"},method = RequestMethod.GET)
	public String searchByPhone(HttpServletRequest req,ModelMap modelMap){
		String phone = req.getParameter("phone");
		List<BookingTableModel> listBooking = bookingTableDao.seachByCustomerPhone(phone);
		HashMap<CustomerModel,BookingTableModel> list = getListBookedTable(listBooking);
		modelMap.addAttribute("listBooking",list);
		return "admin/list-booked-table";
	}

	public HashMap<CustomerModel,BookingTableModel> getListBookedTable(List<BookingTableModel> listBooking){
		HashMap<CustomerModel,BookingTableModel> list = new HashMap<>();
		for(BookingTableModel bookingTableModel:listBooking){
			CustomerModel customer = customerDao.getByID(bookingTableModel.getCusId());
			list.put(customer,bookingTableModel);
		}
		return list;
	}

}
