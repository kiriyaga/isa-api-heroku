package isa_api.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa_api.beans.users.RegistredUser;

public interface RegistredUserRepository extends JpaRepository<RegistredUser, Long> {

	@Query(value = "SELECT * FROM USER LEFT JOIN REGISTRED_USER ON USER.ID = REGISTRED_USER.ID WHERE USER.USERNAME =?1 ", nativeQuery = true)
	RegistredUser findByUsername(String username);
	
	
	@Query(value = "SELECT * FROM USER LEFT JOIN REGISTRED_USER ON USER.ID = REGISTRED_USER.ID WHERE (?1 IS NULL OR NAME = ?1) AND (?2 IS NULL OR LAST_NAME = ?2) ", nativeQuery = true)
	ArrayList<RegistredUser> findByNameAndLastname(String name, String lastname);

}
