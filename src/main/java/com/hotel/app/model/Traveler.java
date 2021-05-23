package com.hotel.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Traveler {

    @Id
    @GeneratedValue
    private Long travelerId;

    private String firstName;
    private String lastName;
    private String gender;
    private String residentialCity;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("traveler")
    private List<Review> reviews = new ArrayList<>();
}
