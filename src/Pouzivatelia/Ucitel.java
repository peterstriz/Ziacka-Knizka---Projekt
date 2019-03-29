package Pouzivatelia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ucitel extends OsobneUdaje implements Pouzivatel {
	private ObservableList<Trieda> trieda = FXCollections.observableArrayList();;

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

	public void pridajTriedu(Trieda... tr) {
		this.trieda.addAll(tr);
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

}
