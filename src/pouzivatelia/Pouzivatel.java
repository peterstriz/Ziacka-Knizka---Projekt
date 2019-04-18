package pouzivatelia;

import guiAplikacnaLogika.ManazerLogin;

/**
 * Pouzivatel je interface z ktoreho su odvodeni vsetci pouzivatelia.
 * 
 * @author Peter Striz
 * @see Ziak
 * @see Ucitel
 * @see Riaditel
 */
public interface Pouzivatel {
	public void nastavLogin(String username, String password);

	public Boolean overLogin(String username, String password);

	public Boolean overUsername(String username);

	public Boolean overPassword(String password);

	public void setPassword(String password);

	public String getMeno();

	public String getPriezvisko();

	public String vratCeleMeno();

	public void setMeno(String meno);

	public void setPriezvisko(String priezvisko);

	public String getEmail();

	public void setEmail(String email);

	public void login(ManazerLogin m);
}
