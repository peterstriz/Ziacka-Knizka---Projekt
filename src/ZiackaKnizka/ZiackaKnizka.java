package ZiackaKnizka;

import Pouzivatelia.*;
import java.util.*;

public class ZiackaKnizka {
	// public Ziak[] ziak = new Ziak[100];
	// public Trieda[] trieda = new Trieda[20];
	// public Ucitel[] ucitel = new Ucitel[20];
	public List<Trieda> trieda = new ArrayList<>();
	public List<Pouzivatel> pouzivatel = new ArrayList<>();

	public ZiackaKnizka() {
		trieda.add(new Trieda("2.B"));

		pouzivatel.add(new Ziak("Peter", "Striz"));
		pouzivatel.get(0).nastavLogin("striz98", "heslo");

		pouzivatel.add(new Ziak("Marek", "Vajda"));
		pouzivatel.get(1).nastavLogin("vajda98", "abcdef");

		pouzivatel.add(new Ucitel("Lucia", "Simova"));
		pouzivatel.get(2).nastavLogin("simova", "heslo");

	}

	public String vypisZiakovTriedy(Trieda trieda) {
		String string = "";

		for (Ziak zi : trieda.ziak)
			string += vratCeleMeno(zi) + "\n";

		return string;
	}

	public Pouzivatel overLogin(String username, String password, List<Pouzivatel> pouzivatel) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overUserName(username) && pouzivatelFor.overPassword(password))
				return (pouzivatelFor);

		return null;
	}

	public String vratCeleMeno(Pouzivatel pouzivatel) {
		String string;

		if (pouzivatel != null) {
			string = pouzivatel.vratMeno() + " " + pouzivatel.vratPriezvisko();
			return string;
		} else
			return "-";
	}

}
