package com.hotel.app.repository;

import com.hotel.app.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TravelerRepository extends JpaRepository<Traveler, Long>, JpaSpecificationExecutor<Traveler> {
}
