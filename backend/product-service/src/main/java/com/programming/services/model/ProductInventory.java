package com.programming.services.model;

import lombok.Data;

@Data
public class ProductInventory {
    private Integer available;
    private Integer reserved;
    private Integer minimumStock;
    private Boolean backorderAllowed;

}
