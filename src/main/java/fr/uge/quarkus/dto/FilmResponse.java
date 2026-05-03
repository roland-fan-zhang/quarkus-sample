package fr.uge.quarkus.dto;

import fr.uge.quarkus.model.Film;
import java.math.BigDecimal;

public record FilmResponse(
    String title,
    String description,
    Short releaseYear,
    Byte rentalDuration,
    BigDecimal rentalRate,
    Short length,
    String rating,
    String specialFeatures
) {

  public static FilmResponse from(Film film) {
    return new FilmResponse(
        film.getTitle(),
        film.getDescription(),
        film.getReleaseYear(),
        film.getRentalDuration(),
        film.getRentalRate(),
        film.getLength(),
        film.getRating(),
        film.getSpecialFeatures()
    );
  }
}
