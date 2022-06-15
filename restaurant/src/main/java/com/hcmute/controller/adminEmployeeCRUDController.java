package com.hcmute.controller;
import com.hcmute.dao.EmployeeDao;
import com.hcmute.model.EmployeeModel;
import com.hcmute.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class adminEmployeeCRUDController {
    @Autowired
    ServletContext servletContext;

    EmployeeDao employeeDao = new EmployeeDao();


    @RequestMapping("viewemp")
    public String viewemp(Model m) {
        List<EmployeeModel> list = employeeDao.selectAllEmployee();
        m.addAttribute("list", list);
        return "admin/Employee/viewemp";
    }

    @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) throws SQLException, ClassNotFoundException {
        employeeDao.deleteEmployee(id);
        return "redirect:/viewemp";
    }

    @RequestMapping(value = "/editemp/{id}")
    public String edit(@PathVariable int id, Model m) {
        EmployeeModel emp = employeeDao.selectEmployee(id);
        m.addAttribute("command", emp);
        return "admin/Employee/empeditform";
    }

//    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
//    public String editsave(@ModelAttribute("emp") EmployeeModel emp) throws SQLException {
//        employeeDao.updateEmployee(emp);
//        return "redirect:/viewemp";
//    }

    @RequestMapping(value="/editsave",method=RequestMethod.POST)
    public String editsave(@RequestParam CommonsMultipartFile file,
                            HttpSession session, @ModelAttribute("product") EmployeeModel employeeModel) throws Exception,SQLException{

        ServletContext context = session.getServletContext();
        String path = context.getRealPath("");
        path = context.getRealPath("/WEB-INF/resources/images/employee");
        String filename = file.getOriginalFilename();
        System.out.println(path+" "+filename);
        byte[] bytes = file.getBytes();
//        PageContext pageContext;
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                new File(path + File.separator+ filename)));
        System.out.println(path);
        stream.write(bytes);
        stream.flush();
        stream.close();
        employeeModel.setImg("../resources/images/employee/"+filename);
        employeeDao.updateEmployee(employeeModel);
        return "redirect:/viewemp";
    }


    @RequestMapping("/empform")
    public String showform(Model m) {
        m.addAttribute("command", new EmployeeModel());
        return "admin/Employee/empform";
    }
//    @RequestMapping(value="/save",method = RequestMethod.POST)
//    public String save(@ModelAttribute("emp") EmployeeModel emp) throws SQLException{
////        String image = "resources/images/employee/"+ emp.getId() +".jpg";
////        emp.setImg(image);
////        System.out.println(image);
//        employeeDao.insertEmployee(emp);
//        return "redirect:/viewemp";
//    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveimage(@RequestParam CommonsMultipartFile file,
                            HttpSession session, @ModelAttribute("product") EmployeeModel employeeModel) throws Exception,SQLException{

        ServletContext context = session.getServletContext();
        String path = context.getRealPath("");
        path = context.getRealPath("/WEB-INF/resources/images/employee");
        String filename = file.getOriginalFilename();
        System.out.println(path+" "+filename);
        byte[] bytes = file.getBytes();
//        PageContext pageContext;
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                new File(path + File.separator+ filename)));
        System.out.println(path);
        stream.write(bytes);
        stream.flush();
        stream.close();
        employeeModel.setImg("../resources/images/employee/"+filename);
        employeeDao.insertEmployee(employeeModel);
        return "redirect:/viewemp";
    }


}
