package com.hcmute.controller.admin;


import com.hcmute.dao.CategoryDao;
import com.hcmute.model.ProductCategoryModel;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/quan-ly-danh-muc")
public class CategoryController {
    public CategoryDao categoryDao;

    public CategoryController() {
        this.categoryDao = new CategoryDao();
    }

    @RequestMapping("/")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView("admin/category");
        mav.addObject("categories", categoryDao.selectCategory());
        return mav;
    }

    @RequestMapping("/view/edit")
    public ModelAndView RetrieveEdit(@RequestParam(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/category-edit");
        mav.addObject("category", categoryDao.selectCategoryByID(id));
        mav.addObject("active7", "active");
        return mav;
    }
    @RequestMapping("/view/add")
    public String RetrieveAdd() {
        return "admin/category-add";
    }
    @RequestMapping("/delete/{id}")
    public String Delete(@PathVariable int id) {
        categoryDao.deleteCategory(id);
        return "redirect:/admin/quan-ly-danh-muc/";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String Update(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "name") String name) {
        ProductCategoryModel category = new ProductCategoryModel(id, name);
        categoryDao.updateCategory(category);
        return "redirect:/admin/quan-ly-danh-muc/";
    }
    @RequestMapping("/insert")
    public String Insert(
            @RequestParam(name = "name") String name) {
        ProductCategoryModel category = new ProductCategoryModel(-1,name);
        categoryDao.insertCategory(category);
        return "redirect:/admin/quan-ly-danh-muc/";
    }
}
