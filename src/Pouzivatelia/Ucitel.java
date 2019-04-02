package Pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.ManazerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ucitel extends OsobneUdaje implements Serializable, Pouzivatel {
	private static final long serialVersionUID = 1L;

	private List<Trieda> trieda = new ArrayList<>();

	public Ucitel(String meno, String priezvisko) {
		super(meno, priezvisko);
	}

	public void nastavLogin(String username, String password) {
		super.nastavLogin(username, password);
	}

	public Boolean overLogin(String username, String password) {
		return super.overLogin(username, password);
	}

	public Boolean overUsername(String username) {
		return super.overUsername(username);
	}

	public String getMeno() {
		return super.getMeno();
	}

	public String getPriezvisko() {
		return super.getPriezvisko();
	}

	public String vratCeleMeno() {
		return getMeno() + " " + getPriezvisko();
	}

	public void nastavMeno(String meno) {
		super.nastavMeno(meno);
	}

	public void nastavPriezvisko(String priezvisko) {
		super.nastavPriezvisko(priezvisko);
	}

	public void pridajTriedu(Trieda... novaTrieda) {
		for (Trieda t : novaTrieda)
			trieda.add(t);
	}

	public Trieda vratTriedu(int i) {
		return trieda.get(i);
	}

	public ObservableList<String> vratMenoTried() {
		ObservableList<String> menoTriedy = FXCollections.observableArrayList();
		for (Trieda t : this.trieda)
			menoTriedy.add(t.getMeno());
		return menoTriedy;
	}

	public void login(ManazerLogin m) {
		m.login(this);
	}

}
