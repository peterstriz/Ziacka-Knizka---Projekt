package ZiackaKnizka;

import Pouzivatelia.*;

public class ZiackaKnizka {
	public Ziak[] ziak = new Ziak[100];
	public Trieda[] trieda = new Trieda[20];
	public Ucitel[] ucitel = new Ucitel[20];

	public void nacitaj() {
		trieda[0] = new Trieda("2.B", 16);		
		
		trieda[0].pridajZiaka(ziak[0] = new Ziak("Peter", "Striz"));
		trieda[0].ziak[0].nastavLogin("striz98", "heslo");
		
		trieda[0].pridajZiaka(ziak[1] = new Ziak("Marek", "Vajda"));
		trieda[0].ziak[1].nastavLogin("vajda98", "abcdef");
		
		ucitel[0] = new Ucitel("Lucia", "Simova");
		ucitel[0].nastavLogin("simova", "heslo");
	}

	public String vypisZiakovTriedy(Trieda trieda) {
		String string = "";

		for (Ziak zi : trieda.ziak)
			string += vratCeleMeno(zi) + "\n";

		return string;
	}

	public Pouzivatel overLogin(String username, String password, Pouzivatel... pouzivatel) {
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
