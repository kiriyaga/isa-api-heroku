package isa_api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa_api.beans.Friendship;
import isa_api.dto.FriendParamsDTO;
import isa_api.dto.ProfileBasicDTO;
import isa_api.dto.ProfilePasswordDTO;
import isa_api.services.FriendshipService;
import isa_api.services.UserService;

@Controller
@CrossOrigin(allowedHeaders = { "Token-Authority",
		"Content-Type" }, origins = "http://localhost:4200", allowCredentials = "true")
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private FriendshipService friendshipService;

	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/basicEdit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> profileBasiceEdit(@Valid @RequestBody ProfileBasicDTO profileBasic) {

		return userService.profileBasicEdit(profileBasic);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/passwordEdit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> profileBasiceEdit(@Valid @RequestBody ProfilePasswordDTO profilePassword) {

		return userService.profilePasswordEdit(profilePassword);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/searchForFriend", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchforUsers(@RequestBody FriendParamsDTO params) {

		return userService.searchForUsers(params.getName(), params.getLastname());
	}

	// Friendship
	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/sendFriendRequest", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> sendFriendRequest(@RequestBody String username) {

		return friendshipService.sendFriendRequest(username);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/acceptFriendship", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateFriendship(@RequestBody Friendship friendship) {

		return friendshipService.acceptFriendRequest(friendship);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/auth/profile/denyFriendship", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> denyFriendship(@RequestBody Friendship friendship) {

		return friendshipService.denyFriendRequest(friendship);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/auth/profile/getFriends")
	public ResponseEntity<?> getAllFriends() {

		return friendshipService.getAllFriends();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/auth/profile/getFriendRequests")
	public ResponseEntity<?> getFriendRequests() {

		return friendshipService.getAllFriendRequests();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/auth/profile/SearchUsers/{search}")
	public ResponseEntity<?> searchUsers(@PathVariable String search) {

		return friendshipService.getSearchUsers(search);
	}

}
