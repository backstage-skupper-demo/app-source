package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;

@Path("/hello")
public class GreetingResource {
    @Inject
    Template hello;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        boolean connectionAvailable = false;
        boolean eksAvailable = false;
        List<Book> books = new ArrayList<>();
        String response = null;
        try {
            books = Book.listAll();
            connectionAvailable = true;
        } catch (Exception ex) {
        }

        try {
            
            eksAvailable = true;
        } catch (Exception ex) {
        }

        return hello
            .data("connectionAvailable", connectionAvailable)
            .data("eksAvailable", eksAvailable)
            .data("eksResponse", response)
            .data("books", books).render();
    }
}