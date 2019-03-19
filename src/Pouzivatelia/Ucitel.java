package Pouzivatelia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ucitel extends OsobneUdaje implements Pouzivatel {
	// private ObservableList<Ziak> ziaciUcitela =
	// FXCollections.observableArrayList();;
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

	public String vratMeno() {
		return super.vratMeno();
	}

	public String vratPriezvisko() {
		return super.vratPriezvisko();
	}

	public String vratCeleMeno() {
		return vratMeno() + " " + vratPriezvisko();
	}

	public void nastavMeno(String meno) {
		super.nastavMeno(meno);
	}

	public void nastavPriezvisko(String priezvisko) {
		super.nastavPriezvisko(priezvisko);
	}

	//==
	public ObservableList<Znamka> vratZnamkyPredmetu(int i) {
		return null;// ziak.vratPredmet(i).vratZnamku();
	}

	// ==
	public void pridajPredmet(Predmet predmetNovy) {

	}

	// ==
	public void pridajZnamku(int cisloPredmetu, String hodnota, String maxHodnota, String datum) {

	}

	// ==
	public Predmet vratPredmet(int i) {
		return null;
	}

	// ==
	public ObservableList<String> vratMenoPredmetov() {
		return null;
	}

//	public void pridajZiakov(ObservableList<Ziak> z) {
//		this.ziaciUcitela.addAll(z);
//	}
//
//	public void pridajZiaka(Ziak z) {
//		this.ziaciUcitela.add(z);
//	}
//
//	public Ziak vratZiaka(int i) {
//		return ziaciUcitela.get(i);
//	}

//	public ObservableList<String> vratMenoZiakov() {
//		ObservableList<String> menoZiaka = FXCollections.observableArrayList();
//		for (Ziak z : this.ziaciUcitela)
//			menoZiaka.add(z.vratMeno() + " " + z.vratPriezvisko());
//		return menoZiaka;
//	}

	public void pridajTriedu(Trieda... tr) {
		this.trieda.addAll(tr);
	}

	public Trieda vratTriedu(int i) {
		return trieda.get(i);
	}

	public ObservableList<String> vratMenoTried() {
		ObservableList<String> menoTriedy = FXCollections.observableArrayList();
		for (Trieda t : this.trieda)
			menoTriedy.add(t.vratMeno());
		return menoTriedy;
	}

}
