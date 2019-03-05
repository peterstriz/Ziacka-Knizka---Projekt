package ZiackaKnizka;

import Pouzivatelia.*;

public class ZiackaKnizka {

	public static void main(String[] args) {
		Trieda[] t = new Trieda[20];
		Ziak[] z = new Ziak[100];

		
		
		t[0] = new Trieda("2.B", 16);
		
		t[0].ziak[0] = new Ziak("Peter", "Striz", 10);
		t[0].ziak[1] = new Ziak("Marek", "Vajda", 10);
		
		
		

		for (Ziak zi : t[0].ziak)
			System.out.println(zi.vratMeno() + " " + zi.vratPriezvisko());
		
		
	}

}
