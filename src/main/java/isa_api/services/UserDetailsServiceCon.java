package isa_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isa_api.beans.security.SecurityUserFactory;
import isa_api.beans.users.User;
import isa_api.dao.UserRepository;

@Service
public class UserDetailsServiceCon implements UserDetailsService {

	// private static final Logger logger =
	// LoggerFactory.getLogger(UserDetailsServiceCon.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return SecurityUserFactory.create(user);
		}
	}

}