package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import isa_api.beans.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
