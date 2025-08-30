package com.programming.services.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Dimensions {
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private String unit;

}
