package pouzivatelia;

import java.io.Serializable;

/**
 * OsobneUdaje je abstaktna trieda v ktorej su ulozene osobne udaaje vsetkych
 * pouzivatelov.
 * 
 * @author Peter Striz
 * @see Ziak
 * @see Ucitel
 * @see Riaditel
 */
public abstract class OsobneUdaje implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Meno pouzivatela. */
	private String meno;
	/** Priezvisko pouzivatela. */
	private String priezvisko;
	/** E-mail pouzivatela. */
	private String email = "";
	/** Pouzivatelske meno. */
	private String username;
	/** Heslo pouzivatela. */
	private String password;

	public OsobneUdaje(String meno, String priezvisko) {
		setMeno(meno);
		setPriezvisko(priezvisko);
	}

	public String getMeno() {
		return this.meno;
	}

	public String getPriezvisko() {
		return this.priezvisko;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Vrati cele meno pouzivatela.
	 */
	public String vratCeleMeno() {
		return getMeno() + " " + getPriezvisko();
	}

	/**
	 * Nastavi pouzivatelske meno a heslo.
	 * 
	 * @param username Nove pouzivatelske meno.
	 * @param password Nove heslo.
	 */
	public void nastavLogin(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	/**
	 * Zisti ci pouzivatelske meno a heslo sa zoduju.
	 * 
	 * @param username Pouzivatelske meno, ktore bude overovane.
	 * @param password Heslo, ktore bude overovane.
	 * @return <b>true</b> Udaje sa zhoduju. <b>false</b> Udaje sa nezhoduju.
	 */
	public Boolean overLogin(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password))
			return true;
		else
			return false;
	}

	/**
	 * Zisti ci sa pouzivatelske meno zhoduje.
	 * 
	 * @param username Pouzivatelske meno, ktore bude overovane.
	 * @return <b>true</b> Udaje sa zhoduju. <b>false</b> Udaje sa nezhoduju.
	 */
	public Boolean overUsername(String username) {
		if (this.username.equals(username))
			return true;
		else
			return false;
	}

	/**
	 * Zisti ci sa pouzivatelske heslo zhoduje.
	 * 
	 * @param username Heslo, ktore bude overovane.
	 * @return <b>true</b> Udaje sa zhoduju. <b>false</b> Udaje sa nezhoduju.
	 */
	public Boolean overPassword(String password) {
		if (this.password.equals(password))
			return true;
		else
			return false;
	}

}
