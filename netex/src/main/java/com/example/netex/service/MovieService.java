package com.example.netex.service;

import com.example.netex.dto.MovieRequestDto;
import com.example.netex.dto.OmdbApiDto;
import com.example.netex.model.Movie;
import com.example.netex.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class MovieService {
    private final OmdbApiWebService omdbApiWebService;
    private final MovieRepository movieRepository;

    public Movie save(MovieRequestDto movieRequestDto) {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("t", movieRequestDto.getTitle());
        OmdbApiDto omdbApiDto = omdbApiWebService.getBooksBy(qParams);
        return movieRepository.save(toMovie(omdbApiDto));
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    private Movie toMovie(OmdbApiDto omdbApiDto) {
        return new Movie(omdbApiDto.getTitle(), omdbApiDto.getYear());
    }
}
