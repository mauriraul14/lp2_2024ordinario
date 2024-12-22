package io.quarkus.repository;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class prueba {

    @Path("/mi-recurso")
public class MiRecurso {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtener() {
        return "Hola, Mundo!";
    }
}

    
}
