package com.hotel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "rooms")
@NoArgsConstructor
public class Hotel {

    @Id @GeneratedValue
    private Long hotelId;

    private String city;
    private String hotelName;
    @Min(value = 1, message = "stars must be greater than or equal to 1")
    @Max(value = 5, message = "stars must be lesser than or equal to 5")
    private Integer stars;
    private Boolean restaurant;
    private Boolean meals;
    private Double rating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();
}
