package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa_api.beans.users.HotelAdmin;

public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {
	
	@Query(value = "SELECT * FROM USER LEFT JOIN HOTEL_ADMIN ON USER.ID = HOTEL_ADMIN.ID WHERE USER.USERNAME =?1 ", nativeQuery = true)
	HotelAdmin findByUsername(String username);

}
