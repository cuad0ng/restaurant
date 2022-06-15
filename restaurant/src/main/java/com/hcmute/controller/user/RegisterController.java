package com.hcmute.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcmute.dao.CustomerDao;
import com.hcmute.model.AccountModel;
import com.hcmute.model.CustomerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcmute.dao.AccountDao;


import java.io.IOException;

@Controller
public class RegisterController {
    AccountDao dao = new AccountDao();
    CustomerDao customerDao = new CustomerDao();
    @RequestMapping(value= {"/register","/dang-ky"}, method=RequestMethod.GET)
    public String showForm() {
    	return "login";
    }
    @RequestMapping(value= {"/register","/dang-ky"}, method=RequestMethod.POST)
    public String register(ModelMap model, HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");
        AccountModel account = new AccountModel(username,password,"user");
        if(confirm.trim().equals(password.trim()) ){
            AccountModel acc_model = dao.getAccount(username,password);
            if(acc_model != null){
                model.addAttribute("msg","Tài khoản đã tồn tại");
                model.addAttribute("type","error");
                return "login";
            }
            account = new AccountModel(username,password,"user");
            Boolean result = dao.insert(account);
            if(result){
                model.addAttribute("msg","Đăng ký tài khoản thành công");
                model.addAttribute("type","success");
                // tạo customer
                CustomerModel temp = new CustomerModel();
                temp.setName(username);
                customerDao.insert(temp);
                int customer_id = customerDao.getIdByName(username);
                dao.updateCustomerID(customer_id, username);
                return "login";

            }
            else{
                model.addAttribute("msg","Vui lòng thử lại");
                model.addAttribute("type","error");
                return "login";
            }

        }
        else{
            model.addAttribute("msg","Vui lòng xác nhận lại mật khẩu");
            model.addAttribute("type","error");
            return "login";
        }
    }
}
