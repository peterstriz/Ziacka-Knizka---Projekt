package Pouzivatelia;

public class OsobneUdaje extends UserLogin {
	private String meno;
	private String priezvisko;

	public String vratMeno() {
		return this.meno;
	}

	public String vratPriezvisko() {
		return this.priezvisko;
	}

	public void ulozMeno(String meno) {
		this.meno = meno;
	}

	public void ulozPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

}
