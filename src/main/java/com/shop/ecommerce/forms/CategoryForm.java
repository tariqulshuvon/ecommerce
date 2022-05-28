package com.shop.ecommerce.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryForm {
    private Long id;
    private String name;
    private String description;
}
