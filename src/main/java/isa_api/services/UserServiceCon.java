package isa_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa_api.beans.security.SecurityUser;
import isa_api.beans.users.RegistredUser;
import isa_api.beans.users.User;
import isa_api.converters.RegUserDAOtoRegUser;
import isa_api.dao.RegistredUserRepository;
import isa_api.dao.UserRepository;
import isa_api.dto.ProfileBasicDTO;
import isa_api.dto.ProfilePasswordDTO;
import isa_api.dto.RegisterUserDTO;
import isa_api.dto.ResponseMessage;
import isa_api.dto.UserLoginDTO;
import isa_api.exception.UserCreditalsException;
import isa_api.security.TokenUtils;
import isa_api.validation.PasswordValidation;

@Service
public class UserServiceCon implements UserService {

	@Autowired
	private RegistredUserRepository regUserRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcript;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private MailService mailService;

	// private static final Logger logger =
	// LoggerFactory.getLogger(UserDetailsServiceCon.class);

	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	public User loadUserByUsername(String username) throws UserCreditalsException {
		User user = (User) userRepository.findByUsername(username);
		if (user == null) {
			throw new UserCreditalsException("Username '" + username + "' doesn't exist!");
		} else {
			return user;
		}
	}

	@Override
	public ResponseEntity<Object> userLogin(String username, String password) {
		User user = loadUserByUsername(username);

		if (bcript.matches(password, user.getPassword())) {
			SecurityUser userDetails = (SecurityUser) this.userDetailService.loadUserByUsername(username);
			String token = this.tokenUtils.generateToken(userDetails);

			if (!user.getIsVerified()) {
				return new ResponseEntity<Object>(
						new ResponseMessage("Please, verify your account! Check your e-mail!"),
						HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<Object>(new UserLoginDTO(username, user.getName(), user.getLastName(),
					user.getEmail(), user.getTelephone(), user.getAdress(), token, user.getAuthority(),user.getFirstLogin()), HttpStatus.OK);
		}

		throw new UserCreditalsException("Wrong password");
	}

	@Override
	public ResponseEntity<Object> userRegister(RegisterUserDTO user) {

		User userTemp = userRepository.findByUsername(user.getUsername());
		if (userTemp != null)
			throw new UserCreditalsException("Username '" + userTemp.getUsername() + "' already exist!");

		if (!PasswordValidation.validPassword(user.getPassword(), user.getRePassword()))
			throw new UserCreditalsException("Passwords don't match!");

		user.setPassword(bcript.encode(user.getRePassword()));
		RegistredUser regUser = RegUserDAOtoRegUser.create(user);
		userRepository.save(regUser);

		SecurityUser userDetails = (SecurityUser) userDetailService.loadUserByUsername(user.getUsername());
		String token = tokenUtils.generateToken(userDetails);
		try {
			mailService.sendRegistrationActivation(regUser, token);
		} catch (MailException e) {
			return new ResponseEntity<Object>(new ResponseMessage("Can't send a e-mail. Please, try again!"),
					HttpStatus.NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Object>(
				new ResponseMessage("Successfull registration. Please, check e-mail for activation!"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> verifyUser(String token) {

		String username = tokenUtils.getUsernameFromToken(token);

		if (!tokenUtils.validateToken(token, userDetailService.loadUserByUsername(username)))
			return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
					HttpStatus.NOT_ACCEPTABLE);

		RegistredUser userTemp = regUserRepository.findByUsername(username);
		if (userTemp == null)
			throw new UserCreditalsException("Sorry, we can't find your account!");

		if (userTemp.getIsVerified())
			return new ResponseEntity<Object>(new ResponseMessage("Your account has been already activated!"),
					HttpStatus.NOT_ACCEPTABLE);

		userRepository.verifyUser(username);
		return new ResponseEntity<Object>(new ResponseMessage("Well done! Are you ready to start new jorney?"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> profileBasicEdit(ProfileBasicDTO profileBasic) {

		RegistredUser userTemp = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		userRepository.updateUserBasic(profileBasic.getEmail(), profileBasic.getName(), profileBasic.getLastName(),
				profileBasic.getAdress(), profileBasic.getTelephone(), userTemp.getUsername());

		return new ResponseEntity<Object>(
				new UserLoginDTO(userTemp.getUsername(), profileBasic.getName(), profileBasic.getLastName(),
						profileBasic.getEmail(), profileBasic.getTelephone(), profileBasic.getAdress(),
						SecurityContextHolder.getContext().getAuthentication().getCredentials().toString(),
						userTemp.getAuthority(),userTemp.getFirstLogin()),
				HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> profilePasswordEdit(ProfilePasswordDTO profilePassword) {

		RegistredUser userTemp = regUserRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		if (!bcript.matches(profilePassword.getCurrentPassword(), userTemp.getPassword()))
			return new ResponseEntity<Object>(new ResponseMessage("Current password is wrong!"),
					HttpStatus.UNAUTHORIZED);
		if (!PasswordValidation.validPassword(profilePassword.getPassword(), profilePassword.getRePassword()))
			throw new UserCreditalsException("Passwords don't match!");

		userRepository.updateUserPassword(bcript.encode(profilePassword.getPassword()), userTemp.getUsername());
		userRepository.firstTime(userTemp.getUsername());
		
		return new ResponseEntity<Object>(
				new UserLoginDTO(userTemp.getUsername(), userTemp.getName(), userTemp.getLastName(),
						userTemp.getEmail(), userTemp.getTelephone(), userTemp.getAdress(),
						SecurityContextHolder.getContext().getAuthentication().getCredentials().toString(),
						userTemp.getAuthority(),false),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> searchForUsers(String name, String lastname) {

		System.out.println("NAME: " + name + "," + "Lastname: " + lastname);
		ArrayList<RegistredUser> users = regUserRepository.findByNameAndLastname(name, lastname);
		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}

}
