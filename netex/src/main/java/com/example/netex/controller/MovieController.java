package com.example.netex.controller;

import com.example.netex.dto.MovieRequestDto;
import com.example.netex.model.Movie;
import com.example.netex.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MovieController {

    private MovieService movieService;

    @GetMapping("/")
    public List<Movie> getTest() {
        return movieService.findAll();
    }

    @PostMapping("/movie")
    public Movie createMovie(@RequestBody MovieRequestDto movie) {

        return movieService.save(movie);
    }
}
