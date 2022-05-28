package com.shop.ecommerce.controller;

import com.shop.ecommerce.forms.CategoryForm;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.service.CategoryService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("category", CategoryForm.builder().build());
        return "category/form";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute("category") CategoryForm ctg) {
        categoryService.save(Category.builder()
                .id(ctg.getId())
                .name(ctg.getName())
                .description(ctg.getDescription()).build());
        return "redirect:/category";

    }

    @GetMapping("/edit/{id}")
    public String showEditCategory(Model model, @PathVariable(name = "id") long id) {
        Category ct = categoryService.findById(id);
        CategoryForm form = CategoryForm.builder()
                .id(ct.getId())
                .name(ct.getName())
                .description(ct.getDescription())
                .build();
        model.addAttribute("category", ct);
        return "category/form";
    }


    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }


}
