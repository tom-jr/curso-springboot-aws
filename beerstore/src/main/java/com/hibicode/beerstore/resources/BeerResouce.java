package com.hibicode.beerstore.resources;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.dto.BeerDTO;
import com.hibicode.beerstore.service.BeerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/beers")
public class BeerResouce {

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> listBeers() {

        return this.beerService.getRepository().findAll();
    }

    @PostMapping
    public Beer add(@RequestBody @Valid BeerDTO dto) {
        Beer beer = this.beerService.add(dto);
        return this.beerService.getRepository().save(beer);

    }
}
