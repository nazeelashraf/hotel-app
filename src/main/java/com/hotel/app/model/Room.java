package com.hotel.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "hotel")
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {

    @Id @GeneratedValue
    private Long roomId;

    private BigDecimal pricePerNight;
    private Integer beds;
    private Boolean wifi;
    private Boolean ac;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookedStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookedEndDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="hotelId", nullable = false)
    @JsonIgnoreProperties("rooms")
    private Hotel hotel;
}
