package com.programming.services.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductShipping {
    private BigDecimal weight;
    private String weightUnit;
    private Dimensions dimensions;
    private Boolean freeShipping;

}
