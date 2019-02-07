package isa_api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa_api.beans.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

	@Query(value = "SELECT * FROM FRIENDSHIP f WHERE f.FRIEND_ONE_ID = ?1 and f.FIRENDSHIP_STATUS = 0", nativeQuery = true)
	List<Friendship> getAllFriends(Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE FRIENDSHIP f SET f.FIRENDSHIP_STATUS = ?1 WHERE f.ID = ?2", nativeQuery = true)
	void updateFriendship(int friendshipStatus, Long id);

	@Query(value = "SELECT * FROM FRIENDSHIP f WHERE f.FRIEND_ONE_ID = ?1 and f.FRIEND_TWO_ID = ?2 and f.FIRENDSHIP_STATUS != 1", nativeQuery = true)
	Friendship getFriendship(Long id1, Long id2);

	@Query(value = "SELECT * FROM FRIENDSHIP f WHERE f.FRIEND_ONE_ID = ?1 and f.FIRENDSHIP_STATUS = 2 ", nativeQuery = true)
	List<Friendship> getFriendRequests(Long id);

}
