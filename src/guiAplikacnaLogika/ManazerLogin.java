package guiAplikacnaLogika;

import gui.ScenaLogin;
import gui.ScenaRiaditelHlavna;
import gui.ScenaUcitelHlavna;
import gui.ScenaZiakHlavna;
import pouzivatelia.*;
import udaje.ZiackaKnizkaSingleton;

/**
 * Aplikacna logika stojaca za scenou pre Login.
 * 
 * @author Peter Striz
 * @see ScenaLogin
 */
public class ManazerLogin {
	private HlavnyStage hlavnyStage = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private Pouzivatel aktualnyPouzivatel;

	/**
	 * @param username Overovane meno pouzivatela.
	 * @param password Overovane heslo pouzivatela.
	 * @return True ak sa podarilo najst pouzivatela podla zadanych udajov; False ak
	 *         sa nepodarilo.
	 */
	public Boolean loginSubmit(String username, String password) {
		aktualnyPouzivatel = ziackaKnizka.getZiackaKnizka().vratPouzivatelaLogin(username, password);
		if (aktualnyPouzivatel != null) {
			aktualnyPouzivatel.login(this);
			return false;
		} else {
			return true;
		}
	}

	public void login(Ziak z) {
		Scena scena = null;
		scena = new Scena(new ScenaZiakHlavna());
		hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));
	}

	public void login(Ucitel u) {
		Scena scena = null;
		scena = new Scena(new ScenaUcitelHlavna());
		hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));
	}

	public void login(Riaditel r) {
		Scena scena = null;
		scena = new Scena(new ScenaRiaditelHlavna());
		hlavnyStage.getStage().setScene(scena.nastavScene(aktualnyPouzivatel));
	}
}
