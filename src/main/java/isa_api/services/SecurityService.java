package isa_api.services;

public interface SecurityService {

	/**
	 * Metoda vraca da li korisnik ima zadati authority
	 * 
	 * @param authority
	 * @return true ili false u zavisnosti da li ima korisnik ima zadati authority
	 */
	public Boolean hasProtectedAccess(String authority);

}