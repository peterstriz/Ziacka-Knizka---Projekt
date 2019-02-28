package Pouzivatelia;

public class osobneUdaje {
	private String meno;
	private String priezvisko;
	private int ID;

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
