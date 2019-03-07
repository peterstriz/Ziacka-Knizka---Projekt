package ZiackaKnizka;

import Pouzivatelia.*;

public class ZiackaKnizka {
	public Trieda[] trieda = new Trieda[20];

	public void nacitaj() {
		trieda[0] = new Trieda("2.B", 16);

		trieda[0].ziak[0] = new Ziak("Peter", "Striz", 10);
		trieda[0].ziak[0].nastavLogin("striz98", "heslo");
		trieda[0].ziak[1] = new Ziak("Marek", "Vajda", 10);
		trieda[0].ziak[0].nastavLogin("vajda98", "abcdef");
	}

	public String vypisZiakovTriedy(Trieda trieda) {
		String string = "";

		for (Ziak zi : trieda.ziak)
			string += vratCeleMeno(zi) + "\n";

		return string;
	}

	public Pouzivatel vratPouzivatela() {
		Pouzivatel pouzivatel = null;
		
		//for (Pouzivatel pouzi : )
		
		return pouzivatel;
	}
	
	public String vratCeleMeno(Pouzivatel pouzivatel) {
		String string;
		
		if (pouzivatel != null) {
			string = pouzivatel.vratMeno() + " " + pouzivatel.vratPriezvisko();
			return string;
		}
		else
			return "-";
	}

}
