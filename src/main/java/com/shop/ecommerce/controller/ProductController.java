package com.shop.ecommerce.controller;


import com.shop.ecommerce.forms.ProductForm;
import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.service.CategoryService;
import com.shop.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;


    @GetMapping
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", ProductForm.builder().build());
        model.addAttribute("categories", categoryService.findAll());
        return "product/form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") ProductForm pf) {
        Category category = categoryService.findById(pf.getCategoryId());
        productService.save(Product.builder()
                .id(pf.getId())
                .category(category)
                .name(pf.getName())
                .description(pf.getDescription())
                .price(pf.getPrice())
                .build());
        return "redirect:/product";
    }

    @GetMapping("edit/{id}")
    public String showEditProduct(Model model, @PathVariable(name = "id") long id) {
        Product p = productService.findById(id);
        ProductForm form = ProductForm.builder()
                .id(p.getId())
                .categoryId(p.getCategory().getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();
        model.addAttribute("product", form);
        model.addAttribute("categories", categoryService.findAll());
        return "product/form";

    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        productService.delete(id);
        return "redirect:/product";
    }


}
