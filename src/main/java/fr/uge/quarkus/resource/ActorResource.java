package fr.uge.quarkus.resource;

import fr.uge.quarkus.dto.ActorRequest;
import fr.uge.quarkus.dto.ActorResponse;
import fr.uge.quarkus.model.Actor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Path("/actors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActorResource {

  @GET
  @Operation(summary = "List all actors with pagination")
  public List<ActorResponse> list(@QueryParam("page") @DefaultValue("0") @Min(0) int page,
                                  @QueryParam("size") @DefaultValue("20") @Positive @Max(100) int size) {
    List<Actor> actors = Actor.findAll().page(page, size).list();
    return actors.stream().map(ActorResponse::from).toList();
  }

  @GET @Path("/{id}")
  @Operation(summary = "Get an actor by ID")
  public ActorResponse get(@PathParam("id") int id) {
    Optional<Actor> actor = Actor.findByIdOptional(id);
    return ActorResponse.from(actor.orElseThrow(NotFoundException::new));
  }

  @POST
  @Transactional
  @Operation(summary = "Create a new actor")
  public Response create(@Valid ActorRequest dto, @Context UriInfo uriInfo) {
    var actor = new Actor();
    actor.setFirstName(dto.firstName());
    actor.setLastName(dto.lastName());
    actor.persist();
    var uri = uriInfo.getAbsolutePathBuilder().path(actor.getId().toString()).build();
    return Response.created(uri).entity(ActorResponse.from(actor)).build();
  }

  @PUT @Path("/{id}")
  @Transactional
  @Operation(summary = "Update an existing actor")
  public ActorResponse update(@PathParam("id") int id, @Valid ActorRequest dto) {
    Optional<Actor> opt = Actor.findByIdOptional(id);
    var actor = opt.orElseThrow(NotFoundException::new);
    actor.setFirstName(dto.firstName());
    actor.setLastName(dto.lastName());
    actor.setLastUpdate(Instant.now());
    return ActorResponse.from(actor);
  }

  @DELETE @Path("/{id}")
  @Transactional
  @Operation(summary = "Delete an actor by ID")
  public Response delete(@PathParam("id") int id) {
    Optional<Actor> opt = Actor.findByIdOptional(id);
    opt.orElseThrow(NotFoundException::new).delete();
    return Response.noContent().build();
  }
}
