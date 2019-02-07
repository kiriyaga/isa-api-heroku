package isa_api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Value("${token.header}")
	private String tokenHeader;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

	/**
	 * Metoda koja vrsi autorizaciju korisnika preko jwt tokena
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (this.tokenUtils.validateToken(authToken, userDetails)) {
				logger.info("Korinsik ima sledeci autoritet:" + userDetails.getAuthorities().toString());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), authToken, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

}