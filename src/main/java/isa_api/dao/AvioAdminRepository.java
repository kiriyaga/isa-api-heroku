package isa_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa_api.beans.users.AvioAdmin;

public interface AvioAdminRepository extends JpaRepository<AvioAdmin, Long> {
	
	@Query(value = "SELECT * FROM USER LEFT JOIN AVIO_ADMIN ON USER.ID = AVIO_ADMIN.ID WHERE USER.USERNAME =?1 ", nativeQuery = true)
	AvioAdmin findByUsername(String username);

}
