package fr.uge.quarkus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActorRequest(
    @NotBlank @Size(max = 45) String firstName,
    @NotBlank @Size(max = 45) String lastName
) {}
