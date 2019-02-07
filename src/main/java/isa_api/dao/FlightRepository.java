package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.flight.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
