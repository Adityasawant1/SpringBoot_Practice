package com.spring.projects.JPA.dto;

import java.math.BigDecimal;

public interface ProductProjection {

    Long getId();
    String getTitle();
    BigDecimal getPrice();

}
