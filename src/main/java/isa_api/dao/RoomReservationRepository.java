package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.hotel.RoomReservation;

public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

}
