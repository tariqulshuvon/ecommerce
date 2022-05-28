package com.shop.ecommerce.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductForm {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private int price;
}
