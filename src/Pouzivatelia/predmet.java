package Pouzivatelia;

import java.util.*;

public class Predmet {
	private String meno;
	public List<Znamky> znamka = new ArrayList<>();

	public Predmet(String meno) {
		this.meno = meno;
	}

	public List<Znamky> vratZnamky() {
		return znamka;
	}

	public String vratMenoPredmetu() {
		return this.meno;
	}

}
