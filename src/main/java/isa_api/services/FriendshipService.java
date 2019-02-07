package isa_api.services;

import org.springframework.http.ResponseEntity;

import isa_api.beans.Friendship;

public interface FriendshipService {

	public ResponseEntity<Object> sendFriendRequest(String friendUN);

	public ResponseEntity<Object> acceptFriendRequest(Friendship friendship);

	public ResponseEntity<Object> denyFriendRequest(Friendship friendship);

	public ResponseEntity<Object> getAllFriendRequests();

	public ResponseEntity<Object> getAllFriends();

	public ResponseEntity<Object> getSearchUsers(String search);

}
