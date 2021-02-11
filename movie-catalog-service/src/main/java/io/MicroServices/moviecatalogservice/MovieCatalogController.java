package io.MicroServices.moviecatalogservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {


    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){
        

        //get all rated movie Id
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",5),
                new Rating("5678",4)
        );
        return ratings.stream().map(rating -> {
            Movie movie  = restTemplate.getForObject("http://localhost:8051/movies/" + rating.getMovieId(),Movie.class);
            return new CatalogItem(movie.getName(), "description",rating.getRating());
        })
                .collect(Collectors.toList());
        //for each movieId,call movie service and getDetails

        //Put them all together

           /* List<CatalogItem> catalogList = new ArrayList<>();
            catalogList.add(new CatalogItem("Terminator","action",4));
            return catalogList;*/
    }

}