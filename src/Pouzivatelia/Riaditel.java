package Pouzivatelia;

import java.io.Serializable;

import gui.ManazerLogin;

public class Riaditel extends OsobneUdaje implements Serializable, Pouzivatel {
	private static final long serialVersionUID = 1L;

	public Riaditel(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void nastavLogin(String username, String password) {
		super.nastavLogin(username, password);
	}

	public Boolean overLogin(String username, String password) {
		return super.overLogin(username, password);
	}

	public Boolean overUsername(String username) {
		return super.overUsername(username);
	}

	public String getMeno() {
		return super.getMeno();
	}

	public String getPriezvisko() {
		return super.getPriezvisko();
	}

	public String vratCeleMeno() {
		return getMeno() + " " + getPriezvisko();
	}

	public void nastavMeno(String meno) {
		super.nastavMeno(meno);
	}

	public void nastavPriezvisko(String priezvisko) {
		super.nastavPriezvisko(priezvisko);
	}

	public void login(ManazerLogin m) {
		m.login(this);
	}

}
