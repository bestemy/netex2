package com.example.netex.service;

import com.example.netex.dto.OmdbApiDto;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.GenericType;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OmdbApiWebService extends WebClientService {

    private final JacksonJsonProvider jacksonJsonProvider;

    private final String baseUrl = "http://www.omdbapi.com/";


    public OmdbApiDto getBooksBy(Map<String, Object> queryParams) {

        queryParams.put("apikey", "e12179bf");
        return doGet(new GenericType<>(){}, queryParams);
    }

    @Override
    protected JacksonJsonProvider getJacksonProvider() {
        return jacksonJsonProvider;
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }
}
