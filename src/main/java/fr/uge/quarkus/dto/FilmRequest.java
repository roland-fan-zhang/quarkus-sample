package fr.uge.quarkus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record FilmRequest(
    @NotBlank @Size(max = 128) String title,
    String description,
    Short releaseYear,
    @NotNull Short languageId,
    Byte rentalDuration,
    @Positive BigDecimal rentalRate,
    Short length,
    @Positive BigDecimal replacementCost,
    String rating,
    String specialFeatures
) {}
