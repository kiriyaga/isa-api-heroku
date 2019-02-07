package isa_api.validation;

public class PasswordValidation {

	public static boolean validPassword(String password, String re_password) {
		if (password.equals(re_password))
			return true;
		return false;
	}

}
