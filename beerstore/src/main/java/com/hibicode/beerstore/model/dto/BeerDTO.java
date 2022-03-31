package com.hibicode.beerstore.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hibicode.beerstore.model.enums.BeerType;

import lombok.Data;

@Data
public class BeerDTO {

    private Long id;
    
    @NotBlank
    private String name;

    @NotNull
    private BeerType type;

    @NotNull
    @DecimalMin("0")
    private BigDecimal volume;
}
