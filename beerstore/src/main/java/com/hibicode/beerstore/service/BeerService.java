package com.hibicode.beerstore.service;

import java.util.Optional;

import com.hibicode.beerstore.error.BeerAlreadyExistsException;
import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.dto.BeerDTO;
import com.hibicode.beerstore.repository.BeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    private BeerRepository beerRepository;

    public BeerRepository getRepository() {
        return beerRepository;
    }

    public BeerService( BeerRepository beerRepository){
        this.beerRepository = beerRepository;
    }

    public Beer add(BeerDTO dto) {
        Beer beer = new Beer();
        beer.setId(null);
        beer.setName(dto.getName());
        beer.setType(dto.getType());
        beer.setVolume(dto.getVolume());
        return beer;
    }

    public Beer save(Beer beer) {
        Optional<Beer> beerFromPersistence = this.getRepository().findByNameAndType(beer.getName(), beer.getType());

        if (beerFromPersistence.isPresent()) {
            throw new BeerAlreadyExistsException();
        }
        return this.getRepository().save(beer);
    }

}
