package com.hibicode.beerstore.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.hibicode.beerstore.model.enums.BeerType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Beer {
    @Id
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "beer_id_seq",sequenceName = "beer_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "beer_id_seq")
    private Long id;
    
    private String name;
    
    private BeerType type;
    
    private BigDecimal volume;
}