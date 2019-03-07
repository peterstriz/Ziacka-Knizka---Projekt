package Pouzivatelia;

public class Pouzivatel extends OsobneUdaje {
	public Pouzivatel(String meno, String priezvisko) {
		ulozMeno(meno);
		ulozPriezvisko(priezvisko);
	}
	
	public void nastavLogin(String username, String password) {
		nastavUserName(username);
		nastavPassword(password);
	}
}
