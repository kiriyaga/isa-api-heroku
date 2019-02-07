package isa_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserCreditalsException extends RuntimeException {

	private static final long serialVersionUID = 7585382983410067115L;

	public UserCreditalsException(String message) {
		super(message);
	}

}
