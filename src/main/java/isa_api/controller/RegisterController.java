package isa_api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa_api.dto.RegisterUserDTO;
import isa_api.services.UserService;

@RestController
@RequestMapping("/public")
@CrossOrigin
public class RegisterController {

	@Autowired
	private UserService userService;

	/**
	 * Controller za registraciju korsnika na sistem uz potrebne validacije
	 * 
	 * @param user
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO user) {

		return userService.userRegister(user);

	}

	/**
	 * Controller za verifikaciju korisnika preko linka koji je poslat na korisnikov
	 * mail
	 * 
	 * @param token
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/register/{token}")
	public ResponseEntity<?> verifyUser(@PathVariable String token) {
		return userService.verifyUser(token);

	}

}
