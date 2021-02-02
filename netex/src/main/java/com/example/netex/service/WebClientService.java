package com.example.netex.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Map;

public abstract class WebClientService {
    protected static final String COOKIE_HEADER_NAME = "Cookie";

    private WebTarget baseTarget;

    protected abstract JacksonJsonProvider getJacksonProvider();

    protected abstract String getBaseUrl();

    protected <T> T doGet(GenericType<T> responseType, Map<String, Object> templateValues) {
        return doGet( responseType, templateValues, MediaType.APPLICATION_JSON);
    }

    private <T> T doGet(GenericType<T> responseType, Map<String, Object> templateValues, String acceptedMediaType) {
        baseTarget = ClientBuilder.newClient().target(getBaseUrl());
        T result = applyQueryParam(templateValues, baseTarget)
                .register(getJacksonProvider())
                .request(acceptedMediaType)
                .get(responseType);

        return result;
    }

    private WebTarget applyQueryParam(Map<String, Object> queryParams, WebTarget targetResource){
        for(Map.Entry<String, Object> qParam: queryParams.entrySet()){
            targetResource = targetResource.queryParam(qParam.getKey(), qParam.getValue());
        }
        return targetResource;
    }

}
