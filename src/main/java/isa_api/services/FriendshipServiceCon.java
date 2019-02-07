package isa_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa_api.beans.Friendship;
import isa_api.beans.FriendshipStatusEnum;
import isa_api.beans.users.RegistredUser;
import isa_api.dao.FriendshipRepository;
import isa_api.dao.RegistredUserRepository;
import isa_api.dto.ResponseMessage;

@Service
public class FriendshipServiceCon implements FriendshipService {

	@Autowired
	private RegistredUserRepository regUserRepository;

	@Autowired
	private FriendshipRepository friendRepository;

	@Override
	public ResponseEntity<Object> sendFriendRequest(String friendUN) {

		System.out.println("usernam drugog : " + friendUN);
		RegistredUser friendOne = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		RegistredUser friendTwo = regUserRepository.findByUsername(friendUN);

		System.out.println("prvi :" + friendOne.getId());
		System.out.println("drugi :" + friendTwo.getId());
		if (friendRepository.getFriendship(friendTwo.getId(), friendOne.getId()) != null
				&& friendRepository.getFriendship(friendOne.getId(), friendTwo.getId()) != null) {
			if (friendRepository.getFriendship(friendTwo.getId(), friendOne.getId())
					.getFirendshipStatus() != FriendshipStatusEnum.DENIED)
				return new ResponseEntity<Object>(new ResponseMessage("You alredy send request!"),
						HttpStatus.NOT_FOUND);

		}
		friendRepository.save(new Friendship(friendOne, friendTwo, FriendshipStatusEnum.WAITING, friendOne));
		friendRepository.save(new Friendship(friendTwo, friendOne, FriendshipStatusEnum.WAITING, friendOne));

		return new ResponseEntity<Object>(new ResponseMessage("Request has been sent!"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> acceptFriendRequest(Friendship friendship) {

		Friendship f = friendRepository.getFriendship(friendship.getFriendTwo().getId(),
				friendship.getFriendOne().getId());

		friendRepository.updateFriendship(0, friendship.getId());

		System.out.println("fff " + f.getId() + f.getFirendshipStatus());
		friendRepository.updateFriendship(0, f.getId());

		return new ResponseEntity<Object>(new ResponseMessage("Successfully accepted friend request!"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> denyFriendRequest(Friendship friendship) {

		friendRepository.updateFriendship(1, friendship.getId());

		Friendship f = friendRepository.getFriendship(friendship.getFriendTwo().getId(),
				friendship.getFriendOne().getId());

		friendRepository.updateFriendship(1, f.getId());

		return new ResponseEntity<Object>(new ResponseMessage("Successfully accepted friend request!"), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getAllFriendRequests() {

		RegistredUser userTemp = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		List<Friendship> friends = friendRepository.getFriendRequests(userTemp.getId());
		System.out.println(friends.size());
		return new ResponseEntity<Object>(friends, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getAllFriends() {

		RegistredUser userTemp = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		List<Friendship> friends = friendRepository.getAllFriends(userTemp.getId());

		return new ResponseEntity<Object>(friends, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getSearchUsers(String search) {

		String[] splitStr = search.split("\\s+");
		System.out.println("name:" + splitStr[0] + "da" + splitStr[1]);
		List<RegistredUser> users = regUserRepository.findAll();
		RegistredUser userTemp = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		List<Friendship> friends = friendRepository.getAllFriends(userTemp.getId());
		List<Friendship> friendRequests = friendRepository.getFriendRequests(userTemp.getId());

		List<RegistredUser> userForSearch = new ArrayList<>();
		for (RegistredUser registredUser : users) {

			if (!registredUser.getName().contains(splitStr[0]) && !registredUser.getLastName().contains(splitStr[1])) {
				continue;
			}
			boolean friendB = false;
			boolean friendRB = false;

			for (Friendship friendship : friends) {
				if (friendship.getFriendOne().getId().equals(registredUser.getId())
						|| friendship.getFriendTwo().getId().equals(registredUser.getId())) {
					friendB = true;
				}

			}

			for (Friendship friendship : friendRequests) {
				if (friendship.getFriendOne().getId().equals(registredUser.getId())
						|| friendship.getFriendTwo().getId().equals(registredUser.getId())) {
					friendRB = true;
				}
			}

			if (friendB == false && friendRB == false && !userTemp.getId().equals(registredUser.getId())) {
				userForSearch.add(registredUser);
			}
		}
		// treba isfiltrirati prijatelje i zahteve za prijateljstvo

		System.out.println(userForSearch.size());

		return new ResponseEntity<Object>(userForSearch, HttpStatus.OK);
	}

}
