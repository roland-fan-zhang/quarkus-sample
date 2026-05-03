package fr.uge.quarkus.resource;

import fr.uge.quarkus.dto.FilmRequest;
import fr.uge.quarkus.dto.FilmResponse;
import fr.uge.quarkus.model.Film;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
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

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilmResource {

  @GET
  public List<FilmResponse> list(@QueryParam("page") @DefaultValue("0") @Min(0) int page,
                                 @QueryParam("size") @DefaultValue("20") @Positive @Max(100) int size) {
    List<Film> films = Film.findAll().page(page, size).list();
    return films.stream().map(FilmResponse::from).toList();
  }

  @GET @Path("/{id}")
  public FilmResponse get(@PathParam("id") int id) {
    Optional<Film> film = Film.findByIdOptional(id);
    return FilmResponse.from(film.orElseThrow(NotFoundException::new));
  }

  @POST
  @Transactional
  public Response create(@Valid FilmRequest dto, @Context UriInfo uriInfo) {
    var film = new Film();
    film.setTitle(dto.title());
    film.setDescription(dto.description());
    film.setReleaseYear(dto.releaseYear());
    film.setLanguageId(dto.languageId());
    film.setRentalDuration(dto.rentalDuration() != null ? dto.rentalDuration() : Film.DEFAULT_RENTAL_DURATION);
    film.setRentalRate(dto.rentalRate() != null ? dto.rentalRate() : Film.DEFAULT_RENTAL_RATE);
    film.setLength(dto.length());
    film.setReplacementCost(dto.replacementCost() != null ? dto.replacementCost() : Film.DEFAULT_REPLACEMENT_COST);
    film.setRating(dto.rating() != null ? dto.rating() : Film.DEFAULT_RATING);
    film.setSpecialFeatures(dto.specialFeatures());
    film.persist();
    var uri = uriInfo.getAbsolutePathBuilder().path(film.getId().toString()).build();
    return Response.created(uri).entity(FilmResponse.from(film)).build();
  }

  @PUT @Path("/{id}")
  @Transactional
  public FilmResponse update(@PathParam("id") int id, @Valid FilmRequest dto) {
    Optional<Film> opt = Film.findByIdOptional(id);
    var film = opt.orElseThrow(NotFoundException::new);
    film.setTitle(dto.title());
    film.setDescription(dto.description());
    film.setReleaseYear(dto.releaseYear());
    film.setLanguageId(dto.languageId());
    film.setRentalDuration(dto.rentalDuration() != null ? dto.rentalDuration() : Film.DEFAULT_RENTAL_DURATION);
    film.setRentalRate(dto.rentalRate() != null ? dto.rentalRate() : Film.DEFAULT_RENTAL_RATE);
    film.setLength(dto.length());
    film.setReplacementCost(dto.replacementCost() != null ? dto.replacementCost() : Film.DEFAULT_REPLACEMENT_COST);
    film.setRating(dto.rating() != null ? dto.rating() : Film.DEFAULT_RATING);
    film.setSpecialFeatures(dto.specialFeatures());
    film.setLastUpdate(Instant.now());
    return FilmResponse.from(film);
  }

  @DELETE @Path("/{id}")
  @Transactional
  public Response delete(@PathParam("id") int id) {
    Optional<Film> opt = Film.findByIdOptional(id);
    opt.orElseThrow(NotFoundException::new).delete();
    return Response.noContent().build();
  }
}
