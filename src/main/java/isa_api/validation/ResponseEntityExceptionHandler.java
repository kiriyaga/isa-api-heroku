package isa_api.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import isa_api.dto.ResponseMessage;
import isa_api.exception.UserCreditalsException;

@ControllerAdvice
@Controller
public class ResponseEntityExceptionHandler
		extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(
				new ResponseMessage(ex.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserCreditalsException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserCreditalsException ex, WebRequest request) {
		return new ResponseEntity<Object>(new ResponseMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}

}
