package ZiackaKnizka;

import Pouzivatelia.*;

import java.io.Serializable;
import java.util.*;

public class ZiackaKnizka implements Serializable {
	public List<Trieda> trieda = new ArrayList<>();
	public List<Pouzivatel> pouzivatel = new ArrayList<>();

	public ZiackaKnizka() {
		trieda.add(new Trieda("2.B"));
		trieda.add(new Trieda("Oktava"));

		pouzivatel.add(new Ziak("Peter", "Striz"));
		pouzivatel.get(0).nastavLogin("striz98", "heslo");
		trieda.get(0).pridajZiaka((Ziak) pouzivatel.get(0));

		pouzivatel.add(new Ziak("Marek", "Vajda"));
		pouzivatel.get(1).nastavLogin("vajda98", "abcdef");
		trieda.get(0).pridajZiaka((Ziak) pouzivatel.get(1));

		pouzivatel.add(new Ziak("Juraj", "Polak"));
		pouzivatel.get(2).nastavLogin("polak98", "heslo");
		trieda.get(1).pridajZiaka((Ziak) pouzivatel.get(2));

		pouzivatel.add(new Ucitel("Lucia", "Simova"));
		pouzivatel.get(3).nastavLogin("simova", "heslo");

		trieda.get(0).pridajPredmet("Matematika");
		trieda.get(0).pridajPredmet("Slovencina");
		trieda.get(1).pridajPredmet("Anglictina");

		pouzivatel.get(0).pridajZnamku(0, 10, 20, null);
		pouzivatel.get(0).pridajZnamku(0, 9, 20, null);
		pouzivatel.get(0).pridajZnamku(1, 101, 40, null);
		pouzivatel.get(0).pridajZnamku(1, 913, 40, null);
		pouzivatel.get(1).pridajZnamku(0, 15, 30, null);
		pouzivatel.get(2).pridajZnamku(0, 20, 30, null);
		
		Pouzivatel tempPouzi = pouzivatel.get(3);
		
		if (tempPouzi instanceof Ucitel) {
			((Ucitel) tempPouzi).pridajZiakov(trieda.get(0).vratZiakovTriedy());
		}
	}

//	public String vypisZiakovTriedy(Trieda trieda) {
//		String string = "";
//
//		for (Ziak zi : trieda.vratZiakov())
//			string += vratCeleMeno(zi) + "\n";
//
//		return string;
//	}

	public Pouzivatel overLogin(String username, String password, List<Pouzivatel> pouzivatel) {
		for (Pouzivatel pouzivatelFor : pouzivatel)
			if (pouzivatelFor != null && pouzivatelFor.overLogin(username, password))
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
