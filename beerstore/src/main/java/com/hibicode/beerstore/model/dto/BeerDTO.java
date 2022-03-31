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
    
    @NotBlank(message = "beer-1")
    private String name;

    @NotNull(message = "beer-2")
    private BeerType type;

    @NotNull(message = "beer-3")
    @DecimalMin(value = "0",message = "beer-3")
    private BigDecimal volume;
}
