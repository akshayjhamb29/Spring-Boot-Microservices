package io.MicroServices.movieinfoservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @RequestMapping("/{movieId}")
    public Movie getCatalog(@PathVariable("movieId")  String movieId){

        return new Movie(movieId,"Test name");
    }
}
