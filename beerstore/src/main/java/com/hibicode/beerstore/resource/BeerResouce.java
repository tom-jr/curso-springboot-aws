package com.hibicode.beerstore.resource;

import java.util.List;

import javax.validation.Valid;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.dto.BeerDTO;
import com.hibicode.beerstore.service.BeerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(value = "/{beerId}")
    public Beer getyById(@PathVariable Long beerId) {

        return this.beerService.findyById(beerId);
    }

    @PostMapping
    public Beer add(@RequestBody @Valid BeerDTO dto) {
        Beer beer = this.beerService.add(dto);
        return this.beerService.save(beer);

    }

    @PutMapping(value = "/{beerId}")
    public ResponseEntity<?> update(@PathVariable Long beerId,@Valid @RequestBody BeerDTO dto) {
        Beer beerFromPersistence = this.beerService.findyById(beerId);
        Beer beerUpdated = this.beerService.fromBeerDtoToBeer(beerFromPersistence,dto);
        beerUpdated = this.beerService.save(beerUpdated);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(beerUpdated);
    }

    @DeleteMapping(value = "/{beerId}")
    public ResponseEntity<?> delete(@PathVariable Long beerId) {
        Beer beerFromPersistence = this.beerService.findyById(beerId);
        this.beerService.getRepository().delete(beerFromPersistence);
        return ResponseEntity.noContent().build();
    }
}
