package Pouzivatelia;

import gui.ManazerLogin;

public interface Pouzivatel {
	public void nastavLogin(String username, String password);

	public Boolean overLogin(String username, String password);

	public Boolean overUsername(String username);

	public String getMeno();

	public String getPriezvisko();

	public String vratCeleMeno();

	public void nastavMeno(String meno);

	public void nastavPriezvisko(String priezvisko);
	
	public void login(ManazerLogin m);
}
