package com.hibicode.beerstore.model.dto;

import java.math.BigDecimal;

import com.hibicode.beerstore.model.enums.BeerType;

import lombok.Data;

@Data
public class BeerDTO {

    private Long id;

    private String name;

    private BeerType type;

    private BigDecimal volume;
}
