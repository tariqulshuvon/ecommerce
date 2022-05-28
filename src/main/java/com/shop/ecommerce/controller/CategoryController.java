package com.shop.ecommerce.controller;

import com.shop.ecommerce.forms.CategoryForm;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String saveCategory(@Valid @ModelAttribute("category") CategoryForm form, BindingResult result) {
        categoryService.findByName(form.getName()).ifPresent(category ->
                result.rejectValue("name", "error.category", form.getName() + " already exists"));

        if (result.hasErrors()) {
            return "category/form";
        }

        categoryService.save(Category.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription()).build());
        return "redirect:/category";

    }

    @GetMapping("/edit/{id}")
    public String showEditCategory(Model model, @PathVariable(name = "id") long id) {
        categoryService.findById(id).ifPresent(ct -> {
            CategoryForm form = CategoryForm.builder()
                    .id(ct.getId())
                    .name(ct.getName())
                    .description(ct.getDescription())
                    .build();
            model.addAttribute("category", form);
        });

        return "category/form";
    }


    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }


}
