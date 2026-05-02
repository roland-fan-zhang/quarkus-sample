package fr.uge.quarkus.resource;

import fr.uge.quarkus.model.Film;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class FilmResource {

  @GET
  @Path("/film/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Film getFilm(@PathParam("id") int id) {
    return Film.findById(id);
  }
}
