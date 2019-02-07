package isa_api.services;

import org.springframework.http.ResponseEntity;

import isa_api.beans.users.User;
import isa_api.dto.ProfileBasicDTO;
import isa_api.dto.ProfilePasswordDTO;
import isa_api.dto.RegisterUserDTO;

public interface UserService {

	/**
	 * Metoda koja vraca usera za zadati username
	 * 
	 * @param username
	 * @return User
	 */
	User loadUserByUsername(String username);

	/**
	 * Metoda koja vrsi login korisnika na sistem i generise jwt token
	 * 
	 * @param username
	 * @param password
	 * @return UserDTO(User,token)
	 */
	ResponseEntity<Object> userLogin(String username, String password);

	/**
	 * Metoda koja vrsi registraciju korisnika
	 * 
	 * @param user
	 * @return Response- da li je uspesna registracija(poruka)
	 */
	ResponseEntity<Object> userRegister(RegisterUserDTO user);

	/**
	 * Metoda koja vrsi verifikaciju korisnika
	 * 
	 * @param token
	 * @return Response - da li je uspesno verifikovan korisnik
	 */
	ResponseEntity<Object> verifyUser(String token);

	/**
	 * Metoda koja vrsi promenu podataka koje je korisnik promenio
	 * 
	 * @param profileBasic
	 * @return Response - da li su uspesno promenjeni podaci korinsika
	 */
	ResponseEntity<Object> profileBasicEdit(ProfileBasicDTO profileBasic);

	/**
	 * Metoda koja vrsi promenu sifre korinsika koju koristi pri logovanju na sistem
	 * 
	 * @param profilePassword
	 * @return Response- da li je uspesno promenjena sifra korisnika
	 */
	ResponseEntity<Object> profilePasswordEdit(ProfilePasswordDTO profilePassword);

	ResponseEntity<Object> searchForUsers(String name, String lastname);

}
