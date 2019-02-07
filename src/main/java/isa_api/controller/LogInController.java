package isa_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa_api.dto.LoginDTO;
import isa_api.services.MailService;
import isa_api.services.UserService;

@Controller
@CrossOrigin
@RequestMapping("/public")
public class LogInController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

	//private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	/**
	 * Controller za login korsnika na sistem
	 * @param user
	 * @return Response
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO user) {

		return userService.userLogin(user.getUsername(), user.getPassword());

	}
	/**
	 * Test metoda!
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/auth/test")
	@PreAuthorize("@securityService.hasProtectedAccess('SYSADMIN')")
	public ResponseEntity<?> test() {
		mailService.sendTest("MAKICA", "Da li radi ovo?");
		return ResponseEntity.ok("Sve je ok, imas pravo pristupa");
	}

}
