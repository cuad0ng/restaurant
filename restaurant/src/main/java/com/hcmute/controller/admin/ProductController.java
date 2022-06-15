package com.hcmute.controller.admin;

import com.hcmute.dao.ProductDao;
import com.hcmute.model.EmployeeModel;
import com.hcmute.model.ProductCategoryModel;
import com.hcmute.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductController {
    ProductDao productDao = new ProductDao();

    @RequestMapping("viewproduct")
    public String viewemp(Model m){
        List<ProductModel> list=productDao.getAllProduct();
        m.addAttribute("list",list);
        return "admin/Product/viewproduct";
    }
    @RequestMapping(value="/deleteproduct/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id) throws SQLException, ClassNotFoundException{
        productDao.deleteProduct(id);
        return "redirect:/viewproduct";
    }

    @RequestMapping(value="/editproduct/{id}")
    public String edit(@PathVariable int id, Model m, Model n){
        ProductModel productModel=productDao.getProductById(id);
        m.addAttribute("command",productModel);
        List<ProductCategoryModel> list=productDao.getAllCategoryId();
        n.addAttribute("list",list);
        return "admin/Product/producteditform";
    }
//    @RequestMapping(value="/editsaveproduct",method = RequestMethod.POST)
//    public String editsave(@ModelAttribute("emp") ProductModel productModel) throws SQLException{
//        productDao.updateProduct(productModel);
//        return "redirect:/viewproduct";
//    }
@RequestMapping(value="editsaveproduct",method=RequestMethod.POST)
public String editsave(@RequestParam CommonsMultipartFile file,
                        HttpSession session, @ModelAttribute("product") ProductModel productModel) throws Exception,SQLException{

    ServletContext context = session.getServletContext();
    String path = context.getRealPath(UPLOAD_DIRECTORY);
    path = context.getRealPath("/WEB-INF/resources/images/asset");
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
    productModel.setImg("../resources/images/asset/"+filename);
    productDao.updateProduct(productModel);
    return "redirect:/viewproduct";
}

    @RequestMapping("/productform")
    public String showform(Model m,Model n){
        m.addAttribute("command", new ProductModel());
        List<ProductCategoryModel> list=productDao.getAllCategoryId();
        n.addAttribute("list",list);
        return "admin/Product/productform";
    }

    private static final String UPLOAD_DIRECTORY ="";

//    @RequestMapping(value="/saveproduct",method = RequestMethod.POST)
//    public String save(@ModelAttribute("product") ProductModel productModel) throws SQLException{
////        String image = "resources/images/employee/"+ emp.getId() +".jpg";
////        emp.setImg(image);
////        System.out.println(image);
//        productDao.insertProduct(productModel);
//        return "redirect:/viewproduct";
//    }

    @RequestMapping(value="saveproduct",method=RequestMethod.POST)
    public String saveimage(@RequestParam CommonsMultipartFile file,
                                  HttpSession session, @ModelAttribute("product") ProductModel productModel) throws Exception,SQLException{

        ServletContext context = session.getServletContext();
        String path = context.getRealPath(UPLOAD_DIRECTORY);
        path = context.getRealPath("/WEB-INF/resources/images/asset");
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
        productModel.setImg("../resources/images/asset/"+filename);
        productDao.insertProduct(productModel);
        return "redirect:/viewproduct";
    }


}
