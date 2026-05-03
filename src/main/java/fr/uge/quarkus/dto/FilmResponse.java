package fr.uge.quarkus.dto;

import fr.uge.quarkus.model.Film;
import java.math.BigDecimal;
import java.time.Instant;

public record FilmResponse(
    Short id,
    String title,
    String description,
    Short releaseYear,
    Short languageId,
    Byte rentalDuration,
    BigDecimal rentalRate,
    Short length,
    BigDecimal replacementCost,
    String rating,
    String specialFeatures,
    Instant lastUpdate
) {

  public static FilmResponse from(Film film) {
    return new FilmResponse(
        film.getId(),
        film.getTitle(),
        film.getDescription(),
        film.getReleaseYear(),
        film.getLanguageId(),
        film.getRentalDuration(),
        film.getRentalRate(),
        film.getLength(),
        film.getReplacementCost(),
        film.getRating(),
        film.getSpecialFeatures(),
        film.getLastUpdate()
    );
  }
}
