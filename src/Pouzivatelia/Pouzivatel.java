package Pouzivatelia;

import java.util.Date;

import javafx.collections.ObservableList;

public interface Pouzivatel {
	public void nastavLogin(String username, String password);
	public Boolean overUserName(String username);
	public Boolean overPassword(String username);
	public String vratMeno();
	public String vratPriezvisko();
//	public ObservableList<Znamky> vratZnamku(List<Znamky> znamka);
	public void pridajPredmet(Predmet predmetNovy);
	public String vratMenoPredmetu(int i);
	public ObservableList<Znamka> vratZnamkyPredmetu(int i);
	public void pridajZnamku(int i, double hodnota, double maxHodnota, Date datum);
	public Predmet vratPredmet(int i);
}
