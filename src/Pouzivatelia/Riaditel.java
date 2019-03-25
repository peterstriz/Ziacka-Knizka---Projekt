package Pouzivatelia;

public class Riaditel extends OsobneUdaje implements Pouzivatel {
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

}
