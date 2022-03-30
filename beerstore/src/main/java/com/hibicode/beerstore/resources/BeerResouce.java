package com.hibicode.beerstore.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/beers")
public class BeerResouce {
    
    @GetMapping
    public List<String> listBeers(){


    return Arrays.asList("Brahma","Amstel","Heineken","Budweiser","Devassa","Antarctica","Schin");
    }
}
