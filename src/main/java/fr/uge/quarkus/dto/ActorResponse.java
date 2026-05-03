package fr.uge.quarkus.dto;

import fr.uge.quarkus.model.Actor;
import java.time.Instant;

public record ActorResponse(
    String firstName,
    String lastName,
    Instant lastUpdate
) {
  public static ActorResponse from(Actor actor) {
    return new ActorResponse(actor.getFirstName(), actor.getLastName(), actor.getLastUpdate());
  }
}
