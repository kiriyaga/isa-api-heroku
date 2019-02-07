package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.flight.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
