package com.hotel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {
    @Id @GeneratedValue
    private Long reviewId;

    @Max(value = 10, message = "rating cannot be more than 10")
    @Min(value = 0, message = "rating cannot be less than 10")
    private Integer ratingValue;
    private String comment;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="hotelId", nullable = false)
    @JsonIgnoreProperties("rooms")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="travelerId", nullable = false)
    @JsonIgnoreProperties("reviews")
    private Traveler traveler;
}
