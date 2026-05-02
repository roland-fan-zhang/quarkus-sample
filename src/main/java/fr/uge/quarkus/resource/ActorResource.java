package fr.uge.quarkus.resource;

import fr.uge.quarkus.model.Actor;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ActorResource {

  @GET
  @Path("/actor/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Actor getActor(@PathParam("id") int id) {
    return Actor.findById(id);
  }
}
