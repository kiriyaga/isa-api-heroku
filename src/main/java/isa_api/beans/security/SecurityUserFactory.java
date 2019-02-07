package isa_api.beans.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import isa_api.beans.users.User;

public class SecurityUserFactory {

	//private static final Logger logger = LoggerFactory.getLogger(SecurityUserFactory.class);

	public static SecurityUser create(User user) {

		Collection<? extends GrantedAuthority> authorities;
		try {
			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthority().toString());
		} catch (Exception e) {
			authorities = null;
		}
		return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getLastPasswordReset(), authorities);
	}

}
