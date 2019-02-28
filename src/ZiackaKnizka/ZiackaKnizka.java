package ZiackaKnizka;

import Pouzivatelia.*;

public class ZiackaKnizka {

	public static void main(String[] args) {
		Ziak[] z = new Ziak[100];

		z[0] = new Ziak("Peter", "Striz");
		z[1] = new Ziak("Marek", "Vajda");
		// List<Ziak> moj = new ArrayList<Ziak>();

		for (Ziak zi : z)
			System.out.println(zi.vratMeno() + " " + zi.vratPriezvisko());
	}

}
