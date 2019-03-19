package ZiackaKnizka;

import Pouzivatelia.*;

//import java.io.Serializable;
import java.util.*;

public class ZiackaKnizka /* implements Serializable */ {
	public List<Trieda> trieda = new ArrayList<>();
	public List<Pouzivatel> pouzivatel = new ArrayList<>();

	public ZiackaKnizka() {
		trieda.add(new Trieda("2.B"));
		trieda.add(new Trieda("Oktava"));

		pouzivatel.add(new Ziak("Peter", "Striz"));
		pouzivatel(0).nastavLogin("striz98", "heslo");

		pouzivatel.add(new Ziak("Marek", "Vajda"));
		pouzivatel(1).nastavLogin("vajda98", "abcdef");

		pouzivatel.add(new Ziak("Juraj", "Polak"));
		pouzivatel(2).nastavLogin("polak98", "heslo");

		pouzivatel.add(new Ucitel("Lucia", "Simova"));
		pouzivatel(3).nastavLogin("simova", "heslo");

		trieda(0).pridajZiaka(pouzivatel, "striz98", "vajda98");
		trieda(1).pridajZiaka(pouzivatel, "polak98");

		trieda(0).pridajPredmet("Matematika", "Slovencina");
		trieda(1).pridajPredmet("Anglictina");

		ziak("striz98").pridajZnamku(0, "10", "20", null);
		ziak("striz98").pridajZnamku(0, "9", "20", null);
		ziak("striz98").pridajZnamku(1, "101", "40", null);
		ziak("striz98").pridajZnamku(1, "913", "40", null);
		ziak("vajda98").pridajZnamku(0, "15", "30", null);
		ziak("polak98").pridajZnamku(0, "20", "30", null);

		ucitel("simova").pridajTriedu(trieda(0), trieda(1));

	}

	public Pouzivatel vratPouzivatela(String username, String password, List<Pouzivatel> pouzivatel) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overLogin(username, password))
				return (pouzivatelFor);

		return null;
	}

	public Pouzivatel vratPouzivatela(String username, List<Pouzivatel> pouzivatel) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return (pouzivatelFor);

		return null;
	}

	public Trieda trieda(int i) {
		return trieda.get(i);
	}

	public Pouzivatel pouzivatel(int i) {
		return pouzivatel.get(i);
	}

	public Ziak ziak(String username) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ziak) pouzivatelFor);
		return null;
	}
	
	public Ucitel ucitel(String username) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overUsername(username))
				return ((Ucitel) pouzivatelFor);
		return null;
	}
}
