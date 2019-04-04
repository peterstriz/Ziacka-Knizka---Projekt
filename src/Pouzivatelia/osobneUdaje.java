package Pouzivatelia;

import java.io.Serializable;

public abstract class OsobneUdaje extends UserLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String meno;
	private String priezvisko;
	private String email = "";

	public OsobneUdaje(String meno, String priezvisko) {
		nastavMeno(meno);
		nastavPriezvisko(priezvisko);
	}

	public String getMeno() {
		return this.meno;
	}

	public String getPriezvisko() {
		return this.priezvisko;
	}

	public void nastavMeno(String meno) {
		this.meno = meno;
	}

	public void nastavPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
