package com.cts.flightsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.flightsearch.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{
  public Flight findByUserId(int id);
  
  public List<Flight> findByAirline(String airline);
}
