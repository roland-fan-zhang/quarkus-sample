package fr.uge.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "film")
public class Film extends PanacheEntityBase {

  public static final byte DEFAULT_RENTAL_DURATION = 3;
  public static final BigDecimal DEFAULT_RENTAL_RATE = new BigDecimal("4.99");
  public static final BigDecimal DEFAULT_REPLACEMENT_COST = new BigDecimal("19.99");
  public static final String DEFAULT_RATING = "G";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
  private Short id;

  @Column(name = "title", nullable = false, length = 128)
  private String title;

  @Column(name = "description", columnDefinition = "tinytext")
  private String description;

  @Column(name = "release_year", columnDefinition = "year")
  private Short releaseYear;

  @Column(name = "language_id", columnDefinition = "tinyint UNSIGNED not null")
  private Short languageId;

  @ColumnDefault("'3'")
  @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
  private Byte rentalDuration;

  @ColumnDefault("4.99")
  @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
  private BigDecimal rentalRate;

  @Column(name = "length", columnDefinition = "smallint UNSIGNED")
  private Short length;

  @ColumnDefault("19.99")
  @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
  private BigDecimal replacementCost;

  @ColumnDefault("'G'")
  @Column(name = "rating", columnDefinition = "tinytext default 'G'")
  private String rating;

  @Column(name = "special_features", columnDefinition = "tinytext")
  private String specialFeatures;

  @CreationTimestamp
  @Column(name = "last_update", nullable = false)
  private Instant lastUpdate;

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Short getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Short releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Short getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Short languageId) {
    this.languageId = languageId;
  }

  public Byte getRentalDuration() {
    return rentalDuration;
  }

  public void setRentalDuration(Byte rentalDuration) {
    this.rentalDuration = rentalDuration;
  }

  public BigDecimal getRentalRate() {
    return rentalRate;
  }

  public void setRentalRate(BigDecimal rentalRate) {
    this.rentalRate = rentalRate;
  }

  public Short getLength() {
    return length;
  }

  public void setLength(Short length) {
    this.length = length;
  }

  public BigDecimal getReplacementCost() {
    return replacementCost;
  }

  public void setReplacementCost(BigDecimal replacementCost) {
    this.replacementCost = replacementCost;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getSpecialFeatures() {
    return specialFeatures;
  }

  public void setSpecialFeatures(String specialFeatures) {
    this.specialFeatures = specialFeatures;
  }

  public Instant getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Instant lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}
