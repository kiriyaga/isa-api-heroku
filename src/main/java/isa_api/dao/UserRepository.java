package isa_api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa_api.beans.users.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM USER LEFT JOIN REGISTRED_USER ON USER.ID = REGISTRED_USER.ID LEFT JOIN SYSTEM_ADMIN ON USER.ID = SYSTEM_ADMIN.ID LEFT JOIN AVIO_ADMIN ON USER.ID = AVIO_ADMIN.ID LEFT JOIN HOTEL_ADMIN ON USER.ID = HOTEL_ADMIN.ID LEFT JOIN RENTA_CAR_ADMIN ON USER.ID = RENTA_CAR_ADMIN.ID WHERE USER.USERNAME =?1 ", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "SELECT * FROM USER", nativeQuery = true)
	List<User> getAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE USER SET IS_VERIFIED = 1 WHERE USERNAME = ?1", nativeQuery = true)
	void verifyUser(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USER SET FIRST_LOGIN = 0 WHERE USERNAME = ?1", nativeQuery = true)
	void firstTime(String username);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USER SET EMAIL = ?1 ,NAME = ?2,  LAST_NAME = ?3 , ADRESS = ?4 , TELEPHONE= ?5  WHERE USERNAME = ?6", nativeQuery = true)
	void updateUserBasic(String email, String name, String lastname, String adress, String telephone,String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE USER SET PASSWORD= ?1  WHERE USERNAME = ?2", nativeQuery = true)
	void updateUserPassword(String password,String username);

}
