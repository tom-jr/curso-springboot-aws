package com.hibicode.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import com.hibicode.beerstore.error.BeerAlreadyExistsException;
import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.enums.BeerType;
import com.hibicode.beerstore.repository.BeerRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BeerServiceTest {

    

    @Mock
    BeerRepository beerMock;
    @Test
    public void should_deny_creation_of_beer_that_early_exixts() {
        MockitoAnnotations.initMocks(this);
         BeerService beerService = new BeerService(beerMock);


        Exception exception = assertThrows(BeerAlreadyExistsException.class, () -> {



            Beer beerFromPersistence = new Beer();
            beerFromPersistence.setId(10L);
            beerFromPersistence.setName("Heineken");
            beerFromPersistence.setType(BeerType.LARGE);
            beerFromPersistence.setVolume(new BigDecimal("355"));
    
            Mockito.when(beerMock.findByNameAndType("Heineken", BeerType.LARGE)).thenReturn(Optional.of(beerFromPersistence));





            Beer newBeer = new Beer();
            newBeer.setName("Heineken");
            newBeer.setType(BeerType.LARGE);
            newBeer.setVolume(new BigDecimal("355"));

            beerService.save(newBeer);

        });
    }

    @Test
    public void shold_create_bew_beer() {
        MockitoAnnotations.initMocks(this);
        BeerService beerService = new BeerService(beerMock);

        Beer beerFromPersistence = new Beer();
        beerFromPersistence.setId(10L);
        beerFromPersistence.setName("Heineken");
        beerFromPersistence.setType(BeerType.LARGE);
        beerFromPersistence.setVolume(new BigDecimal("355"));


        
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LARGE);
        newBeer.setVolume(new BigDecimal("355"));
        
        Mockito.when(beerMock.save(newBeer)).thenReturn(beerFromPersistence);

        Beer beerSaved = beerService.save(newBeer);
        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LARGE));

    }
}
