package com.hibicode.beerstore.repository;

import java.util.Optional;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.enums.BeerType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);

}
