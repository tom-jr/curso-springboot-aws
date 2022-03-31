package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.dto.BeerDTO;
import com.hibicode.beerstore.repository.BeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {
    
    @Autowired
    private BeerRepository beerRepository;

    public BeerRepository getRepository() {
        return beerRepository;
    }

    public Beer add(BeerDTO dto) {
        Beer beer = new Beer();
        beer.setId(null);
        beer.setName(dto.getName());
        beer.setType(dto.getType());
        beer.setVolume(dto.getVolume());
        return beer;
    }

}
