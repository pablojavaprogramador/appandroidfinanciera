package com.tecnoplacita.codespeak.io.request;


public class SearchRequest {
    private String busquedad;

    public SearchRequest(String busquedad) {
        this.busquedad = busquedad;
    }

    public String getBusquedad() {
        return busquedad;
    }
}
