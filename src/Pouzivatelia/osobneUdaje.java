package Pouzivatelia;

public abstract class OsobneUdaje extends UserLogin {
	private String meno;
	private String priezvisko;

	public OsobneUdaje(String meno, String priezvisko) {
		nastavMeno(meno);
		nastavPriezvisko(priezvisko);
	}
	
	public String vratMeno() {
		return this.meno;
	}

	public String vratPriezvisko() {
		return this.priezvisko;
	}

	public void nastavMeno(String meno) {
		this.meno = meno;
	}

	public void nastavPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

}
